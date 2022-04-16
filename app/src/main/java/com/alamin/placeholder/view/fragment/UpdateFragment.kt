package com.alamin.placeholder.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alamin.placeholder.PlaceHolderApplication
import com.alamin.placeholder.R
import com.alamin.placeholder.databinding.FragmentUpdateBinding
import com.alamin.placeholder.model.data.Post
import com.alamin.placeholder.model.network.OnResponseCall
import com.alamin.placeholder.utils.LocalDataStore
import com.alamin.placeholder.view_model.PostViewModel
import com.alamin.placeholder.view_model.PostViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "UpdateFragment"

class UpdateFragment : Fragment() {
    @Inject
    lateinit var postViewModelFactory: PostViewModelFactory;
    private val arg by navArgs<UpdateFragmentArgs>()
    private lateinit var binding: FragmentUpdateBinding;
    private lateinit var viewModel: PostViewModel;
    private lateinit var localDataStore: LocalDataStore;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(layoutInflater)
        val component = (requireActivity().applicationContext as PlaceHolderApplication).appComponent
        component.injectUpdate(this);
        viewModel = ViewModelProvider(this,postViewModelFactory).get(PostViewModel::class.java);
        localDataStore = LocalDataStore(requireContext())
        binding.post = arg.post
        binding.btnUpdate.setOnClickListener {
            if (isValid(binding.txtBody.text.toString(), binding.txtTitle.text.toString())) {
                val post = Post(
                    arg.post.id,
                    binding.txtBody.text.toString(),
                    binding.txtTitle.text.toString(),
                    arg.post.userId
                )
                viewModel.updatePostToServer(post, object : OnResponseCall {
                    override fun onSuccess(message: String) {
                        lifecycleScope.launch(Dispatchers.Main) {
                            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_updateFragment_to_homeFragment);
                        }
                    }

                    override fun onFailed(message: String) {
                        lifecycleScope.launch(Dispatchers.Main) {
                            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                        }

                    }

                })
            }

        }
        return binding.root
    }

    private fun isValid(body: String, title: String): Boolean {
        return body.trim().isNotEmpty() && title.trim().isNotEmpty()
    }
}