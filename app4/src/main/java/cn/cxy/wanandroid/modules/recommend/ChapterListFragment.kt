package cn.cxy.wanandroid.modules.recommend

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import cn.cxy.wanandroid.R
import cn.cxy.wanandroid.base.BaseFragment
import cn.cxy.wanandroid.network.ArticleRepo
import cn.cxy.wanandroid.network.ChapterResp
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.fragment_chapter.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChapterListFragment : BaseFragment() {
    private val chapterRepo = ArticleRepo()

    override fun setLayoutResId() = R.layout.fragment_chapter

    override fun initView() {
        immersionBar {
            statusBarColor(R.color.colorPrimary)
            navigationBarColor(R.color.colorPrimary)
            fitsSystemWindows(true)
        }
    }

    override fun initData() {
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
            return ArticleListFragment.newInstance(dataList[position].id)
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