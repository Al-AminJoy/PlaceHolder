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
import com.alamin.placeholder.PlaceHolderApplication
import com.alamin.placeholder.databinding.FragmentHomeBinding
import com.alamin.placeholder.model.data.Post
import com.alamin.placeholder.utils.AppUtils
import com.alamin.placeholder.utils.LocalDataStore
import com.alamin.placeholder.view.adapter.PostAdapter
import com.alamin.placeholder.view.adapter.PostClickListener
import com.alamin.placeholder.view_model.ViewModelFactory
import com.alamin.placeholder.view_model.PostViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory;
    @Inject
    lateinit var postAdapter: PostAdapter;
    private lateinit var localDataStore: LocalDataStore;
    private lateinit var binding: FragmentHomeBinding;
    private lateinit var postViewModel: PostViewModel;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val component = (requireActivity().applicationContext as PlaceHolderApplication).appComponent
        component.injectHome(this)
        postViewModel = ViewModelProvider(this,viewModelFactory).get(PostViewModel::class.java)
        localDataStore = LocalDataStore(requireContext())

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

        with(postAdapter){
            setPostClickListener(object : PostClickListener {
                override fun onItemClick(post: Post) {
                    val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(post)
                    findNavController().navigate(action)
                }

            })
            postViewModel.getAllPost().observe(requireActivity(), Observer {
                setData(it.asReversed())
            })
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext());
            adapter = postAdapter
        }

        return binding.root;
    }
}