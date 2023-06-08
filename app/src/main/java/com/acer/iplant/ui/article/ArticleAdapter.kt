package com.acer.iplant.ui.article

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acer.iplant.databinding.ItemArticleBinding
import com.acer.iplant.data.ArticleEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class ArticleAdapter (private val list: ArrayList<ArticleResponseItem>): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ItemArticleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticleResponseItem){
            with(itemView){
                binding.tvTitle.text = data.title
                binding.tvDesc.text = data.subInfo
                Glide.with(itemView.context)
                    .load(data.thumbnail)
                    .into(binding.imgPost)
            }
        }
    }
}