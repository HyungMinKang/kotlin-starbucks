package com.codesquad.starbucks.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.CircleCropTransformation


@BindingAdapter("getImage")
fun getPersonalRecommendImage(view: ImageView, imageUri: String?) {
    view.load(imageUri) {
        transformations(CircleCropTransformation())
    }
}