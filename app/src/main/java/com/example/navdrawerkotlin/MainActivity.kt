package com.example.navdrawerkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.navdrawerkotlin.fragments.ContactUsFragment
import com.example.navdrawerkotlin.fragments.HomeFragment
import com.example.navdrawerkotlin.fragments.SettingsFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener{
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    private lateinit var toolbar:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
    }

    private fun initializeViews(){
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigationView)
        toolbar = findViewById(R.id.toolbar)

        navigationView.setCheckedItem(R.id.home)
        replaceFragment(HomeFragment.newInstance())
        setSupportActionBar(toolbar)
        setUpDrawerLayout()
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home -> {
                navigationView.setCheckedItem(R.id.home)
                replaceFragment(HomeFragment.newInstance())
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
            }
            R.id.contact_us -> {
                navigationView.setCheckedItem(R.id.contact_us)
                replaceFragment(ContactUsFragment.newInstance())
                Toast.makeText(this, "Contact us", Toast.LENGTH_SHORT).show()
            }
            R.id.settings -> {
                navigationView.setCheckedItem(R.id.settings)
                replaceFragment(SettingsFragment.newInstance())
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun setUpDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun replaceFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit()
    }
}