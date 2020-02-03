package com.example.photoapplication.ui.imagesFrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.photoapplication.R
import com.example.photoapplication.data.images.Pic
import com.example.photoapplication.repository.GlideApp
import com.example.photoapplication.ui.viewImageFrag.ViewImageFragment

class PicAdapter(
    private val pics: List<Pic>,
    val netOn: Boolean = false
) : RecyclerView.Adapter<PicAdapter.PicViewHolder>() {

    class PicViewHolder(itemView: View, val netOn: Boolean) : RecyclerView.ViewHolder(itemView) {
        var currentId: String = "id"
        var curPosition: Int = -1

        init {
//            if(netOn) {
                itemView.setOnClickListener {
                    val activity = itemView.context as AppCompatActivity
                    val fragment = ViewImageFragment()
                    val args = Bundle()
                    args.putString("id", currentId)
                    fragment.arguments = args
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_holder, fragment).addToBackStack(null).commit()
                }
//            }
        }

        fun setData(id: String, curPosition: Int) {
            this.currentId = id
            this.curPosition = curPosition
        }

        val picView = itemView.findViewById<ImageView>(R.id.imageView)
        val textTitle = itemView.findViewById<TextView>(R.id.textImageTitle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_images, parent, false)
        return PicViewHolder(
            v,netOn

        )
    }

    override fun getItemCount(): Int {
        return pics.size
    }

    override fun onBindViewHolder(holder: PicViewHolder, position: Int) {
        val pic: Pic = pics[position]
        holder.textTitle.text = pic.title
        holder.setData(pic.id.toString(),position)
        if(netOn){
            GlideApp.with(holder.itemView.context)
                .load("${pics[position].url}.png")
                .placeholder(R.drawable.offline_image)
                .fitCenter()
                .into(holder.picView)
            holder.setData(pic.id.toString(), position)
        }else{
            holder.picView.setImageDrawable(holder.picView.context.resources.getDrawable(R.drawable.offline_image))
        }
    }
}