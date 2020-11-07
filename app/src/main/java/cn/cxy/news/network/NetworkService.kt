package cn.cxy.news.network

import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET("wxarticle/chapters/json")
    suspend fun getChapters(): ChapterResp

    @GET("wxarticle/list/{chapterId}/{pageNum}/json")
    suspend fun getArticle(
        @Path("chapterId") chapterId: Int,
        @Path("pageNum") pageNum: Int
    ): ArticleResp
}