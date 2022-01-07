package com.example.bakinapplication.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakinapplication.model.ConfirmModel
import com.example.bakinapplication.repo.SignUp1Repo
import com.example.bakinapplication.severdb.ServerBuilder
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpViewModel1: ViewModel() {
    val repo = SignUp1Repo()
    val nickname = MutableLiveData<String>()
    val nicknameWarning = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    var isCheck = false

    init {
        nicknameWarning.value = "3~8자 닉네임"
    }

    fun confirm(){
        viewModelScope.launch {
            repo.confirm(ConfirmModel(phone.value!!))
        }
    }
    fun checkNickname(){
        viewModelScope.launch {
            val isExist = repo.clickNickname(nickname.value!!).body()
            Log.d(TAG, "checkNickname: $isExist")
            isCheck = if(isExist!!.exist){
                nicknameWarning.postValue("이미 존재하는 닉네임 입니다")
                false
            }else{
                nicknameWarning.postValue("3~8자 닉네임")
                true
            }
        }
    }
}