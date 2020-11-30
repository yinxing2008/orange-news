package cn.cxy.wanandroid.modules

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import cn.cxy.wanandroid.R
import cn.cxy.wanandroid.base.BaseActivity
import cn.cxy.wanandroid.utils.EXTRA_URL
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.webview_top_layout.*

class WebViewActivity : BaseActivity() {

    companion object {
        fun buildIntent(context: Context, url: String): Intent {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra(EXTRA_URL, url)
            return intent
        }
    }

    override fun setLayoutId() = R.layout.activity_webview

    override fun initView(savedInstanceState: Bundle?) {
        immersionBar {
            statusBarColor(R.color.white)
            navigationBarColor(R.color.white)
            fitsSystemWindows(true)
            statusBarDarkFont(true, 0.2f)
        }
        intent.getStringExtra(EXTRA_URL)?.let {
            setSettings()
            webView.webViewClient = WebViewClient()
            webView.loadUrl(it)
        }
    }

    override fun initListeners() {
        backIv.setOnClickListener { finish() }
    }
    override fun initData() {}

    private fun setSettings() {
        val settings = webView.settings
        settings.javaScriptEnabled = true//设置WebView属性，能够执行Javascript脚本
//        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        settings.allowFileAccess = true //设置可以访问文件
        settings.builtInZoomControls = false //设置支持缩放
        settings.setSupportZoom(true)
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.setAppCacheEnabled(true)
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
    }
}