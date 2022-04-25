package com.binar.task5chapter5.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.task5chapter5.data.AdminLogin
import com.binar.task5chapter5.data.RegisterAdmin
import com.binar.task5chapter5.response.GetCarsResponseItem
import com.binar.task5chapter5.response.LoginResponse
import com.binar.task5chapter5.response.RegisterResponse
import com.binar.task5chapter5.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private val loginResponse: MutableLiveData<Int>by lazy {
        MutableLiveData<Int>()
    }
    private val registerResponse: MutableLiveData<Int>by lazy {
        MutableLiveData<Int>()
    }
    private val cars: MutableLiveData<List<GetCarsResponseItem>>by lazy {
        MutableLiveData<List<GetCarsResponseItem>>().also {
            loadCars()
        }
    }
    private val code: MutableLiveData<Int>by lazy {
        MutableLiveData<Int>().also {
            loadCars()
        }
    }

    fun getCode(): LiveData<Int>{
        return code
    }

    fun getLoginResponse(): LiveData<Int>{
        return loginResponse
    }
    fun getRegisterResponse():LiveData<Int>{
        return registerResponse
    }
    fun getAllCars():LiveData<List<GetCarsResponseItem>>{
        return cars
    }

    fun authLogin(adminLogin: AdminLogin){
        ApiClient.instance.loginAdmin(adminLogin)
            .enqueue(object : Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                        loginResponse.value= response.code()

                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("viewmodel", t.message.toString())
                }

            })
    }

    fun registerAdmin(registerAdmin: RegisterAdmin){
        ApiClient.instance.registerAdmin(registerAdmin)
            .enqueue(object : Callback<RegisterResponse>{
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    registerResponse.value=response.code()
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.d("register", t.message.toString())
                }

            })
    }
    private fun loadCars(){
        ApiClient.instance.getAllCar()
            .enqueue(object: Callback<List<GetCarsResponseItem>>{
                override fun onResponse(
                    call: Call<List<GetCarsResponseItem>>,
                    response: Response<List<GetCarsResponseItem>>
                ) {
                    cars.value= response.body()
                    code.value=response.code()
                }

                override fun onFailure(call: Call<List<GetCarsResponseItem>>, t: Throwable) {
                    Log.d("getCars", t.message.toString())
                }

            })
    }
}