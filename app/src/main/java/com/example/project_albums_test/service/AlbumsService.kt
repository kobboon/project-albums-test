package com.example.project_albums_test.service

import android.util.Log
import com.example.project_albums_test.model.AlbumModel
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumsService {

    fun getAlbumsSingle(): Single<ArrayList<AlbumModel>> {

        return Single.create {
            val service =
                BuildServiceRetrofit.create()
                    .getAlbums()
//            Log.d("<S", "===url===>${service.request().url()}")
            service.enqueue(
                object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        it.onError(t)
                    }

                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            val res = response.body()?.string()
//                            Log.d("<S", "=======>${res}")
                            val model =   Gson().fromJson(res , Array<AlbumModel>::class.java).toCollection(ArrayList())
                            it.onSuccess(model)
                        } else {
                            it.onError(Exception(response.errorBody()?.string()))
                        }

                    }

                })
        }


//        return BuildServiceRetrofit.create().getAlbums()
//            .subscribeOn(Schedulers.io())
//            .map {
//

//        }

    }

}