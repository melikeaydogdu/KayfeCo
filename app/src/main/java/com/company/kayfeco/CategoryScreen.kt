package com.company.kayfeco

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class Category(
    val id: String,
    val name: String,
    val icon: Painter
)

@Composable
fun CategoryScreen(navController: NavController) {
    val context = LocalContext.current
    val categories = listOf(
        Category("4", "Soğuk İçecekler", painterResource(id = R.drawable.ic_1)),
        Category("3", "Sıcak İçecekler", painterResource(id = R.drawable.ic_2)),
        Category("1", "Sıcak Kahveler", painterResource(id = R.drawable.ic_3)),
        Category("2", "Soğuk Kahveler", painterResource(id = R.drawable.ic_4)),
        Category("5", "Shakeler", painterResource(id = R.drawable.ic_6)),
        Category("6", "Tatlılar", painterResource(id = R.drawable.ic_9)),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("KATEGORİLER", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(categories) { category ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("products/${category.id}")
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .size(100.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = category.icon,
                                contentDescription = category.name,
                                modifier = Modifier.size(64.dp),
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(category.name)
                }
            }
        }
    }
}

