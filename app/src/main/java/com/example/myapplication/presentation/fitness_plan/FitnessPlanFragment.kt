package com.example.myapplication.presentation.fitness_plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFitnessPlanBinding
import com.example.myapplication.presentation.adapter.ExerciseAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FitnessPlanFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentFitnessPlanBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FitnessPlanViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFitnessPlanBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpLiveDataObserver()
    }

    private fun setUpRecyclerView() {
        binding.rvExercise.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setUpLiveDataObserver() {
        viewModel.apply {
            exercises.observe(viewLifecycleOwner) {
                binding.rvExercise.adapter = ExerciseAdapter(it)
            }
            user.observe(viewLifecycleOwner) {
                if (it.goal == "LOSE_WEIGHT") {
                    binding.imgFitnessPlan.setImageResource(R.drawable.fitness_plan_lose_weight)
                } else {
                    binding.imgFitnessPlan.setImageResource(R.drawable.fitness_plan_gain_muscle)
                }
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