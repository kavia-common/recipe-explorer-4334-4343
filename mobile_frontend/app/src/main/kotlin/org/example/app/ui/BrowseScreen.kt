package org.example.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.app.MainViewModel

@Composable
fun BrowseScreen(nav: MainNav, mainVM: MainViewModel, modifier: Modifier = Modifier) {
    val categories by mainVM.categoriesLive.observeAsState(emptyList())
    val recipes by mainVM.recipesLive.observeAsState(emptyList())
    var selectedCat by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) { mainVM.refreshRecipes() }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text("Categories", style = MaterialTheme.typography.titleLarge)
        LazyRow {
            items(categories.size) { idx ->
                val cat = categories[idx]
                FilterChip(
                    selected = selectedCat == cat,
                    onClick = { selectedCat = cat },
                    label = { Text(cat) },
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
        Spacer(Modifier.height(12.dp))
        Divider()
        Spacer(Modifier.height(10.dp))
        val itemsToShow = if (selectedCat == null) recipes else recipes.filter { it.category == selectedCat }
        LazyColumn {
            items(itemsToShow.size) { idx ->
                val recipe = itemsToShow[idx]
                Card(
                    onClick = { nav.go(MainNavScreen.Detail, recipe.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(Modifier.weight(1f)) {
                            Text(recipe.title, style = MaterialTheme.typography.titleMedium)
                            Text(recipe.category, color = MaterialTheme.colorScheme.secondary)
                        }
                        if (recipe.isFavorite) {
                            Icon(Icons.Default.Favorite, "Favorite")
                        }
                    }
                }
            }
        }
    }
}
