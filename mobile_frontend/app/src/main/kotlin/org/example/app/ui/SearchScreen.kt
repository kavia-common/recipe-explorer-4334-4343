package org.example.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.app.MainViewModel

@Composable
fun SearchScreen(nav: MainNav, mainVM: MainViewModel, modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }
    val results by mainVM.searchLive.observeAsState(emptyList())

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Search Recipes", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(
            value = query,
            onValueChange = { query = it; mainVM.search(query) },
            label = { Text("Search") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            items(results.size) { idx ->
                val recipe = results[idx]
                Card(
                    onClick = { nav.go(MainNavScreen.Detail, recipe.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text(recipe.title, style = MaterialTheme.typography.titleMedium)
                        Text(recipe.category, color = MaterialTheme.colorScheme.secondary)
                    }
                }
            }
        }
    }
}
