package cn.cxy.wanandroid.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseRepository {
    val apiService: ApiService by lazy {
        val okHttpClient = OkHttpClient.Builder().build()
         Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ApiService::class.java)
    }
}