package org.example.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.app.MainViewModel

@Composable
fun RecipeDetailScreen(nav: MainNav, mainVM: MainViewModel, id: Long, modifier: Modifier = Modifier) {
    val recipe by mainVM.recipeDetail.observeAsState(null)
    LaunchedEffect(id) { mainVM.loadRecipe(id) }

    recipe?.let { rec ->
        Column(
            Modifier.fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(rec.title, style = MaterialTheme.typography.displaySmall)
            Spacer(Modifier.height(6.dp))
            Text(rec.category, color = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(18.dp))
            Text(rec.instructions, style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(24.dp))
            Row {
                ElevatedButton(
                    onClick = { mainVM.setFavorite(rec.id, !rec.isFavorite) }
                ) {
                    Icon(Icons.Filled.Favorite, "Favorite")
                    Text(if (rec.isFavorite) "Unfavorite" else "Favorite")
                }
                Spacer(Modifier.width(12.dp))
                ElevatedButton(
                    onClick = { nav.go(MainNavScreen.Notes, rec.id) }
                ) {
                    Icon(Icons.Default.Notes, "Notes")
                    Text("Notes")
                }
                Spacer(Modifier.width(12.dp))
                OutlinedButton(
                    onClick = { nav.go(MainNavScreen.Edit, rec.id) }
                ) {
                    Icon(Icons.Default.Edit, "Edit")
                    Text("Edit")
                }
            }
        }
    } ?: Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
