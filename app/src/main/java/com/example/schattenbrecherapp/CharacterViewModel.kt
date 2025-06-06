package com.example.schattenbrecherapp

import android.util.Log
import android.widget.Toast
import androidx.activity.result.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    // 1. Private MutableLiveData that can be changed within the ViewModel
    // but only by the viewmodel to control access
    private val _selectedCharacter = MutableLiveData<Character?>()

    // 2. Public LiveData that can only be observed from outside by fragments or activities
    // public available and can not be changed from outside
    val selectedCharacter: LiveData<Character?> = _selectedCharacter

    // live data for showing if the character is loading from file or saving to file
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    // LiveData for Toast messages (using an Event wrapper) see class Event.kt
    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>> = _toastMessage

    // object from where the character gets loaded or saved
    private val characterRepository = CharacterRepository


    fun loadCharacter(characterFilePath: String) {
        if (_selectedCharacter.value != null && _selectedCharacter.value?.filePath == characterFilePath) {
            // Character already loaded and is the same, no need to reload
            // Or if your filePath is the ID, use: _selectedCharacter.value?.filePath == characterFilePathOrId
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            try {
                // call repository function to load the character from file
                val character = characterRepository.loadCharacterFromFile(characterFilePath)
                _selectedCharacter.value = character
            } catch (e: Exception) {
                // Handle error (e.g., post an error state to LiveData)
                _selectedCharacter.value = null
                Log.e("CharacterViewModel", "Error loading character", e)
                _toastMessage.value = Event("Error loading character: ${e.message}")

            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearCharacter() {
        _selectedCharacter.value = null
    }

    fun saveCharacter() {

        viewModelScope.launch {
            _isLoading.value = true
            try {
                //call repository function to save the currently used character to file
                characterRepository.saveCharacterToFile(_selectedCharacter.value!!)
            } catch (e: Exception) {
                // Handle error (e.g., post an error state to LiveData)
                Log.e("CharacterViewModel", "Error saving character", e)
                _toastMessage.value = Event("Error saving character: ${e.message}")

            } finally {
                _isLoading.value = false
            }
        }

    }

    /**
     * Generic function to update the character.
     * The 'updater' lambda receives the current character and should return the modified character.
     */
    fun updateCharacter(updater: (Character) -> Character) { // 1. Function Definition
        _selectedCharacter.value?.let { currentCharacter -> // 2. Get current character safely
            // 3. Execute the lambda, passing the current character to it
            // this is to make sure the current value of character is changed and not
            // another class or so updated it in the mean time
            val updatedCharacter: Character = updater(currentCharacter)

            // 4. Update LiveData only if the character actually changed
            if (updatedCharacter != currentCharacter) {
                _selectedCharacter.value = updatedCharacter
                // Consider saving the character to your repository here if needed
                // lets see if this takes too much time per saving
            }
        }
    }

    // You can add other methods here to modify character data if needed,
    // which would then update the LiveData and notify all observers.
    // e.g., fun updateCharacterHealth(newHealth: Int) { ... }
}

