package com.example.bakinapplication.repo

import com.example.bakinapplication.model.LoginModel
import com.example.bakinapplication.severdb.ServerBuilder

class LoginRepo {
    suspend fun login(loginModel: LoginModel) = ServerBuilder.serverApi.login(loginModel)
}