package cn.cxy.wanandroid.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import cn.cxy.wanandroid.R
import kotlinx.android.synthetic.main.view_search.view.*

class SearchView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private var text: String? = null

    init {
        initTypeValue(context, attrs)
        initView(context)
    }

    private fun initTypeValue(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchView)
        text = typedArray.getString(R.styleable.SearchView_text)
        typedArray.recycle()
    }

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.view_search, this, true)
        setData(text)
    }

    fun setData(text: String?) = text?.let { editText.setText(it) }

    fun getData() = editText.text.toString()
}