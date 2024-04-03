package com.example.finalprodproject.utils.image_loader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

object ImageLoader {

    fun loadImage(
        imageView: ImageView,
        link: String,
        context: Context = imageView.context
    ) {
        Glide.with(context).load(link)
            .format(DecodeFormat.PREFER_RGB_565)
            .override(imageView.width, imageView.height)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .centerCrop()
            .into(imageView)
    }

    fun loadImageWithRoundCorners(
        imageView: ImageView,
        link: String,
        radius: Int,
        context: Context = imageView.context
    ) {
        Glide.with(context).load(link)
            .format(DecodeFormat.PREFER_RGB_565)
            .override(imageView.width, imageView.height)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .transform(CenterCrop(), RoundedCorners(radius))
            .into(imageView)
    }

}