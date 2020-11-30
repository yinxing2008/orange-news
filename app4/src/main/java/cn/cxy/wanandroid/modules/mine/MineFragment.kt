package cn.cxy.wanandroid.modules.mine

import cn.cxy.wanandroid.R
import cn.cxy.wanandroid.base.BaseFragment
import com.gyf.immersionbar.ktx.immersionBar

class MineFragment : BaseFragment() {
    override fun setLayoutResId() = R.layout.activity_mine_home

    override fun initView() {
        immersionBar {
            statusBarColor(R.color.white)
            navigationBarColor(R.color.white)
            fitsSystemWindows(true)
            statusBarDarkFont(true, 0.2f)
        }
    }

    override fun initData() {}
}