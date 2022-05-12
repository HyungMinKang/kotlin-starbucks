package com.codesquad.starbucks.ui.common

import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.size.Scale


@BindingAdapter("getMainEvent")
fun getMainEventImage(view: ImageView, imageUri: String?) {
    view.load(imageUri) {
        scale(Scale.FIT)
    }
}

@BindingAdapter("welcomeMessage")
fun setWelcomeMessage(view: TextView, userName: String?) {
    view.text = Html.fromHtml("<font color=\"#EEB0B0\">${userName}</font> 님을 위한 추천메뉴")
}