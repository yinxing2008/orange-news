package cn.cxy.wanandroid.network.interceptor

import cn.cxy.wanandroid.utils.OkHttpUrl.LOGIN_URL
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * okhttp基础拦截器，对指定接口不做拦截
 */

abstract class BaseInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return if (shouldIntercept(request)) {
            interceptMe(chain)
        } else {
            chain.proceed(request)
        }
    }

    private fun shouldIntercept(request: Request) = !request.url().url().path.endsWith(LOGIN_URL)
    abstract fun interceptMe(chain: Interceptor.Chain): Response
}
