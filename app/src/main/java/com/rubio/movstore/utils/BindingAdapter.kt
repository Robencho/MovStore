package com.rubio.movstore.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.rubio.movstore.R
import com.squareup.picasso.Picasso

@BindingAdapter("showImgUrl")
fun loadImgPicasso(view:ImageView, url:String?){
    Picasso.get().load(MovStoreConstants.URL_IMAGES.plus(url))
        .placeholder(R.drawable.ic_splash)
        .into(view)
}