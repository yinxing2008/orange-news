package cn.cxy.wanandroid.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("wxarticle/chapters/json")
    suspend fun getChapters(): ChapterResp

    @GET("wxarticle/list/{chapterId}/{pageNum}/json")
    suspend fun getArticle(
        @Path("chapterId") chapterId: Int,
        @Path("pageNum") pageNum: Int
    ): ArticleResp
}