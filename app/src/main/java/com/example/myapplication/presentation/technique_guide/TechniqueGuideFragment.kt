package com.example.myapplication.presentation.technique_guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.databinding.FragmentTechniqueGuideBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TechniqueGuideFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentTechniqueGuideBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<TechniqueGuideFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTechniqueGuideBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpData()

    }

    private fun setUpData() {
        val techniqueGuide = args.techniqueGuide

        Glide.with(binding.root)
            .load(techniqueGuide.urlToImage)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imageTechniqueGuide)

        binding.apply {
            tvTitle.text = techniqueGuide.title
            tvDesc.text = techniqueGuide.description
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