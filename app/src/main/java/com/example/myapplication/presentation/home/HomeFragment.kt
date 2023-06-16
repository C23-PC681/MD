package com.example.myapplication.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ml.Classification
import com.example.myapplication.presentation.adapter.TechniqueGuideAdapter
import com.example.myapplication.utils.BMICalculator
import dagger.hilt.android.AndroidEntryPoint
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

@AndroidEntryPoint
class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    // private var fitnessPlanPredictor: FitnessPlanPredictor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpActionListeners()
        setUpRecyclerView()
        setUpLiveDataObserver()
        setUpModel()
    }

    private fun setUpActionListeners() {
        binding.iconNotification.setOnClickListener(this)
        binding.imgFitnessPlan.setOnClickListener(this)
    }

    private fun setUpRecyclerView() {
        binding.rvTechniqueGuides.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpLiveDataObserver() {
        viewModel.apply {
            user.observe(viewLifecycleOwner) {
                binding.tvName.text = it.name
                if (it.goal == "LOSE_WEIGHT") {
                    binding.imgFitnessPlan.setImageResource(R.drawable.fitness_plan_lose_weight)
                } else {
                    binding.imgFitnessPlan.setImageResource(R.drawable.fitness_plan_gain_muscle)
                }
                val bmiResult = BMICalculator.calc(height = it.height!!, weight = it.weight!!)
                binding.tvDesc3.text =
                    "With height of ${it.height} cm and weight of ${it.weight} kg,\nYour BMI is $bmiResult"
            }
            techniqueGuides.observe(viewLifecycleOwner) {
                val adapter = TechniqueGuideAdapter(it)
                binding.rvTechniqueGuides.adapter = adapter
            }
        }
    }

    private fun setUpModel() {
        val model = Classification.newInstance(requireContext())
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 5), DataType.FLOAT32)
        val sampleData = floatArrayOf(25.0f, 57.0f, 170.0f, 1f, 0.0f)
        val byteBuffer =
            ByteBuffer.allocateDirect(sampleData.size * 4).order(ByteOrder.nativeOrder())
        val floatBuffer = byteBuffer.asFloatBuffer()
        floatBuffer.put(sampleData)

        inputFeature0.loadBuffer(byteBuffer)

        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer
        val floatArray = outputFeature0.floatArray
        val floatValue = floatArray[0]

        Log.e("HomeFragment", "ini $floatValue")

        model.close()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.icon_notification -> {
                findNavController().navigate(R.id.action_fragment_home_to_notificationFragment)
            }

            R.id.img_fitness_plan -> {
                findNavController().navigate(R.id.action_fragment_home_to_fitnessPlanFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}