package com.example.photoapplication.ui.albumsFrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.photoapplication.R
import com.example.photoapplication.data.album.Album
import com.example.photoapplication.ui.imagesFrag.ImagesFragment

class AlbumAdapter (
    private val albums: List<Album>
): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentId: String = "id"
        var curPosition: Int = -1

        init {
            itemView.setOnClickListener {
                val activity = itemView.context as AppCompatActivity
                val fragment = ImagesFragment()
                val args = Bundle()
                args.putString("id", currentId)
                fragment.arguments = args
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_holder, fragment).addToBackStack(null).commit()
            }
        }

        fun setData(id: String, curPosition: Int) {
            this.currentId = id
            this.curPosition = curPosition
        }

        val textId = itemView.findViewById<TextView>(R.id.textViewAlbumId)
        val textTitle = itemView.findViewById<TextView>(R.id.textViewTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_album, parent, false)
        return AlbumViewHolder(
            v
        )
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album: Album = albums[position]
        holder.textId.text = album.id.toString()
        holder.textTitle.text = album.title
        holder.setData(album.id.toString(), position)
    }
}