package com.superjob.android.ui

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.superjob.android.R
import com.superjob.android.di.DaggerMainComponent
import com.superjob.android.di.MainProvider
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: Lazy<MainViewModel.Factory>
    private val viewModel: MainViewModel by viewModels { viewModelFactory.get() }

    private lateinit var mainBottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val dependencies = (applicationContext as MainProvider).mainDependencies
        DaggerMainComponent.builder().dependencies(dependencies).build().inject(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        mainBottomNavigationView = findViewById(R.id.main_bottom_navigation_view)
        mainBottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(
                destination.id == com.superjob.android.feature.login.R.id.loginFragment ||
                destination.id == com.superjob.android.feature.login.R.id.confirmationFragment
            ) {
                mainBottomNavigationView.visibility = View.GONE
                window.navigationBarColor =
                    ContextCompat.getColor(
                        this, com.superjob.android.core.ui.R.color.md_theme_background
                    )
            } else {
                mainBottomNavigationView.visibility = View.VISIBLE
                window.navigationBarColor =
                    ContextCompat.getColor(
                        this, com.superjob.android.core.ui.R.color.md_theme_inverseOnSurface
                    )
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.getFavoritesVacancies()
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { vacancies ->
                    val favoritesNumberIcon = mainBottomNavigationView.getOrCreateBadge(
                        com.superjob.android.feature.favorites.R.id.favorites_nav_graph
                    )
                    if (vacancies.isNotEmpty()) {
                        favoritesNumberIcon.isVisible = true
                        favoritesNumberIcon.badgeTextColor = Color.WHITE
                        favoritesNumberIcon.number = vacancies.size
                    } else {
                        favoritesNumberIcon.isVisible = false
                    }
                }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}