package com.example.myapplication.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnEditProfile.setOnClickListener(this@ProfileFragment)
            btnSettingLanguage.setOnClickListener(this@ProfileFragment)
            btnSettingTheme.setOnClickListener(this@ProfileFragment)
            btnSettingNotification.setOnClickListener(this@ProfileFragment)
            btnLogout.setOnClickListener(this@ProfileFragment)
        }

        setUpLiveDataObserver()
    }

    private fun setUpLiveDataObserver() {
        viewModel.apply {
            loginSession.observe(viewLifecycleOwner) {
                if (!it) {
                    findNavController().navigate(R.id.action_fragment_profile_to_auth_navigation)
                }
            }
            user.observe(viewLifecycleOwner) {
                binding.apply {
                    tvName.text = it.name
                    tvEmail.text = it.email
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_edit_profile -> {
                findNavController().navigate(R.id.action_fragment_profile_to_editProfileFragment)
            }

            R.id.btn_setting_language -> {
                findNavController().navigate(R.id.action_fragment_profile_to_languageFragment)
            }

            R.id.btn_setting_theme -> {
                findNavController().navigate(R.id.action_fragment_profile_to_themeFragment)
            }

            R.id.btn_setting_notification -> {
                findNavController().navigate(R.id.action_fragment_profile_to_remindersFragment)
            }

            R.id.btn_logout -> {
                viewModel.logOut()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}