package com.example.photoapplication.ui.usersFrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.photoapplication.R
import com.example.photoapplication.data.user.User
import com.example.photoapplication.ui.albumsFrag.AlbumsFragment

class UserAdapter(
    private val users: List<User>
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentId: String = "id"
        var currentName: String = "name"
        var curPosition: Int = -1

        init {
            itemView.setOnClickListener {
                val activity = itemView.context as AppCompatActivity
                val fragment =
                    AlbumsFragment()
                val args = Bundle()
                args.putString("id", currentId)
                fragment.arguments = args
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_holder, fragment).addToBackStack(null).commit()
            }

        }

        fun setData(id: String, name: String, curPosition: Int) {
            this.currentId = id
            this.curPosition = curPosition
            this.currentName = name
        }

        val textName = itemView.findViewById<TextView>(R.id.textViewName)
        val textId = itemView.findViewById<TextView>(R.id.textViewId)
        val textNumber = itemView.findViewById<TextView>(R.id.textViewNumber)
        val textUsername = itemView.findViewById<TextView>(R.id.textViewUsername)
        val textWebsite = itemView.findViewById<TextView>(R.id.textViewWebsite)
        val textEmail = itemView.findViewById<TextView>(R.id.textViewEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_user, parent, false)
        return UserViewHolder(
            v
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: User = users[position]
        holder.textId.text = user.id.toString()
        holder.textName.text = user.name
        holder.textNumber.text = user.phone
        holder.textUsername.text = user.username
        holder.textWebsite.text = user.website
        holder.textEmail.text = user.email
        holder.setData(user.id.toString(), user.name, position)
    }
}