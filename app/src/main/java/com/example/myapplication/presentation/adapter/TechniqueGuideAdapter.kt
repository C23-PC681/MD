package com.example.myapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.data.local.entity.TechniqueGuide
import com.example.myapplication.databinding.ItemTechniqueGuideBinding
import com.example.myapplication.presentation.home.HomeFragmentDirections

class TechniqueGuideAdapter(private val data: List<TechniqueGuide>) :
    RecyclerView.Adapter<TechniqueGuideAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemTechniqueGuideBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val techniqueGuide = data[position]
        holder.bind(techniqueGuide)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ItemViewHolder(private val binding: ItemTechniqueGuideBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(techniqueGuide: TechniqueGuide) {
            Glide.with(binding.root)
                .load(techniqueGuide.urlToImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imgTechnique)

            binding.apply {
                tvTitle.text = techniqueGuide.title
                tvDesc.text = techniqueGuide.description
            }

            setUpActionListener(techniqueGuide)
        }

        private fun setUpActionListener(techniqueGuide: TechniqueGuide) {
            itemView.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionFragmentHomeToTechniqueGuideFragment(techniqueGuide)

                itemView.findNavController().navigate(action)
            }
        }
    }
}