package cn.cxy.wanandroid.modules

import android.os.Bundle
import androidx.fragment.app.Fragment
import cn.cxy.wanandroid.R
import cn.cxy.wanandroid.base.BaseActivity
import cn.cxy.wanandroid.modules.mine.MineFragment
import cn.cxy.wanandroid.modules.recommend.ChapterListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {
    private var lastIndex = 0
    private var mFragments = mutableListOf<Fragment>()

    override fun setLayoutId() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        initBottomNavigation()
    }

    override fun initData() {
        mFragments = ArrayList()
        mFragments.add(ChapterListFragment())
        mFragments.add(MineFragment())
        // 初始化展示MessageFragment
        setFragmentPosition(0)
    }

    private fun initBottomNavigation() {
        bottomNavigationView.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> setFragmentPosition(0)
                R.id.menu_mine -> setFragmentPosition(1)
                else -> {
                }
            }
            true
        })
    }

    private fun setFragmentPosition(position: Int) {
        val ft = supportFragmentManager.beginTransaction()
        val currentFragment = mFragments[position]
        val lastFragment = mFragments[lastIndex]
        lastIndex = position
        ft.hide(lastFragment)
        if (!currentFragment.isAdded) {
            supportFragmentManager.beginTransaction().remove(currentFragment).commit()
            ft.add(R.id.ll_frameLayout, currentFragment)
        }
        ft.show(currentFragment)
        ft.commitAllowingStateLoss()
    }
}