package com.example.myapplication.presentation.article_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.databinding.FragmentArticleDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentArticleDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ArticleDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpArticleData()
    }

    private fun setUpArticleData() {
        Glide.with(binding.root)
            .load(args.article.urlToImage)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imageItem)

        binding.apply {
            tvTitle.text = args.article.title
            tvDesc.text = args.article.description
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}