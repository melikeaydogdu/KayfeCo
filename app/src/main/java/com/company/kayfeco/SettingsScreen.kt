package com.company.kayfeco

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

val brown = Color(0xFF5D4037)
@Composable

fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    val user = auth.currentUser
    val email = user?.email ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Şifre Değiştir", style = MaterialTheme.typography.titleMedium)

        OutlinedTextField(
            value = oldPassword,
            onValueChange = { oldPassword = it },
            label = { Text("Eski Şifre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("Yeni Şifre") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (newPassword.length < 6) {
                    Toast.makeText(context, "Şifre en az 6 karakter olmalı!", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (newPassword == oldPassword) {
                    Toast.makeText(context, "Yeni şifre eski şifreyle aynı olamaz!", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                // Yeniden kimlik doğrulama işlemi
                val credential = com.google.firebase.auth.EmailAuthProvider
                    .getCredential(email, oldPassword)

                user?.reauthenticate(credential)
                    ?.addOnSuccessListener {
                        user.updatePassword(newPassword)
                            .addOnSuccessListener {
                                Toast.makeText(context, "Şifre güncellendi!", Toast.LENGTH_SHORT).show()
                                oldPassword = ""
                                newPassword = ""
                            }
                            .addOnFailureListener {
                                Toast.makeText(context, "Şifre güncellenemedi: ${it.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                    ?.addOnFailureListener {
                        Toast.makeText(context, "Eski şifre yanlış!", Toast.LENGTH_SHORT).show()
                    }
            },

            colors = ButtonDefaults.buttonColors(containerColor = brown),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Şifreyi Güncelle")
        }
    }
    Divider(thickness = 1.dp)
    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = { navController.navigate("orders") },

        colors = ButtonDefaults.buttonColors(containerColor = brown),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Geçmiş Siparişlerim")
    }

}

