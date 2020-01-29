package com.example.photoapplication.data.images

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.photoapplication.R
import com.example.photoapplication.ui.imagesFrag.ImagesFragment

class PicOfflineAdapter (
    private val pics: List<Pic>
): RecyclerView.Adapter<PicOfflineAdapter.PicViewHolder>() {

    class PicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var currentId: String = "id"
//        var curPosition: Int = -1
//
//        init {
//            itemView.setOnClickListener {
//                val activity = itemView.context as AppCompatActivity
//                val fragment = ImagesFragment()
//                val args = Bundle()
//                args.putString("id", currentId)
//                fragment.arguments = args
//                activity.supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragment_holder, fragment).commit()
//            }
//        }
//
//        fun setData(id: String, curPosition: Int) {
//            this.currentId = id
//            this.curPosition = curPosition
//        }

        val picView = itemView.findViewById<ImageView>(R.id.imageView)
        val textTitle = itemView.findViewById<TextView>(R.id.textImageTitle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_images, parent, false)
        return PicViewHolder(v)
    }

    override fun getItemCount(): Int {
        return pics.size
    }

    override fun onBindViewHolder(holder: PicViewHolder, position: Int) {
        val pic: Pic = pics[position]
        holder.textTitle.text = pic.title
        Log.e("textTitle",pic.title)
        holder.picView.setImageDrawable(holder.picView.context.resources.getDrawable(R.drawable.offline_image))
        //holder.picView.setImageResource(R.drawable.offline_image)
        //Glide.with(holder.itemView.context).load().into(holder.picView)

        //holder.setData(pic.id.toString(), position)
    }
}