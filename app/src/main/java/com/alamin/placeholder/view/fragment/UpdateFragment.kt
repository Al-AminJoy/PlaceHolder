package com.alamin.placeholder.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.alamin.placeholder.R

private const val TAG = "UpdateFragment"
class UpdateFragment : Fragment() {
    private val arg by navArgs<UpdateFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "PASSED_POST "+arg.post)
        return inflater.inflate(R.layout.fragment_update, container, false)
    }
}