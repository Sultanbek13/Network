package com.example.network.ui.auth.signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.network.data.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.instagram_texnopos.R
import uz.texnopos.instagram_texnopos.databinding.FragmentSigninBinding

class SigninFragment: Fragment(R.layout.fragment_signin) {

    private lateinit var binding: FragmentSigninBinding
    private lateinit var navController: NavController
    private val viewModel : SigninViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSigninBinding.bind(view)
        navController = Navigation.findNavController(view)
        setObservers()

        binding.apply {

            binding.tvSignup.setOnClickListener {
                navController.navigate(R.id.action_signinFragment_to_signupFragment)
            }

            binding.btnSignin.setOnClickListener {
                var success = true
                if (etLoginEmail.text.isNullOrEmpty()) {
                    success = false
                    etLoginEmail.error = getString(R.string.fill_the_field)
                }
                if (etLoginPassword.text.isNullOrEmpty()) {
                    success = false
                    etLoginPassword.error = getString(R.string.fill_the_field)
                }
                if (!success) return@setOnClickListener
                viewModel.signIn(etLoginEmail.text.toString(),etLoginPassword.text.toString())
            }
        }
    }

    private fun setObservers() {
        viewModel.signinStatus.observe(viewLifecycleOwner, {
            when(it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    navController.navigate(R.id.action_signinFragment_to_mainFragment)
                }
                ResourceState.ERROR -> {
                    setLoading(false)
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setLoading(isLoading: Boolean) {
        binding.apply {
            loading.isVisible = isLoading
            etLoginEmail.isEnabled = !isLoading
            etLoginPassword.isEnabled = !isLoading
        }
    }
}