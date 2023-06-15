package com.example.myapplication.presentation.authentication.register.input_weight

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentInputWeightBinding

class InputWeightFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentInputWeightBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<InputWeightFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputWeightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpActionListeners()
    }

    private fun setUpActionListeners() {
        binding.apply {
            edtWeight.apply {
                requestFocus()
                addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable) {}
                    override fun beforeTextChanged(
                        s: CharSequence,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        binding.btnNext.isEnabled = s.isNotBlank()
                    }
                })
            }
            btnBack.setOnClickListener(this@InputWeightFragment)
            btnNext.setOnClickListener(this@InputWeightFragment)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> {
                findNavController().navigateUp()
            }

            R.id.btn_next -> {
                val weight: Int = binding.edtWeight.text.toString().toInt()
                val user = args.user.copy(weight = weight)
                val action =
                    InputWeightFragmentDirections.actionInputWeightFragmentToInputGoalFragment(user)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}