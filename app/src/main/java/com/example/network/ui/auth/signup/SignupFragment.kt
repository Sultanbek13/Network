package com.example.network.ui.auth.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.network.data.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.texnopos.instagram_texnopos.R
import uz.texnopos.instagram_texnopos.databinding.FragmentSignupBinding

class SignupFragment: Fragment(R.layout.fragment_signup) {

    private lateinit var binding: FragmentSignupBinding
    private val viewModel : SignupViewModel by viewModel()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        var success = true

        binding = FragmentSignupBinding.bind(view)

        binding.apply {
            btnRegister.setOnClickListener {
                if (etRegisterEmail.text.isNullOrEmpty()) {
                    etRegisterEmail.error = getString(R.string.fill_the_field)
                    success = false
                }
                if (etRegisterPassword.text.isNullOrEmpty()) {
                    etRegisterPassword.error = getString(R.string.fill_the_field)
                    success = false
                }
                if (etRegisterPassword2.text.isNullOrEmpty()) {
                    etRegisterPassword2.error = getString(R.string.fill_the_field)
                    success = false
                }

                if (!success) return@setOnClickListener

                if (etRegisterPassword.text.toString() != etRegisterPassword2.text.toString()) {
                    success = false
                    etRegisterPassword.error = getString(R.string.password_not_match)
                } else {
                    viewModel.signup(etRegisterEmail.text.toString(),etRegisterPassword.text.toString())
                }
            }
        }
        setObservers()
    }
    private fun setObservers() {
        viewModel.signupStatus.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }

                ResourceState.SUCCESS -> {
                    val action = SignupFragmentDirections.actionSignupFragmentToMainFragment()
                    navController.navigate(action)
                }

                ResourceState.ERROR -> {
                    setLoading(false)
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setLoading(isLoading: Boolean) {
        binding.apply {
            loading.isVisible = isLoading
            etRegisterEmail.isVisible = !isLoading
            etRegisterPassword.isEnabled = !isLoading
            etRegisterPassword2.isEnabled = !isLoading

        }
    }
}