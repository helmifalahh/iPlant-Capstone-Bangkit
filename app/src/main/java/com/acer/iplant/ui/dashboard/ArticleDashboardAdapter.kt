package com.acer.iplant.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acer.iplant.databinding.ItemArticleBinding
import com.acer.iplant.ui.article.ArticleResponseItem
import com.bumptech.glide.Glide

class ArticleDashboardAdapter (private val list: ArrayList<ArticleResponseItem>): RecyclerView.Adapter<ArticleDashboardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleDashboardAdapter.ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleDashboardAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = 3

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