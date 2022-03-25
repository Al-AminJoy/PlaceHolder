package com.alamin.placeholder.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.alamin.placeholder.R
import com.alamin.placeholder.utils.LocalDataStore
import kotlinx.coroutines.flow.collect

class SplashActivity : AppCompatActivity() {
    private val TAG = "SplashActivity"
    lateinit var localDataStore: LocalDataStore;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        localDataStore = LocalDataStore(this)
        lifecycleScope.launchWhenCreated {
            localDataStore.getName().collect {
                if (it !=-1){
                    Log.d(TAG, "CHECK_LOGIN "+"Logged In")
                    startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                    finish()
                }else{
                    Log.d(TAG, "CHECK_LOGIN "+" Not Logged In")
                    startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
}