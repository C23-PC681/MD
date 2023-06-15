package com.example.myapplication.presentation.authentication.register.input_gender

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
import com.example.myapplication.databinding.FragmentInputGenderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputGenderFragment : Fragment(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private var _binding: FragmentInputGenderBinding? = null
    private val binding get() = _binding!!
    private val viewModel: InputGenderViewModel by viewModels()
    private val args by navArgs<InputGenderFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputGenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpActionListeners()
    }

    private fun setUpActionListeners() {
        binding.apply {
            btnBack.setOnClickListener(this@InputGenderFragment)
            btnNext.setOnClickListener(this@InputGenderFragment)
            rgGender.setOnCheckedChangeListener(this@InputGenderFragment)
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        binding.btnNext.isEnabled = true

        when (checkedId) {
            R.id.rdb_male -> {
                viewModel.onCheckedChanged(Gender.MALE)
            }

            R.id.rdb_female -> {
                viewModel.onCheckedChanged(Gender.FEMALE)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> {
                findNavController().navigateUp()
            }

            R.id.btn_next -> {
                val gender = viewModel.gender.value
                val user = args.user.copy(gender = gender)
                val action =
                    InputGenderFragmentDirections.actionInputGenderFragmentToInputHeightFragment(
                        user
                    )
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}