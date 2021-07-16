package com.example.network.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.example.network.data.Settings
import uz.texnopos.instagram_texnopos.R
import uz.texnopos.instagram_texnopos.databinding.FragmentSplashBinding

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var navController : NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val settings = Settings(requireContext())
        binding = FragmentSplashBinding.bind(view)
        navController = Navigation.findNavController(view)

        requireActivity().actionBar?.hide()
        binding.splashView.setMaxProgress(0.6f)
        binding.splashView.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {

                if(settings.signedIn) {
                    navController.navigate(R.id.action_splashFragment_to_mainFragment)
                } else {
                    navController.navigate(R.id.action_splashFragment_to_signinFragment)
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }
        })

    }
}