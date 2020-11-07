package cn.cxy.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_news.*

class NewsListFragment : Fragment() {
    private var urlList = mutableListOf<String>()
    private var homeImageListAdapter: HomeImageListAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        queryData()
        setListeners()
        fragmentManager?.let { viewPager.adapter = MainPagerAdapter(it) }
    }

    private fun setListeners() {

    }


    private fun queryData() {
//        val networkService = getNetworkService()
//        MainScope().launch(Dispatchers.Main) {
//            val result = withContext(Dispatchers.IO) { networkService.query() }
//            result.split("\n").forEach { urlList.add(it) }
//            activity?.let {
//                homeImageListAdapter = HomeImageListAdapter(it, urlList)
//                vp2.adapter = homeImageListAdapter
//            }
//        }
    }

//    private fun getNetworkService(): NetworkService {
//        val okHttpClient = OkHttpClient.Builder().build()
//        val retrofit = Retrofit.Builder()
//            .client(okHttpClient)
//            .baseUrl("https://gitee.com/")
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .build()
//
//        return retrofit.create(NetworkService::class.java)
//    }

    //ViewPager适配器  10个Fragment
    private inner class MainPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return BlankFragment.newInstance(position)
        }

        //TabLayout会根据当前page的title自动绑定tab
        override fun getPageTitle(position: Int): CharSequence? {
            return "Tab $position"
        }

        override fun getCount(): Int {
            return 10
        }
    }
}