package org.example.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.app.MainViewModel
import org.example.app.data.Recipe

@Composable
fun CreateEditScreen(
    nav: MainNav,
    mainVM: MainViewModel,
    modifier: Modifier = Modifier,
    isEdit: Boolean,
    id: Long? = null
) {
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var instructions by remember { mutableStateOf("") }
    var wasLoaded by remember { mutableStateOf(false) }

    if (isEdit && id != null && !wasLoaded) {
        LaunchedEffect(id) {
            mainVM.loadRecipe(id)
        }
        val recipe by mainVM.recipeDetail.observeAsState()
        recipe?.let {
            if (!wasLoaded) {
                title = it.title
                category = it.category
                instructions = it.instructions
                wasLoaded = true
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(if (isEdit) "Edit Recipe" else "Create Recipe", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = title, onValueChange = { title = it },
            label = { Text("Title") },
            singleLine = true
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = category, onValueChange = { category = it },
            label = { Text("Category") },
            singleLine = true
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = instructions, onValueChange = { instructions = it },
            label = { Text("Instructions (steps, one per line)") },
            modifier = Modifier.height(150.dp)
        )
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            if (title.isNotBlank() && category.isNotBlank() && instructions.isNotBlank()) {
                val recipe = Recipe(
                    id = id ?: 0L,
                    title = title,
                    category = category,
                    instructions = instructions
                )
                mainVM.addOrUpdateRecipe(recipe, isEdit)
                nav.go(MainNavScreen.Home)
            }
        }) {
            Text(if (isEdit) "Save Changes" else "Create Recipe")
        }
    }
}
