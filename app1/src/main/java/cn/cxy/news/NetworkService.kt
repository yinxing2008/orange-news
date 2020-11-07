package cn.cxy.news

import retrofit2.http.GET

interface NetworkService {
    @GET("cxyzy1/browse-beauty/raw/master/imageUrl.txt")
    suspend fun query(): String
}