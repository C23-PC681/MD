package com.example.myapplication.presentation.authentication.register.input_credential

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentInputCredentialBinding
import com.example.myapplication.domain.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputCredentialFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentInputCredentialBinding? = null
    private val binding get() = _binding!!
    private val viewModel: InputCredentialViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputCredentialBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnNext.setOnClickListener(this@InputCredentialFragment)
            passwordTextField.setEndIconOnClickListener {
                viewModel.togglePasswordVisibility()
            }
        }

        setUpActionListeners()
        setUpLiveDataObserver()
    }

    private fun setUpActionListeners() {
        binding.apply {
            edtFullName.requestFocus()
            btnBack.setOnClickListener(this@InputCredentialFragment)
            btnNext.setOnClickListener(this@InputCredentialFragment)
        }
    }

    private fun showPassword() {
        binding.apply {
            edtPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            passwordTextField.endIconDrawable =
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_visibility
                )
        }
    }

    private fun hidePassword() {
        binding.apply {
            edtPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            passwordTextField.endIconDrawable =
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_visibility_off
                )
        }
    }

    private fun setUpLiveDataObserver() {
        viewModel.apply {
            passwordVisible.observe(viewLifecycleOwner) { visible ->
                if (visible) {
                    showPassword()
                } else {
                    hidePassword()
                }
            }
            nameError.observe(viewLifecycleOwner) { nameError ->
                binding.tvNameError.text = nameError
            }
            emailError.observe(viewLifecycleOwner) { emailError ->
                binding.tvEmailError.text = emailError
            }
            passwordError.observe(viewLifecycleOwner) { passwordError ->
                binding.tvPasswordError.text = passwordError
            }
            isLoading.observe(viewLifecycleOwner) { isLoading ->
                if (isLoading) {
                    binding.loadingOverlay.showLoadingOverlay()
                    binding.btnBack.isEnabled = false
                    binding.btnNext.isEnabled = false
                } else {
                    binding.loadingOverlay.hideLoadingOverlay()
                    binding.btnBack.isEnabled = true
                    binding.btnNext.isEnabled = true
                }
            }
            isValid.observe(viewLifecycleOwner) { isValid ->
                if (isValid) {
                    val action =
                        InputCredentialFragmentDirections.actionRegisterFragmentToInputGenderFragment(
                            viewModel.user.value!!
                        )
                    findNavController().navigate(action)

                    viewModel.setIsValid(false)
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> {
                findNavController().navigateUp()
            }

            R.id.btn_next -> {
                val name = binding.edtFullName.text.toString()
                val email = binding.edtEmail.text.toString()
                val password = binding.edtPassword.text.toString()
                val user = User(name = name, email = email, password = password)
                viewModel.validateInput(user)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
