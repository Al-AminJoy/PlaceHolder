package com.alamin.placeholder.view.fragment

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alamin.placeholder.R
import com.alamin.placeholder.databinding.FragmentCreatePostBinding
import com.alamin.placeholder.model.data.Post
import com.alamin.placeholder.model.network.OnResponseCall
import com.alamin.placeholder.utils.LocalDataStore
import com.alamin.placeholder.view_model.PostViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "CreatePostFragment"
class CreatePostFragment : Fragment() {
    private lateinit var binding: FragmentCreatePostBinding;
    private lateinit var viewModel: PostViewModel;
    private lateinit var localDataStore: LocalDataStore;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatePostBinding.inflate(layoutInflater);
        viewModel = ViewModelProvider(this).get(PostViewModel::class.java);
        localDataStore = LocalDataStore(requireContext());
        binding.btnPost.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                localDataStore.getId().collect {
                    if (valideData(binding.txtTitle.text,binding.txtBody.text)){
                        val post = Post(0,binding.txtBody.text.toString(),binding.txtTitle.text.toString(),it);
                        viewModel.createPostToServer(post,object : OnResponseCall{
                            override fun onSuccess(message: String) {
                                binding.txtTitle.setText("");
                                binding.txtBody.setText("");
                                lifecycleScope.launch(Dispatchers.Main) {
                                    findNavController().navigate(R.id.action_createPostFragment_to_homeFragment)
                                }
                            }

                            override fun onFailed(message: String) {
                                lifecycleScope.launch(Dispatchers.Main) {
                                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        });
                    }
                }
            }

        }

        return binding.root
    }

    private fun valideData(title: Editable?, body: Editable?): Boolean {
       return title.toString().trim().isNotEmpty() && body.toString().trim().isNotEmpty();
    }
}