package com.example.project_albums_test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_albums_test.R
import com.example.project_albums_test.model.AlbumModel
import com.example.project_albums_test.util.OnItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class PhotoAdapter(
    val context: Context,
    val listener: OnItemClickListener
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    private var listPhoto: ArrayList<AlbumModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return this.listPhoto.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        listPhoto[position].let {

//            Glide.with(holder.img)
//                .asBitmap()
//                .load("https://i.imgur.com/DvpvklR.png")
//                .placeholder(R.drawable.drawable_loading_progress)
//                .error(android.R.drawable.stat_notify_error)
//                .apply( RequestOptions().override(20, 20))
//                .into(holder.img)

//            Picasso.with(context).load("https://via.placeholder.com/150/810b14")
//                .placeholder(R.drawable.drawable_loading_progress)
//                .error(android.R.drawable.stat_notify_error)
//                .into(holder.img);

            Glide.with(holder.img)
                .asBitmap()
                .load(it.thumbnailUrl)
                .centerCrop()
                .placeholder(R.drawable.drawable_loading_progress)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.img)

            holder.txtTitle.text = it.title
            holder.ln.setOnClickListener { _ ->
                listener.onItemClick(position, it)
            }
        }

    }


    fun setPhoto(listPhoto: ArrayList<AlbumModel>) {
        this.listPhoto = listPhoto
        notifyDataSetChanged()
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtTitle: TextView = view.txtTitle
        var img: ImageView = view.img
        var ln: RelativeLayout = view.ln
    }


}