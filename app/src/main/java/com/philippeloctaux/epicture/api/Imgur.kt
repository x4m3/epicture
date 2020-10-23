package com.philippeloctaux.epicture.api

import com.philippeloctaux.epicture.api.types.GalleryListResponse
import com.philippeloctaux.epicture.api.types.ImageListResponse
import com.philippeloctaux.epicture.api.types.ImageResponse
import com.philippeloctaux.epicture.api.types.UploadResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

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

    @GET("image/{hash}")
    fun getImage(
        @Header("Authorization") clientId: String,
        @Path("hash", encoded = true) hash: String,
    ): Call<ImageResponse>

    @GET("gallery/hot/viral/0.json")
    fun getHomePage(
        @Header("Authorization") clientId: String,
    ): Call<GalleryListResponse>

    @GET("account/me/favorites")
    fun getFavoritesImages(
        @Header("Authorization") accessToken: String,
    ): Call<ImageListResponse>

    @Multipart
    @POST("upload")
    fun uploadImage(
        @Header("Authorization") accessToken: String,
        @Part image: MultipartBody.Part,
        @Part type: String,
        @Part title: String,
        @Part description: String
    ): Call<UploadResponse>
}