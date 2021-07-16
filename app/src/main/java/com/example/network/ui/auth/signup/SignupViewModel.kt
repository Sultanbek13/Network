package com.example.network.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.network.data.FirebaseHelper
import com.example.network.data.Resource

class SignupViewModel(private val firebaseHelper: FirebaseHelper) : ViewModel() {

    private var mutableSignupStatus : MutableLiveData<Resource<String?>> = MutableLiveData()

    val signupStatus : LiveData<Resource<String?>>
    get() = mutableSignupStatus

    fun signup(email: String, password: String) {
        mutableSignupStatus.value = Resource.loading()
        firebaseHelper.signup(email,password,
            {
                mutableSignupStatus.value = Resource.success(null)
            },
            {
                mutableSignupStatus.value = Resource.error(it)
            })

    }

}
