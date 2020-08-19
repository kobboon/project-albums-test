package com.example.project_albums_test.util

import com.example.project_albums_test.model.AlbumModel

interface OnItemClickListener {
    fun onItemClick(position: Int, albumModel: AlbumModel)
}