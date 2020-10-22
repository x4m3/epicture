package com.philippeloctaux.epicture.api

import com.philippeloctaux.epicture.api.types.ImageListResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface Imgur {
    companion object {
        fun create(): Imgur {
            val builder = Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
            val retrofit = builder.build()
            return retrofit.create(Imgur::class.java)
        }
    }

    @GET("account/me/images")
    fun getAccountImages(@Header("Authorization") accessToken: String): Call<ImageListResponse>
}