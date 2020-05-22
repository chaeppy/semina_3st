package com.example.semina_3st

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class InstaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val tv_username = itemView.findViewById<TextView>(R.id.tv_username)
    val img_contents = itemView.findViewById<ImageView>(R.id.img_contents)
    val img_profile = itemView.findViewById<ImageView>(R.id.img_profile)

    fun bind(instaData: InstaData){
        tv_username.text = instaData.userName
        Glide.with(itemView).load(instaData.img_contents).into(img_contents)
        Glide.with(itemView).load(instaData.img_profile).into(img_profile)

    }

}