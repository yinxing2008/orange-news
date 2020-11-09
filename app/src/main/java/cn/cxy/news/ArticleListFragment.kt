package cn.cxy.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cn.cxy.news.network.ArticleRepo
import kotlinx.android.synthetic.main.fragment_article_list.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ArticleListFragment : Fragment() {
    private val articleRepo = ArticleRepo()
    private var chapterId = 0
    private val adapter = ArticleAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_article_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        initSwipeRefreshLayout()
        loadData()
    }

    private fun initSwipeRefreshLayout() {
        //设置下拉刷新转圈的颜色
//        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN)
        swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        MainScope().launch {
            swipeRefreshLayout.isRefreshing = true
            val response = articleRepo.getArticle(chapterId, 1)
            if (response.isSuccess()) {
                response.data?.datas?.let { adapter.setData(it) }
            }
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initRecyclerView() {
        recyclerView.adapter = adapter
        //设置布局类型
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        fun newInstance(chapterId: Int): Fragment {
            val fragment = ArticleListFragment()
            fragment.chapterId = chapterId
            return fragment
        }
    }
}