package cn.cxy.news.favorite

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import cn.cxy.news.R
import cn.cxy.news.db.bean.ImageInfo
import cn.cxy.news.db.repository.ImageInfoRepository
import cn.cxy.news.utils.ImageUtil
import com.bumptech.glide.Glide
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ImageListAdapter(
    private val activity: Activity,
    private val imageInfoList: MutableList<ImageInfo>
) : PagerAdapter() {
    override fun getCount() = imageInfoList.size

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val root = View.inflate(activity, R.layout.item_image, null)
        container.addView(
            root, ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        val photoView = root.findViewById<ImageView>(R.id.photoView)
        photoView.setBackgroundColor(Color.parseColor("#000000"))
        val imageInfo = imageInfoList[position]
        Glide.with(activity).load(imageInfo.path).into(photoView)

        initListeners(root, imageInfo, position)
        return root
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    private fun initListeners(rootView: View, imageInfo: ImageInfo, position: Int) {
        rootView.findViewById<ImageView>(R.id.favoriteIcon).setOnClickListener {
            delImageFromFavorite(imageInfo, position)
        }
    }

    private fun delImageFromFavorite(imageInfo: ImageInfo, position: Int) {
        MainScope().launch {
            ImageUtil.deleteFile(imageInfo.path)
            ImageInfoRepository.del(imageInfo.url)
            imageInfoList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    /**
     * 覆写此函数，才能使得删除数据后UI及时刷新
     */
    override fun getItemPosition(obj: Any): Int {
        return POSITION_NONE
    }
}