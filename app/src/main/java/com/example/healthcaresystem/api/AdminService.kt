package com.example.healthcaresystem.api

import com.example.healthcaresystem.model.GetUser
import com.example.healthcaresystem.model.GetUserID
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers

interface AdminService {
    @Headers("Content-Type: application/json")
    @GET("admin/users")
    suspend fun getUsers(): Response<List<GetUser>> //trả về một đối tượng GetUser

    @GET("user/get/:id")
    suspend fun GetByUserID(@Body request: GetUserID): Response<GetUser>
}