package com.alamin.placeholder.view.fragment

import android.os.Bundle
import android.util.Log
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
import com.alamin.placeholder.R
import com.alamin.placeholder.databinding.FragmentGalleryBinding
import com.alamin.placeholder.model.data.Album
import com.alamin.placeholder.utils.AppUtils
import com.alamin.placeholder.utils.LocalDataStore
import com.alamin.placeholder.view.adapter.AlbumAdapter
import com.alamin.placeholder.view.adapter.AlbumClickListener
import com.alamin.placeholder.view_model.AlbumViewModel
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.coroutines.flow.collect

private const val TAG = "AlbumFragment"
class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding;
    lateinit var manager: RecyclerView.LayoutManager;
    lateinit var localDataStore: LocalDataStore;
    lateinit var albumViewModel: AlbumViewModel;
    lateinit var adapter: AlbumAdapter;

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
            binding.recyclerView.layoutManager = manager
            adapter = AlbumAdapter(it);
            adapter.setOnClickItem(object : AlbumClickListener{
                override fun onItemClicked(album: Album) {
                    var action = GalleryFragmentDirections.actionGalleryFragmentToPhotoFragment(album)
                   findNavController().navigate(action);
                }
            })
            binding.recyclerView.adapter = adapter;
           /* binding.recyclerView.apply {
                layoutManager = manager
                adapter = AlbumAdapter(it);

            }*/
        })
        return binding.root;
    }
}