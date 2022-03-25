package com.alamin.placeholder.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.alamin.placeholder.R
import com.alamin.placeholder.utils.LocalDataStore
import kotlinx.coroutines.flow.collect

private const val TAG = "HomeFragment"
class HomeFragment : Fragment() {
    lateinit var localDataStore: LocalDataStore;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "CHECK_LOGIN "+" Home Fragment")

        localDataStore = LocalDataStore(requireContext())
        lifecycleScope.launchWhenCreated {
            localDataStore.getName().collect {
                Log.d(TAG, "CHECK_LOGIN "+"Store Data In Home "+it)
                if (it !=-1){
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}