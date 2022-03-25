package com.alamin.placeholder.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.alamin.placeholder.R
import com.alamin.placeholder.databinding.ActivityLoginBinding
import com.alamin.placeholder.databinding.ActivityMainBinding
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.fragment);
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration);
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment);

        return super.onSupportNavigateUp() || navController.navigateUp(appBarConfiguration);
    }
}