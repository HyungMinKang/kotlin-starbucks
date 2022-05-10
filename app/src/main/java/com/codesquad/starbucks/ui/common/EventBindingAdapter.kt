package com.codesquad.starbucks.ui.common

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("eventDescription")
fun getDescription(view: TextView, description: String?) {
    val splitResult = description?.split("**")
    val res = splitResult?.let {
        "${it[0]} <strong>${it[1]}<strong> <br> ${it[2]} <strong>${it[3]}</strong> ${it[4]}"
    }
    view.text= res?.htmlToString()

}