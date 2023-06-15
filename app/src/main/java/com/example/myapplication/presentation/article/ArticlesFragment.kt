package com.example.myapplication.presentation.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentArticlesBinding
import com.example.myapplication.presentation.adapter.ArticleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlesFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ArticlesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticlesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpLiveDataObserver()
    }

    private fun setUpRecyclerView() {
        binding.rvArticle.layoutManager = LinearLayoutManager(requireContext())
        viewModel.articles.observe(viewLifecycleOwner) {
            binding.rvArticle.adapter = ArticleAdapter(it)
        }
    }

    private fun setUpLiveDataObserver() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if(it) {
                binding.loadingOverlay.showLoadingOverlay()
            } else {
                binding.loadingOverlay.hideLoadingOverlay()
            }
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