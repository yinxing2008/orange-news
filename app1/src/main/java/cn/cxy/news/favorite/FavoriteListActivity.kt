package cn.cxy.news.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import cn.cxy.news.R
import cn.cxy.news.db.repository.ImageInfoRepository
import kotlinx.android.synthetic.main.activity_favorite_list.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class FavoriteListActivity : AppCompatActivity() {
    private val adapter = FavoriteAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_list)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        favoriteRv.adapter = adapter
        favoriteRv.layoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        MainScope().launch {
            adapter.setData(ImageInfoRepository.list())
        }
    }
}