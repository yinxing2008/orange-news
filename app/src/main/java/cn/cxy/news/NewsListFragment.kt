package cn.cxy.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import cn.cxy.news.network.ArticleRepo
import cn.cxy.news.network.ChapterResp
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsListFragment : Fragment() {
    private val chapterRepo = ArticleRepo()
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
        MainScope().launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { chapterRepo.getChapters() }
            if (result.isSuccess()) {
                if (fragmentManager != null && result.data != null) {
                    viewPager.adapter = MainPagerAdapter(fragmentManager!!, result.data!!)
                }
            }
        }
    }

    //ViewPager适配器  10个Fragment
    private inner class MainPagerAdapter(
        fm: FragmentManager,
        var dataList: List<ChapterResp.Chapter>
    ) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return BlankFragment.newInstance(dataList[position].id)
        }

        //TabLayout会根据当前page的title自动绑定tab
        override fun getPageTitle(position: Int): CharSequence? {
            return dataList[position].name
        }

        override fun getCount(): Int {
            return dataList.size
        }
    }
}