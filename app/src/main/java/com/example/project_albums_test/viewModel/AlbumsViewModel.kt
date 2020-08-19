package com.example.project_albums_test.viewModel

import androidx.lifecycle.ViewModel
import com.example.project_albums_test.model.AlbumModel
import com.example.project_albums_test.service.AlbumsService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class AlbumsViewModel : ViewModel() {


    private val service: AlbumsService =
        AlbumsService()
    private var albumList: ArrayList<AlbumModel>? = null
    private var photoSubject: BehaviorSubject<AlbumModel> =
        BehaviorSubject.create()

    fun getAlbumsSingle(isFetch: Boolean = true): Single<ArrayList<AlbumModel>> {
        return if (isFetch || albumList == null) {
            service.getAlbumsSingle().doOnSuccess {
                this.albumList = it
            }
        } else {
            Single.just(albumList)
        }
    }

    fun setPhoto(id: String, albumList: ArrayList<AlbumModel>) {
        val model = albumList.find { it.id == id }
        if (model != null) {
            photoSubject.onNext(model)
        } else {
            photoSubject.onError(Exception("Not found Data"))
        }
    }

    fun getPhoto(): Observable<AlbumModel> {
        return photoSubject
    }
}