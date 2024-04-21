package com.example.myhobby.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myhobby.model.Article
import com.example.myhobby.databinding.ItemNewsBinding

class ArticleAdapter(private val articles: List<Article>, private val listener: (Article) -> Unit) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvShortContent.text = article.shortContent
            binding.tvWriter.text = article.writer
            Glide.with(binding.root.context)
                .load(article.imageUrl)
                .into(binding.ivNews)
            binding.btnRead.setOnClickListener {
                listener.invoke(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount() = articles.size
}