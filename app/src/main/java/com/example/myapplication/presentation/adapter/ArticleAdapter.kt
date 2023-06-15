package com.example.myapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.databinding.ItemArticleBinding
import com.example.myapplication.domain.model.Article
import com.example.myapplication.presentation.article.ArticlesFragmentDirections

class ArticleAdapter(private val data: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val article = data[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ItemViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            Glide.with(binding.root)
                .load(article.urlToImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageItem)

            binding.apply {
                tvTitle.text = article.title
                tvDesc.text = article.description
            }

            setUpActionListener(article)
        }

        private fun setUpActionListener(article: Article) {
            itemView.setOnClickListener {
                val action =
                    ArticlesFragmentDirections.actionFragmentArticlesToArticleDetailFragment(article)

                itemView.findNavController().navigate(action)
            }
        }
    }
}