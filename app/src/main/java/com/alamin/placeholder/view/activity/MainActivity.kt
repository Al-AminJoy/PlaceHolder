package com.alamin.placeholder.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.alamin.placeholder.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.fragment));
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment);

        return super.onSupportNavigateUp() || navController.navigateUp();
    }
}