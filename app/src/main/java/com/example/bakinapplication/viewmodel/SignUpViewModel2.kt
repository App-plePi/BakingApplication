package com.example.bakinapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakinapplication.repo.SignUp2Repo
import com.example.bakinapplication.model.SignUpModel
import kotlinx.coroutines.async

class SignUpViewModel2: ViewModel() {
    private val signUpRepo = SignUp2Repo()
    val confirm = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val repeatPassword = MutableLiveData<String>()
    val isSuccess = MutableLiveData<Boolean>()

    fun checkPassword(): Boolean{
        return password.value == repeatPassword.value
    }

    suspend fun signUp(nickname: String, phone: String): Boolean? {
        viewModelScope.async {
            isSuccess.value =
                signUpRepo.signUp(SignUpModel(nickname, password.value!!,phone, confirm.value!!))
                    .isSuccessful
        }.await()
        return isSuccess.value
    }
}