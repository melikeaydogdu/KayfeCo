// Gerekli Compose importları
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.company.kayfeco.CartManager
import com.company.kayfeco.R
import com.company.kayfeco.data.ProductWithCategoryId

@Composable
fun ProductDetailScreen(
    product: ProductWithCategoryId,
    onBack: () -> Unit,
    onFavoriteToggle: () -> Unit,
    isFavorite: Boolean
) {
    var selectedSize by remember { mutableStateOf("S") }
    val sizePriceModifier = when (selectedSize) {
        "M" -> 5
        "L" -> 10
        else -> 0
    }
    val totalPrice = product.price + sizePriceModifier

    // GÖRSEL seçim bloğu
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

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Geri ve Favori
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Geri",
                    tint = Color.Black
                )
            }
            var isFavoriteLocal by remember { mutableStateOf(isFavorite) }

            IconButton(onClick = {
                isFavoriteLocal = !isFavoriteLocal
                onFavoriteToggle()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.favorite_white),
                    contentDescription = "Favori",
                    tint = if (isFavoriteLocal) Color.Red else Color.Gray
                )
            }

        }

        Spacer(modifier = Modifier.height(8.dp))

        // ÜRÜN GÖRSELİ
        Image(
            painter = painter,
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ÜRÜN BİLGİLERİ
        Text(product.title, style = MaterialTheme.typography.headlineSmall)
        Text(product.extra, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        Text("₺$totalPrice", style = MaterialTheme.typography.titleMedium, color = Color(0xFF4CAF50))

        Spacer(modifier = Modifier.height(16.dp))

        // DETAYLI AÇIKLAMA
        Text(product.description, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(24.dp))

        // BOYUT SEÇİMİ
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            listOf("S", "M", "L").forEach { size ->
                OutlinedButton(
                    onClick = { selectedSize = size },
                    border = if (selectedSize == size)
                        BorderStroke(2.dp, Color(0xFF4CAF50)) else null
                ) {
                    Text(size)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // SATIN AL BUTONU
        val context = LocalContext.current

        Button(
            onClick = {
                    CartManager.addToCart(product, selectedSize)
                    Toast.makeText(context, "Ürün sepete eklendi!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sepete Ekle")
        }

    }
}
