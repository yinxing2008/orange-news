package cn.cxy.news

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeImageListAdapter(fragmentActivity: FragmentActivity, var urlList: MutableList<String>) :
    FragmentStateAdapter(fragmentActivity) {
    private var fragments = mutableListOf<HomeImageFragment>()

    override fun createFragment(position: Int): HomeImageFragment {
        val fragment = HomeImageFragment(urlList[position])
        fragments.add(fragment)
        return fragment
    }

    override fun getItemCount() = urlList.size

    fun getFragment(index: Int) = fragments[index]
}