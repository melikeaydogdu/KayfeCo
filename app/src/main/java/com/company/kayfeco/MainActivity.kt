package com.company.kayfeco

import ProductDetailScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.company.kayfeco.data.ProductWithCategoryId
import com.company.kayfeco.data.getAllProducts
import com.company.kayfeco.ui.theme.KayfeCoTheme

data class CartItem(
    val product: ProductWithCategoryId,
    var size: String,
    var quantity: Int,
    var unitPrice: Int
)

object CartManager {
    private val cartItems = mutableStateListOf<CartItem>()

    fun addToCart(product: ProductWithCategoryId, size: String) {
        val extraPrice = when (size) {
            "M" -> 5
            "L" -> 10
            else -> 0
        }
        val price = product.price + extraPrice

        // Aynı ürün + aynı beden varsa quantity artır
        val existingItem = cartItems.find { it.product.title == product.title && it.size == size }
        if (existingItem != null) {
            existingItem.quantity += 1
        } else {
            cartItems.add(CartItem(product, size, 1, price))
        }
    }

    fun getCartItems(): List<CartItem> = cartItems

    fun increaseQuantity(item: CartItem) {
        item.quantity += 1
    }

    fun decreaseQuantity(item: CartItem) {
        if (item.quantity > 1) {
            item.quantity -= 1
        } else {
            cartItems.remove(item)
        }
    }

    fun getTotalPrice(): Int {
        return cartItems.sumOf { it.unitPrice * it.quantity }
    }

    fun clearCart() {
        cartItems.clear()
    }
}

object FavoriteManager {
    private val favoriteList = mutableStateListOf<ProductWithCategoryId>()

    fun addToFavorite(product: ProductWithCategoryId) {
        if (!favoriteList.contains(product)) {
            favoriteList.add(product)
        }
    }

    fun removeFromFavorite(product: ProductWithCategoryId) {
        favoriteList.remove(product)
    }

    fun isFavorite(product: ProductWithCategoryId): Boolean {
        return favoriteList.contains(product)
    }

    fun getFavorites(): List<ProductWithCategoryId> = favoriteList
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            KayfeCoTheme {
                val navController = rememberNavController()
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentRoute != "login" && currentRoute != "signup") {
                            BottomBar(navController)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("login") {
                            LoginScreen(navController)
                        }
                        composable("signup") {
                            SignUpScreen(navController)
                        }
                        composable("categories") {
                            CategoryScreen(navController)
                        }
                        composable("products/{categoryId}") { backStackEntry ->
                            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
                            ProductListScreen(navController, categoryId)
                        }
                        composable("detail/{title}") { backStackEntry ->
                            val title = backStackEntry.arguments?.getString("title") ?: ""
                            val product = getAllProducts().find { it.title == title }!!

                            ProductDetailScreen(
                                product = product,
                                onBack = { navController.popBackStack() },
                                onFavoriteToggle = {
                                    if (FavoriteManager.isFavorite(product)) {
                                        FavoriteManager.removeFromFavorite(product)
                                    } else {
                                        FavoriteManager.addToFavorite(product)
                                    }
                                },
                                isFavorite = FavoriteManager.isFavorite(product)
                            )
                        }
                        composable("favorites") {
                            FavoritesScreen(navController)
                        }
                        composable("cart") {
                            CartScreen(navController)
                        }
                        composable("settings") {
                            SettingsScreen(navController)
                        }
                        composable  ("paymentscreen" ){
                        PaymentScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    NavigationBar(
        tonalElevation = 0.dp, // Flat bar
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("categories") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = "Kategoriler",
                    modifier = Modifier.size(45.dp) // ICON BOYUTU KÜÇÜLDÜ
                )
            },
            label = null, // Label yok
            alwaysShowLabel = false
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("favorites") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = "Favoriler",
                    modifier = Modifier.size(45.dp)
                )
            },
            label = null,
            alwaysShowLabel = false
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("cart") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = "Sepet",
                    modifier = Modifier.size(50.dp)
                )
            },
            label = null,
            alwaysShowLabel = false
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("settings") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "Ayarlar",
                    modifier = Modifier.size(40.dp)
                )
            },
            label = null,
            alwaysShowLabel = false
        )
    }
}
