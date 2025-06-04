package com.example.schattenbrecherapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.schattenbrecherapp.Character
import com.example.schattenbrecherapp.CharacterViewModel
import com.example.schattenbrecherapp.SideAttribute
import com.example.schattenbrecherapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val characterViewModel: CharacterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // old template ViewModel and binding of example text
        //val homeViewModel =
        //    ViewModelProvider(this).get(HomeViewModel::class.java)
        //        val textView: TextView = binding.textHome
        //        homeViewModel.text_test.observe(viewLifecycleOwner) {
        //            textView.text = it
        //        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //part of the CharacterModelView loading thing
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set ui and observe the characterViewModel for updates of the values
        characterViewModel.selectedCharacter.observe(viewLifecycleOwner) { character ->
            if (character != null) {
                // Use the character data to update your Fragment's UI

                //character name
                binding.textCharacterName.text = character.characterName

                //health bar
                binding.healthTextView.text = "LE: ${character.currentHealthPoints}/${character.sideAttributesList[SideAttribute.LEBENSENERGIE]}"
                binding.healthProgressBar.max = character.sideAttributesList[SideAttribute.LEBENSENERGIE]!!
                binding.healthProgressBar.progress = character.currentHealthPoints

                //ehrem
                binding.ehremTextView.text = "EH: ${character.currentEhremPoints}/${character.sideAttributesList[SideAttribute.EHREM]}"
                binding.ehremProgressBar.max = character.sideAttributesList[SideAttribute.EHREM]!!
                binding.ehremProgressBar.progress = character.currentEhremPoints

                //Geistige Gesundheit - mental health
                binding.mentalHealthTextView.text = "GG: ${character.currentMentalHealthPoints}/${character.sideAttributesList[SideAttribute.GEISTIGE_GESUNDHEIT]}"
                binding.mentalHealthProgressBar.max = character.sideAttributesList[SideAttribute.GEISTIGE_GESUNDHEIT]!!
                binding.mentalHealthProgressBar.progress = character.currentMentalHealthPoints

                // ... update health bars, stats, lists, etc.
            } else {
                // Handle case where character is not loaded (e.g., show placeholder, navigate away)
                binding.textCharacterName.text = "No Character Selected"
            }
        }

        characterViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            // Show/hide a loading indicator in this fragment
            // binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observe the Toast message LiveData for realizing when an error occurs loading the character
        characterViewModel.toastMessage.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { message ->
                // Use requireContext() or context, ensuring it's not null
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}