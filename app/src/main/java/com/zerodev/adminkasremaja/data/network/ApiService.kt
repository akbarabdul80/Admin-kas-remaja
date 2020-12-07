package com.zerodev.adminkasremaja.data.network

import com.zerodev.adminkasremaja.BuildConfig
import com.zerodev.adminkasremaja.data.model.login.ResponseLogin
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {
    private val api : ApiEndpoint

    init {
        val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else
                    HttpLoggingInterceptor.Level.NONE
            })
            .addInterceptor( Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.header("X-API-KEY", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9")
                return@Interceptor chain.proceed(builder.build())
            })
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()


        val server = "http://192.168.43.73/kas/api/"
        api = Retrofit.Builder()
            .baseUrl(server)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .build()
            .create(ApiEndpoint::class.java)
    }

    fun postLogin(username : String, password : String) : Single<ResponseLogin> {
        return api.login(username, password)
    }

}