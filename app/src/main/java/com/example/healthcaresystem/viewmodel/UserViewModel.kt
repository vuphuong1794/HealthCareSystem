package com.example.healthcaresystem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcaresystem.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.healthcaresystem.model.GetUser

class UserViewModel : ViewModel() {
    private val _users = MutableStateFlow<List<GetUser>>(emptyList())
    val users: StateFlow<List<GetUser>> get() = _users

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.admin.getUsers()
                if (response.isSuccessful) {
                    _users.value = response.body() ?: emptyList()
                } else {
                    println("Lỗi API: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
