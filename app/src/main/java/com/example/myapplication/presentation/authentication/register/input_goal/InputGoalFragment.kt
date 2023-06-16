package com.example.myapplication.presentation.authentication.register.input_goal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentInputGoalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputGoalFragment : Fragment(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private var _binding: FragmentInputGoalBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<InputGoalFragmentArgs>()
    private val viewModel: InputGoalViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputGoalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpActionListeners()
        setUpLiveDataObserver()
    }

    private fun setUpActionListeners() {
        binding.apply {
            btnBack.setOnClickListener(this@InputGoalFragment)
            btnFinish.setOnClickListener(this@InputGoalFragment)
            rgGoal.setOnCheckedChangeListener(this@InputGoalFragment)
        }
    }

    private fun setUpLiveDataObserver() {
        viewModel.apply {
            isLoading.observe(viewLifecycleOwner) {
                if (it) {
                    binding.loadingOverlay.showLoadingOverlay()
                    binding.btnBack.isEnabled = false
                    binding.btnFinish.isEnabled = false
                } else {
                    binding.loadingOverlay.hideLoadingOverlay()
                    binding.btnBack.isEnabled = true
                    binding.btnFinish.isEnabled = true

                }
            }
            isValid.observe(viewLifecycleOwner) {
                if (it) {
                    val action =
                        InputGoalFragmentDirections.actionInputGoalFragmentToHomeNavigation()
                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        binding.btnFinish.isEnabled = true

        when (checkedId) {
            R.id.rdb_lose_weight -> {
                viewModel.onCheckedChanged(Goal.LOSE_WEIGHT)
            }

            R.id.rdb_muscle_gain -> {
                viewModel.onCheckedChanged(Goal.GAIN_MUSCLE)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> {
                findNavController().navigateUp()
            }

            R.id.btn_finish -> {
                val goal = viewModel.goal.value
                val user = args.user.copy(goal = goal)
                viewModel.register(user)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}