package org.example.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.app.MainViewModel

@Composable
fun HomeScreen(nav: MainNav, mainVM: MainViewModel, modifier: Modifier = Modifier) {
    LaunchedEffect(Unit) { mainVM.refreshRecipes() }
    val recipes by mainVM.recipesLive.observeAsState(emptyList())
    val favorites by mainVM.favoritesLive.observeAsState(emptyList())

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text("Featured Recipes", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        LazyRow {
            items(recipes.take(5)) { recipe ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .width(180.dp)
                        .padding(end = 12.dp)
                        .clickable { nav.go(MainNavScreen.Detail, recipe.id) },
                    elevation = CardDefaults.cardElevation(5.dp)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(recipe.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))
                        Text(recipe.category, color = MaterialTheme.colorScheme.secondary)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(10.dp))
        Text("Your Favorites", style = MaterialTheme.typography.titleLarge)
        if (favorites.isEmpty()) Text("No favorites yet.")
        favorites.forEach { recipe ->
            ListItem(
                headlineContent = { Text(recipe.title) },
                trailingContent = {
                    IconButton(onClick = { nav.go(MainNavScreen.Detail, recipe.id) }) {
                        Icon(Icons.Default.Star, "View")
                    }
                }
            )
        }
    }
}
