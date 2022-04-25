package com.binar.task5chapter5.data

import com.google.gson.annotations.SerializedName

data class RegisterAdmin(
    @SerializedName("email")
    val email: String?=null,
    @SerializedName("password")
    val password: String?=null,
    @SerializedName("role")
    val role: String?= "admin"
)
