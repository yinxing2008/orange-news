package cn.cxy.news.network

import retrofit2.http.GET

interface NetworkService {
    @GET("wxarticle/chapters/json")
    suspend fun queryChapters(): Chapters
}