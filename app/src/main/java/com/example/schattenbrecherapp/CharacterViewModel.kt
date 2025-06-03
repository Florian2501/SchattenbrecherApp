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
    val selectedcharacter: LiveData<Character?> = _selectedCharacter

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val characterRepository = CharacterRepository

    // LiveData for Toast messages (using an Event wrapper) see class Event.kt
    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>> = _toastMessage

    fun loadCharacter(characterFilePath: String) {
        if (_selectedCharacter.value != null && _selectedCharacter.value?.filePath == characterFilePath) {
            // Character already loaded and is the same, no need to reload
            // Or if your filePath is the ID, use: _selectedCharacter.value?.filePath == characterFilePathOrId
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Replace with the actual character loading logic
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

    // You can add other methods here to modify character data if needed,
    // which would then update the LiveData and notify all observers.
    // e.g., fun updateCharacterHealth(newHealth: Int) { ... }
}

