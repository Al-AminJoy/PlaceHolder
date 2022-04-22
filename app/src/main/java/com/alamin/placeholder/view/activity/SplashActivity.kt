package com.alamin.placeholder.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.alamin.placeholder.R
import com.alamin.placeholder.utils.LocalDataStore
import kotlinx.coroutines.flow.collect

private const val TAG = "SplashActivity"
class SplashActivity : AppCompatActivity() {
    lateinit var localDataStore: LocalDataStore;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        localDataStore = LocalDataStore(this)
        lifecycleScope.launchWhenCreated {
            localDataStore.getUser().collect {
                if (it != -1) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
}