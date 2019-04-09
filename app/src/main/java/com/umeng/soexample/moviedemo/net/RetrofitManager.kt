package com.wd.demo.net

import com.umeng.soexample.moviedemo.BuildConfig
import com.umeng.soexample.moviedemo.net.RxUtils
import com.wd.demo.api.MyApi.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class RetrofitManager private constructor() {
    private var retrofit: Retrofit = init(BASE_URL)
    val DEFAULT_TIME_OUT: Long = 5
    val DEFAULT_REDA_TIME_OUT: Long = 10


    companion object {
        val instance: RetrofitManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitManager()
        }
    }

    fun init(baseUrl: String): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                ))
                .readTimeout(DEFAULT_REDA_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_REDA_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .sslSocketFactory(RxUtils.createSSLSocketFactory())
                .build()
        return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
    }

    fun <T> createSerivce(tClass: Class<T>): T {
        return retrofit.create(tClass)
    }
}