package cn.cxy.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 *@创建者wwy
 *@创建时间 2019/10/9 11:10
 *@描述
 */
abstract class BaseFragment : Fragment() {
    //    private lateinit var progressDialogFragment: ProgressDialogFragment
    private var isLoaded = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(setLayoutResId(), container, false)
    }

    override fun onResume() {
        super.onResume()
        if (!isLoaded && !isHidden) {
            onFragmentFirstVisible()
            isLoaded = true
        }
    }

    protected open fun onFragmentFirstVisible() {
        initView()
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun setLayoutResId(): Int

    abstract fun initView()

    abstract fun initData()

    /**
     * 显示加载(转圈)对话框
     */
    fun showProgressDialog(@StringRes message: Int) {
//        if (!this::progressDialogFragment.isInitialized) {
//            progressDialogFragment = ProgressDialogFragment.newInstance()
//        }
//        if (!progressDialogFragment.isAdded) {
//            activity?.supportFragmentManager?.let { progressDialogFragment.show(it, message, false) }
//        }
    }

    /**
     * 隐藏加载(转圈)对话框
     */
    fun dismissProgressDialog() {
//        if (this::progressDialogFragment.isInitialized && progressDialogFragment.isVisible) {
//            progressDialogFragment.dismissAllowingStateLoss()
//        }
    }

}