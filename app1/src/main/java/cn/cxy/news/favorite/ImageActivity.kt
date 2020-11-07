package cn.cxy.news.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.cxy.news.R
import cn.cxy.news.db.bean.ImageInfo
import cn.cxy.news.db.repository.ImageInfoRepository
import cn.cxy.news.utils.EXTRA_IMAGE_INFO
import cn.cxy.news.utils.EXTRA_IMAGE_INFO_POSITION
import kotlinx.android.synthetic.main.activity_image.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ImageActivity : AppCompatActivity() {
    private var mPositionInList = 0

    companion object {
        fun buildIntent(context: Context, imageInfo: ImageInfo, positionInImageList: Int): Intent {
            val intent = Intent(context, ImageActivity::class.java)
            intent.putExtra(EXTRA_IMAGE_INFO, imageInfo)
            intent.putExtra(EXTRA_IMAGE_INFO_POSITION, positionInImageList)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val imageInfo = intent.getParcelableExtra<ImageInfo>(EXTRA_IMAGE_INFO)
        mPositionInList = intent.getIntExtra(EXTRA_IMAGE_INFO_POSITION, 0)
        initViews(imageInfo)
    }

    private fun initViews(imageInfo: ImageInfo?) {
        MainScope().launch {
            vp.adapter =
                ImageListAdapter(this@ImageActivity, ImageInfoRepository.list().toMutableList())
            vp.currentItem = mPositionInList
        }
    }
}

