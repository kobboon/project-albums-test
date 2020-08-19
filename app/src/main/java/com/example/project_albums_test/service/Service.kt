package com.example.project_albums_test.service


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part

interface Service {

    @GET("albums/1/photos")
    fun getAlbums(): Call<ResponseBody>

}