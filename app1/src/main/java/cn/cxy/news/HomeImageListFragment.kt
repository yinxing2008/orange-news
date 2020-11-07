package cn.cxy.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import cn.cxy.news.db.repository.ImageInfoRepository
import cn.cxy.news.utils.ImageUtil.deleteFile
import cn.cxy.news.utils.ImageUtil.saveBitmap
import kotlinx.android.synthetic.main.fragment_image.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class HomeImageListFragment : Fragment() {
    private var urlList = mutableListOf<String>()
    private var homeImageListAdapter: HomeImageListAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        queryData()
        //设置上下滑动
        vp2.orientation = ViewPager2.ORIENTATION_VERTICAL
        setListeners()
    }

    private fun setListeners() {
        vp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateFavoriteIv(position)
            }
        })
        favoriteIcon.setOnClickListener {
            val imageFragment = homeImageListAdapter?.getFragment(vp2.currentItem)
            imageFragment?.let {
                if (favoriteIcon.isChecked) {
                    addImageToFavorite(it)
                } else {
                    delImageFromFavorite(it)
                }
            }
        }
    }

    private fun delImageFromFavorite(it: HomeImageFragment) {
        MainScope().launch {
            val imageInfo = ImageInfoRepository.queryByUrl(it.url)
            imageInfo?.let {
                deleteFile(it.path)
                ImageInfoRepository.del(it.url)
            }
        }
    }

    private fun addImageToFavorite(it: HomeImageFragment) {
        val image = it.getImage()
        if (context != null && image != null) {
            MainScope().launch(Dispatchers.IO) {
                val filePath = saveBitmap(context!!, image)
                ImageInfoRepository.add(filePath, it.url)
            }
        }
    }

    private fun updateFavoriteIv(position: Int) {
        getFragment(position)?.let {
            MainScope().launch {
                favoriteIcon.isChecked = ImageInfoRepository.exists(it.url)
            }
        }
    }

    private fun getFragment(position: Int) = homeImageListAdapter?.getFragment(position)

    private fun queryData() {
        val networkService = getNetworkService()
        MainScope().launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { networkService.query() }
            result.split("\n").forEach { urlList.add(it) }
            activity?.let {
                homeImageListAdapter = HomeImageListAdapter(it, urlList)
                vp2.adapter = homeImageListAdapter
            }
        }
    }

    private fun getNetworkService(): NetworkService {
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://gitee.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        return retrofit.create(NetworkService::class.java)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            updateFavoriteIv(vp2.currentItem)
        }
    }
}