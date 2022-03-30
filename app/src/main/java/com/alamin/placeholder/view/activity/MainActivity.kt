package com.alamin.placeholder.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.alamin.placeholder.R
import com.alamin.placeholder.databinding.ActivityMainBinding
import com.alamin.placeholder.databinding.HeaderBinding
import com.alamin.placeholder.model.data.User
import com.alamin.placeholder.model.local.LocalDatabase
import com.alamin.placeholder.utils.LocalDataStore
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var toolbar: Toolbar? = null
    private lateinit var binding: ActivityMainBinding
    lateinit var headerBinding: HeaderBinding;
    private lateinit var localDatabase: LocalDataStore;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        localDatabase = LocalDataStore(this)

        headerBinding = HeaderBinding.bind(binding.navView.getHeaderView(0));
        lifecycleScope.launchWhenCreated {
            localDatabase.getUser().collect {
                var user: User = Gson().fromJson(it.toString(), User::class.java);
                headerBinding.user = user
            }
        }

        setContentView(binding.root)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.fragment);

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.galleryFragment,
                R.id.createPostFragment,
            ), binding.layoutDrawer
        )

        setupActionBarWithNavController(navController, appBarConfiguration);
        binding.navView.setupWithNavController(navController);
        binding.navView.setNavigationItemSelectedListener {
            if (it.itemId == R.id.logout){
                lifecycleScope.launch {
                    localDatabase.clearAllPreferences();
                }
                startActivity(Intent(this@MainActivity,LoginActivity::class.java))
                finish()
            }else{
                onNavDestinationSelected(it, navController)
                binding.layoutDrawer.closeDrawers()
            }
            true
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment);

        return super.onSupportNavigateUp() || navController.navigateUp(appBarConfiguration);
    }
}