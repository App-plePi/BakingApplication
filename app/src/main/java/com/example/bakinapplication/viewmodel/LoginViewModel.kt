package com.example.bakinapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakinapplication.utils.UtilCode.Companion.token
import com.example.bakinapplication.model.LoginModel
import com.example.bakinapplication.repo.LoginRepo
import kotlinx.coroutines.async

class LoginViewModel : ViewModel() {
    val repo = LoginRepo()
    val nickname = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    private var isSuccess = true

    suspend fun login(): Boolean{
        viewModelScope.async {
            repo.login(LoginModel(nickname.value!!, password.value!!)).apply {
                isSuccess = isSuccessful
                if(isSuccessful){
                    token = this.body()!!.accessToken
                }
            }
        }.await()
        return isSuccess
    }
}