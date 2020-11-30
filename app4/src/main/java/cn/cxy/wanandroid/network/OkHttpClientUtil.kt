package cn.cxy.wanandroid.network

import cn.cxy.wanandroid.network.interceptor.HttpLogInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpClientUtil {
    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .apply {
                    addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                    addInterceptor(HttpLogInterceptor())
                }.build()
    }
}
