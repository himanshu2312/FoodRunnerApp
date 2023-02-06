package com.himanshu.foodrunnerapp.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.himanshu.foodrunnerapp.R
import com.himanshu.foodrunnerapp.fragment.FAQFragment
import com.himanshu.foodrunnerapp.fragment.FavoriteFragment
import com.himanshu.foodrunnerapp.fragment.HomeFragment
import com.himanshu.foodrunnerapp.fragment.ProfileFragment

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var frameLayout: FrameLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var sideDrawer: DrawerLayout
    private var previousItem: MenuItem? = null
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationView)
        toolbar = findViewById(R.id.toolbar)
        sideDrawer = findViewById(R.id.sideDrawer)
        setUpToolbar(toolbar)
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            sideDrawer,
            R.string.open_drawer,
            R.string.close_drawer
        )
        sideDrawer.setDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        openFragment(getString(R.string.home))
        navigationView.setCheckedItem(R.id.home)

        navigationView.setNavigationItemSelectedListener {
            if (previousItem != null) {
                previousItem?.isChecked = false
            }
            it.isChecked = true
            if (it.title != getString(R.string.log_out)) {
                openFragment(it.title.toString())
            }
            else{
                sharedPreferences=getSharedPreferences(R.string.app_name.toString(), MODE_PRIVATE)
                sharedPreferences.edit().putBoolean("isLoggedIn",false).apply()
                val intent=Intent(this@MainActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            return@setNavigationItemSelectedListener true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            sideDrawer.open()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Home"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun openFragment(name: String) {
        val fragment = when (name) {
            getString(R.string.home) -> HomeFragment()
            getString(R.string.favorite) -> FavoriteFragment()
            getString(R.string.profile) -> ProfileFragment()
            else -> FAQFragment()
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment).commit()
        supportActionBar?.title = name
        sideDrawer.close()
    }

    override fun onBackPressed() {
        when(supportFragmentManager.findFragmentById(R.id.frameLayout)){
            !is HomeFragment -> openFragment(getString(R.string.home))
            else -> super.onBackPressed()
        }
    }
}