package com.company.kayfeco

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun PaymentScreen(navController: NavController) {
    val context = LocalContext.current
    val cartItems = CartManager.getCartItems()
    val totalPrice = CartManager.getTotalPrice()

    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }

    val auth = FirebaseAuth.getInstance()
    val userId = auth.currentUser?.uid
    val db = FirebaseFirestore.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Ödeme Bilgileri", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("İsim") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = surname,
            onValueChange = { surname = it },
            label = { Text("Soyisim") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cardNumber,
            onValueChange = { if (it.length <= 10) cardNumber = it },
            label = { Text("Kart Numarası") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Text("Toplam Tutar: ₺$totalPrice", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Siparişe Eklenecek Ürünler:", style = MaterialTheme.typography.titleSmall)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            items(cartItems.size) { index ->
                Text("- ${cartItems[index].product.title} (${cartItems[index].quantity} adet)")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.isBlank() || surname.isBlank() || cardNumber.length != 10) {
                    Toast.makeText(context, "Tüm alanları doldurun ve kart numarasını 10 haneli girin!", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                val orderData = hashMapOf(
                    "userId" to userId,
                    "name" to name,
                    "surname" to surname,
                    "cardNumber" to cardNumber,
                    "items" to cartItems.map {
                        mapOf(
                            "title" to it.product.title,
                            "quantity" to it.quantity,
                            "price" to it.unitPrice
                        )
                    },
                    "totalPrice" to totalPrice,
                    "timestamp" to Timestamp.now()
                )

                db.collection("orders")
                    .add(orderData)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Sipariş başarıyla alındı!", Toast.LENGTH_SHORT).show()
                        CartManager.clearCart()
                        navController.navigate("categories") {
                            popUpTo("payment") { inclusive = true }
                        }
                    }
                    .addOnFailureListener {
                        Log.e("FIRESTORE", "Sipariş kaydedilemedi: ${it.message}")
                        Toast.makeText(context, "Sipariş gönderilemedi: ${it.message}", Toast.LENGTH_SHORT).show()
                    }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Siparişi Onayla")
        }
    }
}

