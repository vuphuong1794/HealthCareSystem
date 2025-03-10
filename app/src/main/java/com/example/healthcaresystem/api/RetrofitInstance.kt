package com.example.healthcaresystem.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// cấu hình retrofit
object RetrofitInstance {
    private const val BASE_URL = "https://healthcare-backend-yc39.onrender.com/"

    val api: AuthService by lazy {
        // tạo retrofit
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // dùng gson để chuyển json thành obj
            .build()
            .create(AuthService::class.java) // tạo đối tg API để gọi API
    }
}
