package ru.netology.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.common.utils.log
import ru.netology.domain.model.Flight
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

        navController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener {
            @SuppressLint("NewApi")
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                binding.topToolbar.apply {
                    when (destination.id) {
                        R.id.travelListFragment -> {
                            navigationIcon = AppCompatResources.getDrawable(this@MainActivity, R.drawable.airplane)
                            subtitle = null
                        }
                        R.id.travelDetailsFragment -> {
                            navigationIcon = AppCompatResources.getDrawable(this@MainActivity, R.drawable.back)
                            subtitle = arguments?.getObject<Flight>("flight")?.let { "${it.startCity} - ${it.endCity}" }
                        }
                    }
                }
            }

        })

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}