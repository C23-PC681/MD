package com.example.myapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.databinding.ItemArticleBinding
import com.example.myapplication.domain.model.Exercise

class ExerciseAdapter(private val data: List<Exercise>) :
    RecyclerView.Adapter<ExerciseAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val exercise = data[position]
        holder.bind(exercise)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ItemViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: Exercise) {
            Glide.with(binding.root)
                .load(exercise.urlToImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageItem)

            binding.apply {
                tvTitle.text = exercise.title
                tvDesc.text = exercise.desc
            }
        }
    }
}