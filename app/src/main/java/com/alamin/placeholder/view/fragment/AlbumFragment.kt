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
import com.alamin.placeholder.databinding.FragmentGalleryBinding
import com.alamin.placeholder.model.data.Album
import com.alamin.placeholder.utils.AppUtils
import com.alamin.placeholder.utils.LocalDataStore
import com.alamin.placeholder.view.adapter.AlbumAdapter
import com.alamin.placeholder.view.adapter.AlbumClickListener
import com.alamin.placeholder.view_model.AlbumViewModel
import com.alamin.placeholder.view_model.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_gallery.*
import javax.inject.Inject

private const val TAG = "AlbumFragment"
class AlbumFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory;
    @Inject
    lateinit var albumAdapter: AlbumAdapter;
    private lateinit var binding: FragmentGalleryBinding;
    lateinit var localDataStore: LocalDataStore;
    private lateinit var albumViewModel: AlbumViewModel;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryBinding.inflate(layoutInflater);
        val component = (requireActivity().applicationContext as PlaceHolderApplication).appComponent
        component.injectAlbum(this)
        albumViewModel = ViewModelProvider(this,viewModelFactory).get(AlbumViewModel::class.java);
        localDataStore = LocalDataStore(requireContext());
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


        with(albumAdapter){
            setOnClickItem(object : AlbumClickListener{
                override fun onItemClicked(album: Album) {
                    var action = AlbumFragmentDirections.actionGalleryFragmentToPhotoFragment(album)
                    findNavController().navigate(action);
                }
            })

            albumViewModel.getAllAlbum().observe(requireActivity(), Observer {
                setData(it)
            })
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext());
            adapter = albumAdapter;
        }

        return binding.root;
    }
}