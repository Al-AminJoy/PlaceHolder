package com.alamin.placeholder.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgument
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alamin.placeholder.PlaceHolderApplication
import com.alamin.placeholder.R
import com.alamin.placeholder.databinding.FragmentPhotoBinding
import com.alamin.placeholder.utils.AppUtils
import com.alamin.placeholder.view.adapter.PhotoAdapter
import com.alamin.placeholder.view_model.PhotoViewModel
import com.alamin.placeholder.view_model.PhotoViewModelFactory
import javax.inject.Inject

private const val TAG = "PhotoFragment"

class PhotoFragment : Fragment() {
    @Inject
    lateinit var photoViewModelFactory: PhotoViewModelFactory;
    @Inject
    lateinit var photoAdapter: PhotoAdapter ;
    private lateinit var binding: FragmentPhotoBinding;
    private lateinit var photoViewModel: PhotoViewModel;
    private val args by navArgs<PhotoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoBinding.inflate(layoutInflater);
        val component = (requireActivity().applicationContext as PlaceHolderApplication).appComponent
        component.injectPhoto(this)
        photoViewModel = ViewModelProvider(this,photoViewModelFactory).get(PhotoViewModel::class.java);
        if (AppUtils.isOnline(requireContext())) {
            photoViewModel.getAllPhotoFromResponse(args.album.id)
        }
        photoViewModel.photoList.observe(requireActivity(),
            Observer {
                photoViewModel.insertPhotoList(it);
            })

        with(photoAdapter){
            photoViewModel.getAllPhoto().observe(requireActivity(), Observer {
                setData(it)
            })
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext());
            adapter = photoAdapter
        }

        return binding.root
    }

}