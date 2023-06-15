package com.example.myapplication.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSplashBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SplashViewModel by viewModels()
    private var loginSession = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).asGif().load(R.raw.splash).into(binding.imageGif)
        setUpLiveDataObserver()

        Handler(Looper.getMainLooper())
            .postDelayed({
                if (loginSession) {
                    findNavController().navigate(R.id.action_splashFragment_to_home_navigation)
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_auth_navigation)
                }
            }, 2000)

        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        navBar.visibility = View.GONE
    }

    private fun setUpLiveDataObserver() {
        viewModel.loginSession.observe(viewLifecycleOwner) {
            if (it) {
                loginSession = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}