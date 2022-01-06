package com.example.bakinapplication.repo

import com.example.bakinapplication.model.SignUpModel
import com.example.bakinapplication.severdb.ServerBuilder

class SignUp2Repo {
    suspend fun signUp(signUpModel: SignUpModel) = ServerBuilder.serverApi.signUp(signUpModel)
}