package com.alamin.placeholder.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alamin.placeholder.databinding.FragmentHomeBinding
import com.alamin.placeholder.model.data.Post
import com.alamin.placeholder.utils.AppUtils
import com.alamin.placeholder.utils.LocalDataStore
import com.alamin.placeholder.view.adapter.PostAdapter
import com.alamin.placeholder.view.adapter.PostClickListener
import com.alamin.placeholder.view_model.PostViewModel
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {
    private lateinit var localDataStore: LocalDataStore;
    private lateinit var binding: FragmentHomeBinding;
    private lateinit var postViewModel: PostViewModel;
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var adapter: PostAdapter;

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
            binding.recyclerView.layoutManager = manager;
            adapter = PostAdapter(it.asReversed());
            adapter.setPostClickListener(object: PostClickListener{
                override fun onItemClick(post: Post) {
                    val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(post)
                    findNavController().navigate(action)
                }

            })
            recyclerView.adapter = adapter
        })

        return binding.root;
    }
}