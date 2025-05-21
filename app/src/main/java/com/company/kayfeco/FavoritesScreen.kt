package com.company.kayfeco

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.company.kayfeco.data.ProductWithCategoryId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavController) {
    val favorites = FavoriteManager.getFavorites()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favoriler") }
            )
        }
    ) { innerPadding ->
        if (favorites.isEmpty()) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("Favorilere eklenmiş ürün bulunamadı.")
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(innerPadding)
            ) {
                items(favorites.size) { index ->
                    val product = favorites[index]
                    FavoriteItem(product, navController)
                }
            }
        }
    }
}
@Composable
fun FavoriteItem(product: ProductWithCategoryId, navController: NavController) {
    val painter = if (product.imageUrl.isNotBlank()) {
        rememberAsyncImagePainter(product.imageUrl)
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
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("detail/${product.title}") },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painter,
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(product.title, style = MaterialTheme.typography.titleMedium)
                Text("${product.price} ₺", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

