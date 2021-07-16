package com.example.network.di

import com.example.network.data.FirebaseHelper
import com.example.network.data.Settings
import com.example.network.ui.auth.signin.SigninViewModel
import com.example.network.ui.auth.signup.SignupViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module () {
    single { FirebaseAuth.getInstance() }
    single { FirebaseHelper(get())}
    single { Settings(androidContext()) }
}

val viewModelModule = module {
    viewModel { SignupViewModel(get()) }
    viewModel { SigninViewModel(get()) }
}