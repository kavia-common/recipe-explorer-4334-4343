package org.example.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.app.MainViewModel

@Composable
fun FavoritesScreen(nav: MainNav, mainVM: MainViewModel, modifier: Modifier = Modifier) {
    val favorites by mainVM.favoritesLive.observeAsState(emptyList())
    LaunchedEffect(Unit) { mainVM.refreshRecipes() }
    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("Your Favorites", style = MaterialTheme.typography.titleLarge)
        if (favorites.isEmpty()) {
            Text("No favorite recipes saved yet.")
        } else {
            LazyColumn {
                items(favorites.size) { idx ->
                    val recipe = favorites[idx]
                    Card(
                        onClick = { nav.go(MainNavScreen.Detail, recipe.id) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Row(
                            Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            Column(Modifier.weight(1f)) {
                                Text(recipe.title, style = MaterialTheme.typography.titleMedium)
                                Text(recipe.category, color = MaterialTheme.colorScheme.secondary)
                            }
                            IconButton(onClick = { mainVM.setFavorite(recipe.id, false) }) {
                                Icon(Icons.Default.Favorite, "Unfavorite", tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                }
            }
        }
    }
}
