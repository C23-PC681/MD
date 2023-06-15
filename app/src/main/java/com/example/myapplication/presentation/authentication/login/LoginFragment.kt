package com.example.myapplication.presentation.authentication.login

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpActionListeners()
        setUpLiveDataObserver()
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

    private fun setUpActionListeners() {
        binding.apply {
            edtEmail.requestFocus()
            btnLogin.setOnClickListener(this@LoginFragment)
            passwordTextField.setEndIconOnClickListener {
                viewModel.togglePasswordVisibility()
            }
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
            emailError.observe(viewLifecycleOwner) { emailError ->
                binding.tvEmailError.text = emailError
            }
            passwordError.observe(viewLifecycleOwner) { passwordError ->
                binding.tvPasswordError.text = passwordError
            }
            isLoading.observe(viewLifecycleOwner) { isLoading ->
                if (isLoading) {
                    binding.loadingOverlay.showLoadingOverlay()
                    binding.btnLogin.isEnabled = false
                } else {
                    binding.loadingOverlay.hideLoadingOverlay()
                    binding.btnLogin.isEnabled = true
                }
            }
            errorMessage.observe(viewLifecycleOwner) { errorMessage ->
                if (errorMessage.isNotEmpty()) {
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
            isValid.observe(viewLifecycleOwner) { isValid ->
                if (isValid == true) {
                    findNavController().navigate(R.id.action_loginFragment_to_home_navigation)
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                val emailInput = binding.edtEmail.text.toString()
                val passwordInput = binding.edtPassword.text.toString()
                viewModel.login(emailInput, passwordInput)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}