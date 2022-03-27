package com.alamin.placeholder.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.alamin.placeholder.R

import com.alamin.placeholder.databinding.ActivityLoginBinding
import com.alamin.placeholder.utils.LocalDataStore
import com.alamin.placeholder.view_model.UserViewModel
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    private lateinit var binding: ActivityLoginBinding
    lateinit var userViewModel: UserViewModel;
    lateinit var localDataStore: LocalDataStore;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java);
        binding.userViewModel = userViewModel;
        binding.lifecycleOwner = this;
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        localDataStore = LocalDataStore(this)

    }

    override fun onStart() {
        super.onStart()

        binding.btnLogin.setOnClickListener {
            if (validateData(binding.txtUid.text)){
              userViewModel.user.observe(this, Observer {
                    if (it != null){
                        lifecycleScope.launch {
                            userViewModel.createUser(it);
                        }
                        lifecycleScope.launch {
                            localDataStore.storeName(it.name)
                            localDataStore.storeId(it.id)
                            localDataStore.storeUser(Gson().toJson(it))
                        }
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        finish()
                    }
                })
                userViewModel.getUserResponse(binding.txtUid.text.toString().toInt());
            }
        }
    }
    private fun validateData(email: Editable?): Boolean {
        return email.toString()?.trim()?.isNotEmpty();
    }

}