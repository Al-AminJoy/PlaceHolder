package com.alamin.placeholder.view.fragment

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alamin.placeholder.R
import com.alamin.placeholder.databinding.FragmentLoginBinding
import com.alamin.placeholder.view_model.UserViewModel
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding;
    lateinit var userViewModel: UserViewModel;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java);
        binding.userViewModel = userViewModel;
        binding.lifecycleOwner = this;

        binding.btnLogin.setOnClickListener {
            if (validateData(binding.txtUid.text)){
                userViewModel.user.observe(viewLifecycleOwner, Observer {
                    if (it != null){
                        lifecycleScope.launch {
                            userViewModel.createUser(it);
                        }
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment);
                    }
                })
                userViewModel.getUserResponse(binding.txtUid.text.toString().toInt());
            }
        }

        return binding.root;
    }

    private fun validateData(email: Editable?): Boolean {
        return email.toString()?.trim()?.isNotEmpty();
    }


}