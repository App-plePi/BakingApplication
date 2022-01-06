package com.example.bakinapplication.repo

import com.example.bakinapplication.model.ConfirmModel
import com.example.bakinapplication.severdb.ServerBuilder

class SignUp1Repo {
    suspend fun confirm(confirmModel: ConfirmModel) = ServerBuilder.serverApi.confirm(confirmModel)
}