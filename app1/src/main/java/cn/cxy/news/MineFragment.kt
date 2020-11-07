package cn.cxy.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.cxy.news.db.repository.ImageInfoRepository
import cn.cxy.news.favorite.FavoriteListActivity
import kotlinx.android.synthetic.main.mine_part_favorite.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MineFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        favoriteLayout.setOnClickListener {
            startActivity(Intent(context, FavoriteListActivity::class.java))
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            changeFavoriteTv()
        }
    }

    override fun onResume() {
        super.onResume()
        changeFavoriteTv()
    }

    private fun changeFavoriteTv() {
        MainScope().launch {
            val favoriteCount = ImageInfoRepository.list().size
            if (favoriteCount > 0) {
                favoriteTv.text = "收藏($favoriteCount)"
            } else {
                favoriteTv.text = "收藏"
            }
        }
    }
}