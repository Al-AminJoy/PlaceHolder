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
import com.alamin.placeholder.R
import com.alamin.placeholder.databinding.FragmentGalleryBinding
import com.alamin.placeholder.utils.AppUtils
import com.alamin.placeholder.utils.LocalDataStore
import com.alamin.placeholder.view.adapter.AlbumAdapter
import com.alamin.placeholder.view_model.AlbumViewModel
import kotlinx.coroutines.flow.collect

class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding;
    lateinit var manager: RecyclerView.LayoutManager;
    lateinit var localDataStore: LocalDataStore;
    lateinit var albumViewModel: AlbumViewModel;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryBinding.inflate(layoutInflater);
        albumViewModel = ViewModelProvider(this).get(AlbumViewModel::class.java);
        localDataStore = LocalDataStore(requireContext());
        manager = LinearLayoutManager(requireContext());
        lifecycleScope.launchWhenCreated {
            localDataStore.getId().collect {
                if (AppUtils.isOnline(requireContext())) {
                    albumViewModel.getAlbumFromResponse(it)
                };
            }
        }
        albumViewModel.albumList.observe(requireActivity(), Observer {
            albumViewModel.insertAlbumList(it)
        })
        albumViewModel.getAllAlbum().observe(requireActivity(), Observer {
            binding.recyclerView.apply {
                layoutManager = manager
                adapter = AlbumAdapter(it);
            }
        })
        return binding.root;
    }
}