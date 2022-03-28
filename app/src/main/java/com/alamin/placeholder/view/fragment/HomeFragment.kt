package com.alamin.placeholder.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alamin.placeholder.databinding.FragmentHomeBinding
import com.alamin.placeholder.utils.AppUtils
import com.alamin.placeholder.utils.LocalDataStore
import com.alamin.placeholder.view.adapter.PostAdapter
import com.alamin.placeholder.view_model.PostViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {
    lateinit var localDataStore: LocalDataStore;
    lateinit var binding: FragmentHomeBinding;
    lateinit var postViewModel: PostViewModel;
    private lateinit var manager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        localDataStore = LocalDataStore(requireContext())
        manager = LinearLayoutManager(requireContext());

        lifecycleScope.launch(Dispatchers.IO) {
            localDataStore.getId().collect {
                if (AppUtils.isOnline(requireContext())) {
                    postViewModel.getPostFromResponseByUserId(it)
                };
            }
        }

        postViewModel.postList.observe(requireActivity(), Observer {
            postViewModel.insertPostList(it)
        })

        postViewModel.getAllPost().observe(requireActivity(), Observer {
            binding.recyclerView.apply {
                layoutManager = manager;
                adapter = PostAdapter(it.asReversed())
            }
        })

        return binding.root;
    }
}