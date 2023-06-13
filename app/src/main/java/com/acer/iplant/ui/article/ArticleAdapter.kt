package com.acer.iplant.ui.article

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.acer.iplant.databinding.ItemArticleBinding
import com.bumptech.glide.Glide

class ArticleAdapter (private val list: ArrayList<ArticleResponseItem>): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ViewHolder, position: Int) {
        val article = list[position]
        holder.bind(article)
        holder.binding.cardStory.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailArticleActivity::class.java)
            intent.putExtra(DetailArticleActivity.TITLE_EXTRA, article.title)
            intent.putExtra(DetailArticleActivity.DESCRIPTION_EXTRA, article.article)
            intent.putExtra(DetailArticleActivity.IMAGE_URL_EXTRA, article.thumbnail)

            val optionsCompat: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    holder.itemView.context as Activity,
                    Pair(holder.binding.imgPost, "picture"),
                    Pair(holder.binding.tvDesc, "description")
                )

            holder.itemView.context.startActivity(intent, optionsCompat.toBundle())
        }
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