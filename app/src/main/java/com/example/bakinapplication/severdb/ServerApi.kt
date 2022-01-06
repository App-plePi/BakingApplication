package com.example.bakinapplication.severdb

import com.example.bakinapplication.model.*
import retrofit2.Response
import retrofit2.http.*

interface ServerApi {
    @POST("auth/login")
    suspend fun login(@Body model: LoginModel) : Response<Authorization>

    @POST("auth/join")
    suspend fun confirm(@Body model: ConfirmModel) : Response<ConfirmModel>

    @POST("auth/verify")
    suspend fun signUp(@Body model: SignUpModel) : Response<SignUpModel>

    @POST("/recipe")
    suspend fun recipeCreate(@Header("Authorization") token: String, @Body model: RecipeCreateModel) : Response<SignUpModel>

    @GET("/recipe?")
    suspend fun getRecipeList(@Query("start") start: Int) : Response<ArrayList<RecipeListModel>>

    @GET("/recipe/{id}")
    suspend fun getRecipe(@Path("id") id: String): Response<RecipeListModel>

    @GET("/user/my")
    suspend fun getProfile(@Header("Authorization") token: String) :Response<User>

    @GET("/recipe/user-id/{userid}")
    suspend fun getMyRecipeList(@Path("userid")id:String):Response<ArrayList<RecipeListModel>>

    @GET("/user/exist/nickname/{nickname}")
    suspend fun checkNickname(@Path("nickname")nickname:String):Response<CheckModel>
}
