package com.alamin.placeholder.view.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("setImage")
fun ImageView.setImage(photoUrl: String) {
    this.load(photoUrl)
}