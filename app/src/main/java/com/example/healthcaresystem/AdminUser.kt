package com.example.healthcaresystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthcaresystem.ui.theme.HealthCareSystemTheme
import com.example.healthcaresystem.viewmodel.UserViewModel
import com.example.healthcaresystem.model.GetUser
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

class AdminUser : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HealthCareSystemTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserListScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UserListScreen(modifier: Modifier = Modifier, viewModel: UserViewModel = viewModel()) {
    val users by viewModel.users.collectAsState()

    // Gọi API khi màn hình được tạo
    LaunchedEffect(Unit) {
        viewModel.fetchUsers()
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Danh sách người dùng", style = MaterialTheme.typography.headlineMedium)

        if (users.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 20.dp))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 10.dp)) {
                items(users) { user ->
                    UserItem(user)
                }
            }
        }
    }
}

@Composable
fun UserItem(user: GetUser) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Tên: ${user.name}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Email: ${user.email}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Vai trò: ${user.role}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserListScreen() {
    HealthCareSystemTheme {
        UserListScreen()
    }
}
