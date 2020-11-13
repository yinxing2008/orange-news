package cn.cxy.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cn.cxy.news.network.ArticleRepo
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import kotlinx.android.synthetic.main.fragment_article_list.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class ArticleListFragment : Fragment() {
    private val articleRepo = ArticleRepo()
    private var chapterId = 0
    private val adapter = ArticleAdapter()
    private val initPageNum = 1
    private var pageNum = initPageNum
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
        refreshLayout.setRefreshHeader( BezierRadarHeader(context).setEnableHorizontalDrag(true));
        refreshLayout.setRefreshFooter( BallPulseFooter(context).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener { loadData() }
        refreshLayout.setOnLoadMoreListener { loadMoreData() }
        refreshLayout.autoLoadMore()
    }

    private fun loadData() {
        MainScope().launch {
            pageNum = initPageNum
            refreshLayout.resetNoMoreData()
            val response = articleRepo.getArticle(chapterId, pageNum)
            if (response.isSuccess()) {
                response.data?.let {
                    adapter.setData(it.datas)
                    if (pageNum >= it.total) {
                        refreshLayout.setNoMoreData(true)
                    } else {
                        pageNum++
                    }
                }
            }
            refreshLayout.finishRefresh()
        }
    }
    private fun loadMoreData() {
        MainScope().launch {
            val response = articleRepo.getArticle(chapterId, pageNum)
            if (response.isSuccess()) {
                response.data?.let {
                    adapter.addData(it.datas)
                    if (pageNum >= it.total) {
                        refreshLayout.setNoMoreData(true)
                    } else {
                        pageNum++
                    }
                }
            }
            refreshLayout.finishLoadMore()
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