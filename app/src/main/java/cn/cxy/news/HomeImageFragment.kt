package cn.cxy.news

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.github.chrisbanes.photoview.PhotoView

class HomeImageFragment(var url: String) : Fragment() {
    private lateinit var mImageView: ImageView
    private var mBitmap: Bitmap? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val imageView = PhotoView(context)
        imageView.setBackgroundColor(Color.parseColor("#000000"))
        mImageView = imageView
        Glide.with(this).asBitmap().load(url)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    imageView.setImageBitmap(resource)
                    mBitmap = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
        return imageView
    }

    fun getImage() = mBitmap
}