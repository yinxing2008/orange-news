package cn.cxy.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cn.cxy.news.network.ArticleResp
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mDataList = mutableListOf<ArticleResp.Articles.Article>()
    private lateinit var mContext: Context
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = mDataList[position]
        holder.itemView.textView.text = data.title
        holder.itemView.setOnClickListener {
            setOnItemClickListener(data)
        }
    }

    fun setData(dataList: List<ArticleResp.Articles.Article>) {
        mDataList.clear()
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mDataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    private fun setOnItemClickListener(data: ArticleResp.Articles.Article) {
        Toast.makeText(mContext, data.title, Toast.LENGTH_SHORT).show()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

}