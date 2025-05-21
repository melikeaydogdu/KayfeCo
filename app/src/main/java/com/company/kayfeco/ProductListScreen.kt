package com.company.kayfeco

import com.company.kayfeco.data.ProductWithCategoryId
import com.company.kayfeco.data.getColdDrinks
import com.company.kayfeco.data.getHotDrinks
import com.company.kayfeco.data.getHotCoffees
import com.company.kayfeco.data.getColdCoffees
import com.company.kayfeco.data.getShakes
import com.company.kayfeco.data.getDesserts


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.res.painterResource
import com.company.kayfeco.data.getColdCoffees
import com.company.kayfeco.data.getDesserts
import com.company.kayfeco.data.getShakes

@Composable
fun ProductListScreen(navController: NavController, categoryId: String) {
    val products = remember {
        when (categoryId) {
            "1" -> getHotCoffees()
            "2" -> getColdCoffees()
            "3" -> getHotDrinks()
            "4" -> getColdDrinks()
            "5" -> getShakes()
            "6" -> getDesserts()
            else -> emptyList()
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Ürünler", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(products.size) { index ->
                val product = products[index]
                ProductCard(product = product, navController = navController)
            }
        }
    }
}

@Composable
fun ProductCard(product: ProductWithCategoryId, navController: NavController) {
    val painter = if (product.imageUrl.isNotBlank()) {
        rememberAsyncImagePainter(model= product.imageUrl)
    } else {
        when (product.title) {
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

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("detail/${product.title}")
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painter,
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .aspectRatio(1f)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(product.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(product.extra, maxLines = 2, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text("${product.price} ₺", color = Color(0xFF4CAF50), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
