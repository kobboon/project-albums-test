package com.example.project_albums_test.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.project_albums_test.R
import com.example.project_albums_test.adapter.PhotoAdapter
import com.example.project_albums_test.model.AlbumModel
import com.example.project_albums_test.util.ManageFragment
import com.example.project_albums_test.util.OnItemClickListener
import com.example.project_albums_test.viewModel.AlbumsViewModel
import com.example.project_albums_test.viewModel.ViewModelProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_album.view.*


class AlbumFragment : Fragment(),
    OnItemClickListener {

    private val albumsViewModel by lazy {
        ViewModelProvider.getType(
            AlbumsViewModel::class.java) as AlbumsViewModel
    }
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var photoAdapter: PhotoAdapter
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridLayoutManager = GridLayoutManager(this.context, 2)
        photoAdapter = PhotoAdapter(
            activity!!,
            this
        )
        view.recyclerView.layoutManager = gridLayoutManager
        view.recyclerView.adapter = photoAdapter

        getAlbums()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }

    override fun onItemClick(position: Int, albumModel: AlbumModel) {

        val fragment = PhotoFragment()
        fragment.arguments = Bundle().also { it.putString("id", albumModel.id) }
        ManageFragment.addFragment(
            fragmentManager!!,
            fragment,
            R.id.layout_content
        )
    }


    private fun getAlbums() {
        albumsViewModel.getAlbumsSingle()
            .subscribeBy(
                onSuccess = {
                    photoAdapter.setPhoto(it)
                }
                , onError = {
                    it.printStackTrace()
                }
            )
            .addTo(compositeDisposable)
    }


}