package com.example.project_albums_test.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object BuildServiceRetrofit {

    fun create(): Service {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create<Service>(
            Service::class.java)
    }
}