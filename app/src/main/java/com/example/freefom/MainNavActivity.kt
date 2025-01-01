package com.example.freefom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.freefom.fragments.HomeFragment
import com.example.freefom.fragments.SettingsFragment
import com.example.freefom.fragments.SupportFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView


class MainNavActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_nav_activity)

        bottomNav = findViewById(R.id.bottomNavigationView)
        bottomNav.selectedItemId = R.id.nav_home
        bottomNav.setOnItemSelectedListener(navListener)

        //default when load
        val selectedFragment = HomeFragment()

        selectedFragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, selectedFragment).commit()

    }

    private val navListener = NavigationBarView.OnItemSelectedListener { item ->
        val itemId = item.itemId

        var selectedFragment: Fragment? = null

        when (itemId) {
            R.id.nav_home -> selectedFragment = HomeFragment()
            R.id.nav_support -> selectedFragment = SupportFragment()
            R.id.nav_settings -> selectedFragment = SettingsFragment()
        }

        if (selectedFragment != null) {
            selectedFragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, selectedFragment)
                .commit()
        }


        true
    }

    fun replaceFragment(fragment: Fragment, keepBottomNavState: Boolean) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()

        if (keepBottomNavState) {
            // Do not update BottomNavigationView state
            return
        }
    }

}