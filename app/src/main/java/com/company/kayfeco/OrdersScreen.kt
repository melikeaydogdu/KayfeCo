package com.company.kayfeco

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
import com.google.firebase.firestore.Query

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(navController: NavController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val userId = auth.currentUser?.uid

    var orders by remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }

    LaunchedEffect(userId) {
        if (userId != null) {
            firestore.collection("orders")
                .whereEqualTo("userId", userId)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { result ->
                    orders = result.documents.mapNotNull { it.data }
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Siparişler alınamadı: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Geçmiş Siparişler") })
        }
    ) { padding ->
        if (orders.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                if (userId == null) {
                    CircularProgressIndicator()
                } else {
                    Text("Hiç siparişiniz bulunmuyor.")
                }
            }
        }
        else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(orders.size) { index ->
                    val order = orders[index]
                    val total = order["totalPrice"] as? Long ?: 0L
                    val timestamp = order["timestamp"] as? Timestamp
                    val date = timestamp?.toDate()?.toString() ?: "Tarih yok"
                    val items = order["items"] as? List<Map<String, Any>> ?: emptyList()

                    Card {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("Tarih: $date", style = MaterialTheme.typography.titleSmall)
                            Text("Toplam: ₺$total", style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(8.dp))
                            items.forEach {
                                val title = it["title"] as? String ?: "-"
                                val qty = (it["quantity"] as? Long)?.toInt() ?: 0
                                Text("- $title ($qty adet)", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }
        }
    }
}
