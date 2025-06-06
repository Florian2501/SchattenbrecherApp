package com.example.schattenbrecherapp.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.schattenbrecherapp.CharacterViewModel
import com.example.schattenbrecherapp.SideAttribute
import com.example.schattenbrecherapp.databinding.FragmentUpdateHealthDialogBinding // Import generated binding class
import kotlin.text.toIntOrNull

class UpdateHealthDialogFragment : DialogFragment() {

    // Use view binding for the dialog's layout
    private var _binding: FragmentUpdateHealthDialogBinding? = null
    private val binding get() = _binding!!

    // Get a reference to the shared CharacterViewModel
    private val characterViewModel: CharacterViewModel by activityViewModels()

    companion object {
        const val TAG = "UpdateHealthDialog" // For identifying the dialog

        fun newInstance(): UpdateHealthDialogFragment {
            return UpdateHealthDialogFragment()
        }
    }

    // You can override onCreateView if you want to use a custom view for the entire dialog
    // For MaterialAlertDialogBuilder, onCreateDialog is often sufficient for content.
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate using view binding if you are not using MaterialAlertDialogBuilder's setView
        // If you use MaterialAlertDialogBuilder.setView(), this onCreateView can sometimes be skipped
        // or just return dialog?.window?.decorView.
        // For simplicity with Material Dialogs, using onCreateDialog is often cleaner.
        // However, if you need more complex view hierarchy beyond what setView offers easily,
        // inflating here and returning binding.root is the way.
        _binding = FragmentUpdateHealthDialogBinding.inflate(inflater, container, false)
        return binding.root // Important: return the root of your binding
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // If not using onCreateView, inflate the binding here
        if (_binding == null) {
            _binding = FragmentUpdateHealthDialogBinding.inflate(LayoutInflater.from(context))
        }

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root) // Set the custom layout

        // No need to set title or buttons on the builder if they are in your custom layout
        // Otherwise, you could do:
        // builder.setTitle("Update Health")
        // .setPositiveButton("Apply") { dialog, id -> handleApply() }
        // .setNegativeButton("Cancel") { dialog, id -> dismiss() }

        setupClickListeners()


        // Create the AlertDialog object and return it
        val dialog = builder.create()
        // Optional: Remove default dialog padding/background if your custom layout handles it all
        // dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // If you inflated in onCreateView, setup listeners here.
        // If using onCreateDialog with builder.setView, listeners can be setup there as well
        // or just after builder.setView like in this example's onCreateDialog.
        // For consistency, let's ensure it's called.
        // setupClickListeners() is called in onCreateDialog in this setup.

    }


    private fun setupClickListeners() {
        binding.buttonApply.setOnClickListener {
            handleApply()
        }
        binding.buttonCancel.setOnClickListener {
            dismiss() // Close the dialog
        }
    }

    private fun handleApply() {
        val damageText = binding.editTextDamageTaken.text.toString()
        val healingText = binding.editTextHealingReceived.text.toString()

        val damage = damageText.toIntOrNull() ?: 0
        val healing = healingText.toIntOrNull() ?: 0

        if (damage < 0 || healing < 0) {
            Toast.makeText(context, "Bitte nur positive Werte eingeben.", Toast.LENGTH_LONG).show()
            return
        }

        if (damage == 0 && healing == 0) {
            Toast.makeText(context, "Bitte geben Sie mindestens einen positiven Wert ein.", Toast.LENGTH_SHORT).show()
            return
        }

        var healthChange = 0
        if (damage > 0) {
            healthChange += -damage // Damage reduces health
        }
        if (healing > 0) {
            healthChange += healing // Healing increases health
        }

        characterViewModel.updateCharacter { charToUpdate ->

            val currentHP = charToUpdate.currentHealthPoints
            val maxHP = charToUpdate.getSideAttributesList()[SideAttribute.LEBENSENERGIE]
                ?: currentHP // Fallback to currentHP if maxHP is somehow null

            val newHP = (currentHP + healthChange).coerceIn(0, maxHP)

            charToUpdate.copy(currentHealthPoints = newHP)
        }

        Toast.makeText(context, "Lebensenergie aktualisiert!", Toast.LENGTH_SHORT).show()
        dismiss() // Close the dialog after applying
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Important to avoid memory leaks
    }
}