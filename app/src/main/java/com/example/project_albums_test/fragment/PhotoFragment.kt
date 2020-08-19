package com.example.project_albums_test.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.project_albums_test.R
import com.example.project_albums_test.viewModel.AlbumsViewModel
import com.example.project_albums_test.viewModel.ViewModelProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_photo.view.*

class PhotoFragment : Fragment() {

    private val albumsViewModel by lazy {
        ViewModelProvider.getType(
            AlbumsViewModel::class.java) as AlbumsViewModel
    }

    private val compositeDisposable = CompositeDisposable()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val id = it.getString("id")
            id?.let { it1 -> getAlbums(it1) }
            getPhoto()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }

    private fun getAlbums(id: String) {
        albumsViewModel.getAlbumsSingle(false)
            .subscribeBy(
                onSuccess = { albumsViewModel.setPhoto(id, it) },
                onError = { it.printStackTrace() }
            )
            .addTo(compositeDisposable)
    }

    private fun getPhoto() {
        albumsViewModel.getPhoto()
            .subscribeBy(
                onNext = {
                    Glide.with(this.view!!.img)
                        .asBitmap()
                        .load(it.thumbnailUrl)
                        .centerCrop()
                        .placeholder(R.drawable.drawable_loading_progress)
                        .error(android.R.drawable.stat_notify_error)
                        .into(this.view!!.img)
                },
                onError = { it.printStackTrace() }
            )
            .addTo(compositeDisposable)
    }


}