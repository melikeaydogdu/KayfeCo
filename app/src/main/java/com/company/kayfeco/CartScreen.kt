package com.company.kayfeco

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun CartScreen(navController: NavController) {
    val cartItems = CartManager.getCartItems()
    val totalPrice by remember(cartItems) { derivedStateOf { CartManager.getTotalPrice() } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sepet") }
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Toplam: ₺${totalPrice}", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { navController.navigate("paymentscreen") },

                    colors = ButtonDefaults.buttonColors(containerColor = brown),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ödeme Yap")
                }
            }
        }
    ) { padding ->
        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Sepetiniz boş.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(cartItems) { item ->
                    CartItemCard(item)
                }
            }
        }
    }
}

@Composable
fun CartItemCard(item: CartItem) {
    var quantity by remember { mutableStateOf(item.quantity) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = if (item.product.imageUrl.isNotBlank()) {
                rememberAsyncImagePainter(item.product.imageUrl)
            } else {
                when (item.product.title) {
                    "Portakal Suyu" -> painterResource(R.drawable.portakal_suyu)
                    "Çilekli Limonata" -> painterResource(R.drawable.cilekli_limonata)
                    "Çay" -> painterResource(R.drawable.cay)
                    "Iced Flat White" -> painterResource(R.drawable.iced_flat_white)
                    "Cold Brew" -> painterResource(R.drawable.cold_brew)
                    "Iced Americano" -> painterResource(R.drawable.iced_americano)
                    "Iced Mocha" -> painterResource(R.drawable.iced_mocha)
                    "Iced Latte" -> painterResource(R.drawable.ice_latte)
                    "Çilekli Milkshake" -> painterResource(R.drawable.cilekli_milkshake)
                    "Mangolu Frozen" -> painterResource(R.drawable.mangolu_frozen)
                    "Magnolia" -> painterResource(R.drawable.d_magnolia)
                    "Fırında Sütlaç" -> painterResource(R.drawable.d_sutlac)
                    "Trileçe" -> painterResource(R.drawable.d_trilece)
                    "Cheesecake" -> painterResource(R.drawable.d_cheesecake)
                    else -> painterResource(R.drawable.placeholder)
                }
            }

            Image(
                painter = painter,
                contentDescription = item.product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .aspectRatio(1f)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(item.product.title, style = MaterialTheme.typography.titleMedium)
                Text("Beden: ${item.size}", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                Text("₺${item.unitPrice * item.quantity}", style = MaterialTheme.typography.bodyMedium, color = Color(0xFF4CAF50))
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    CartManager.decreaseQuantity(item)
                    quantity = item.quantity
                }) {
                    Text("-")
                }
                Text("$quantity", style = MaterialTheme.typography.bodyMedium)
                IconButton(onClick = {
                    CartManager.increaseQuantity(item)
                    quantity = item.quantity
                }) {
                    Text("+")
                }
            }
        }
    }
}
