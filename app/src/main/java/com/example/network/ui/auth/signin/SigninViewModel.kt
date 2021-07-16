package com.example.network.ui.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.network.data.FirebaseHelper
import com.example.network.data.Resource

class SigninViewModel(private val firebaseHelper: FirebaseHelper) : ViewModel() {

    private var mutableSignInStatus: MutableLiveData<Resource<Any?>> = MutableLiveData()

    val signinStatus: LiveData<Resource<Any?>>
    get() = mutableSignInStatus

    fun signIn(email: String, password: String) {
        mutableSignInStatus.value = Resource.loading()
        firebaseHelper.signin(email, password,
            {
                mutableSignInStatus.value = Resource.success(null)
            },
            {
                mutableSignInStatus.value = Resource.error(it)
            })
    }
}
