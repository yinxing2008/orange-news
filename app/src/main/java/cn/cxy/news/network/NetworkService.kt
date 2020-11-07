package cn.cxy.news.network

import retrofit2.http.GET

interface NetworkService {
    @GET("wxarticle/chapters/json")
    suspend fun getChapters(): ChapterResp

    @GET("wxarticle/list/408/1/json")
    suspend fun getArticle(): ArticleResp

}