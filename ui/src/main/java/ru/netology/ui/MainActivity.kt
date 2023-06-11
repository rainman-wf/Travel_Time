package ru.netology.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.ui.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHost = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = navHost.navController

        setSupportActionBar(binding.topToolbar)
        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, arguments ->
            binding.topToolbar.apply {
                when (destination.id) {
                    R.id.travelListFragment -> {
                        navigationIcon =
                            AppCompatResources.getDrawable(this@MainActivity, R.drawable.airplane)
                        subtitle = null
                    }

                    R.id.travelDetailsFragment -> {
                        navigationIcon =
                            AppCompatResources.getDrawable(this@MainActivity, R.drawable.back)
                        subtitle =
                            arguments?.let { "${it.getString("start_city")} - ${it.getString("end_city")}" }

                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}