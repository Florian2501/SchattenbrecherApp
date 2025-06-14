package com.example.schattenbrecherapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.schattenbrecherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val characterViewModel: CharacterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Navigation bar setup
        val navView: BottomNavigationView = binding.navView

        //define own toolbar and set it as action bar
        setSupportActionBar(binding.topToolbar)
        //val topToolbar = getSupportActionBar()
        //for "up" button
        //topToolbar?.setDisplayHomeAsUpEnabled(true)


        //setup navigation controller for navigation bar
        navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // with this the "back" or "up" arrows on top left work
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_talents, R.id.navigation_fight,
                R.id.navigation_inventory, R.id.navigation_notes
            )
        )
        // old variant with standard ActionToolbar from Vorlage
        //setupActionBarWithNavController(navController, appBarConfiguration)

        binding.topToolbar.setupWithNavController(navController, appBarConfiguration)
        // example how to change properties of top toolbar:
        //actionBar?.setTitle("uhfosef")

        navView.setupWithNavController(navController)

        // init loading of character here -> needs to be switched to loading and selection screen later
        characterViewModel.loadCharacter("file:///android_asset/character_quentin.json")
    }

    // need to inflate the top toolbar with the self created menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // If app:menu is used on Toolbar, you might not strictly need to inflate here.
        menuInflater.inflate(R.menu.top_action_bar_menu, menu)
        // However, ensure you return true if the menu should be shown.
        return true // Or super.onCreateOptionsMenu(menu)
    }

    // logic of the menu selection in the top bar
    // see also for dynamic top bars:
    // https://developer.android.com/develop/ui/compose/components/app-bars-dynamic
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            // User chooses the "Settings" item. Show the app settings UI.
            // Navigate to the settings fragment using its ID from the navigation graph.
            // Make sure R.id.navigation_settings matches the ID in your navigation graph.
            try {
                navController.navigate(R.id.navigation_settings) // <--- NAVIGATION HAPPENS HERE
            } catch (e: IllegalArgumentException) {
                // This can happen if the destination ID is not found in the current graph
                // or if you're trying to navigate to the current destination.
                Toast.makeText(this, "Could not navigate to settings.", Toast.LENGTH_SHORT).show()
                // Log.e("NavigationError", "Error navigating to settings", e)
            }
            true // Indicate that the event was handled
        }

        R.id.action_statistics -> {
            // User chooses the "Statistics" action.
            try {
                navController.navigate(R.id.navigation_statistics) // <--- NAVIGATION HAPPENS HERE
            } catch (e: IllegalArgumentException) {
                // This can happen if the destination ID is not found in the current graph
                // or if you're trying to navigate to the current destination.
                Toast.makeText(this, "Could not navigate to statistics.", Toast.LENGTH_SHORT).show()
                // Log.e("NavigationError", "Error navigating to statistics", e)
            }
            true // Indicate that the event was handled
        }

        R.id.action_wiki -> {
            // User chooses the "Wiki" action. Open the Schattenrbecher Wiki
            // in Browser
            try {
                val url = "https://schattenbrecher.fandom.com/de/wiki/Schattenbrecher_Wiki"
                val webPage: Uri = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, webPage)
                startActivity(intent)

            } catch (e: ActivityNotFoundException) {
                // This specific exception is less likely for web URLs but good for other implicit intents
                Toast.makeText(this, "No app can handle this request. Please install a web browser.", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            } catch (e: Exception) {
                // Catch any other exception during URI parsing or intent creation
                Toast.makeText(this, "Error opening website.", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }

            true
        }

        else -> {
            // The user's action isn't recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}