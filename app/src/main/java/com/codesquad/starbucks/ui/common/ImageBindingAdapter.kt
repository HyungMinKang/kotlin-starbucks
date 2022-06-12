package com.codesquad.starbucks.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.codesquad.starbucks.R


@BindingAdapter("getMainEvent")
fun getMainEventImage(view: ImageView, imageUri: String?) {
    view.load(imageUri) {
        scale(Scale.FIT)
    }
}

@BindingAdapter("HomeEventImage")
fun getHomeEventImage(view: ImageView, imageUri: String?) {
    view.load(imageUri) {
    }
}

@BindingAdapter("getImage")
fun getPersonalRecommendImage(view: ImageView, imageUri: String?) {
    view.load(imageUri) {
        transformations(CircleCropTransformation())
        placeholder(R.drawable.ic_baseline_downloading_24)
    }
}