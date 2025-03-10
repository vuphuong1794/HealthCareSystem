package com.example.healthcaresystem.api


import com.example.healthcaresystem.model.SignUpRequest
import com.example.healthcaresystem.model.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Call<SignUpResponse>
}
