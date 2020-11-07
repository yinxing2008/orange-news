package cn.cxy.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import cn.cxy.news.network.NetworkService
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsListFragment : Fragment() {
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
    }

    private fun queryData() {
        val networkService = getNetworkService()
        MainScope().launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { networkService.queryChapters() }
            if(result.isSuccess())
            {
                fragmentManager?.let { viewPager.adapter = MainPagerAdapter(it,result.dataList) }
            }
        }
    }

    private fun getNetworkService(): NetworkService {
        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        return retrofit.create(NetworkService::class.java)
    }

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