package com.zerodev.adminkasremaja.data.network

import com.zerodev.adminkasremaja.data.model.login.ResponseLogin
import okhttp3.ResponseBody
import retrofit2.Call
import io.reactivex.Single
import retrofit2.http.*


interface ApiEndpoint {

    /**
     * Login Api
     */
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Single<ResponseLogin>

}