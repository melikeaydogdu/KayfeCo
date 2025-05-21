package com.company.kayfeco

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import androidx.navigation.NavController

@Composable
fun CategoryScreen(navController: NavController) {
    val categories = listOf(
        Category("Sıcak Kahve", R.drawable.ic_3, "1"),
        Category("Soğuk Kahve", R.drawable.ic_4, "2"),
        Category("Sıcak İçecekler", R.drawable.ic_2, "3"),
        Category("Soğuk İçecekler", R.drawable.ic_1, "4"),
        Category("Shakeler", R.drawable.ic_5, "5"),
        Category("Tatlılar", R.drawable.ic_6, "6")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "KATEGORİLER",
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 30.sp,
            color = Color(0xFF4E342E) // kahverengi
        )

        Spacer(modifier = Modifier.height(8.dp))

        categories.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowItems.forEach { category ->
                    CategoryButton(category, onClick = {
                        navController.navigate("products/${category.id}")
                    })
                }
            }
        }
    }
}

@Composable
fun CategoryButton(category: Category, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentSize()
            .clickable { onClick() }
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFFA1887F),
            modifier = Modifier.size(115.dp)
        ) {
            Image(
                painter = painterResource(id = category.icon),
                contentDescription = category.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = category.name,
            fontSize = 16.sp,
            color = Color(0xFF4E342E),
        )
    }
}



data class Category(val name: String, val icon: Int, val id: String)
