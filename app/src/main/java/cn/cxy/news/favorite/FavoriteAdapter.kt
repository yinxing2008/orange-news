package cn.cxy.news.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import cn.cxy.news.R
import cn.cxy.news.db.bean.ImageInfo
import com.bumptech.glide.Glide
import com.cxyzy.utils.ext.show
import kotlinx.android.synthetic.main.item_favorite_list.view.*

class FavoriteAdapter : RecyclerView.Adapter<ViewHolder>() {
    private var mDataList = mutableListOf<ImageInfo>()
    private lateinit var mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_favorite_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = mDataList[position]
        holder.itemView.iv.visibility = INVISIBLE
        showImage(data, holder.itemView.iv, position)
    }

    private fun showImage(
        data: ImageInfo,
        view: ImageView,
        positionInImageList: Int
    ) {
        Glide.with(mContext).load(data.path).into(view)
        view.setOnClickListener {
            mContext.startActivity(ImageActivity.buildIntent(mContext, data, positionInImageList))
        }
        view.show()
    }

    fun setData(dataList: List<ImageInfo>) {
        mDataList.clear()
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mDataList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}