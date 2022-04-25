package com.binar.task5chapter5.service

import com.binar.task5chapter5.data.AdminLogin
import com.binar.task5chapter5.data.RegisterAdmin
import com.binar.task5chapter5.response.GetCarsResponseItem
import com.binar.task5chapter5.response.LoginResponse
import com.binar.task5chapter5.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("admin/auth/register")
    fun registerAdmin(@Body registerAdmin: RegisterAdmin): Call<RegisterResponse>

    @POST("admin/auth/login")
    fun loginAdmin(@Body login: AdminLogin): Call<LoginResponse>

    @GET("admin/car")
    fun getAllCar(): Call<List<GetCarsResponseItem>>
}