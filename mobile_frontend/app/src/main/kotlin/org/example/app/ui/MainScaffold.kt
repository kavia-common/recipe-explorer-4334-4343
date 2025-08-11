package org.example.app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.app.MainViewModel

@Composable
fun MainScaffold(
    nav: MainNav,
    mainVM: MainViewModel
) {
    val screens = listOf(
        NavItem("Home", Icons.Default.Home, MainNavScreen.Home),
        NavItem("Browse", Icons.Default.Search, MainNavScreen.Browse),
        NavItem("Favorites", Icons.Default.Favorite, MainNavScreen.Favorites),
        NavItem("Create", Icons.Default.Add, MainNavScreen.Create),
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                tonalElevation = 3.dp,
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ) {
                screens.forEach { screen ->
                    NavigationBarItem(
                        selected = nav.current == screen.screen,
                        onClick = { nav.go(screen.screen) },
                        icon = { Icon(imageVector = screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (nav.current) {
            MainNavScreen.Home -> HomeScreen(nav, mainVM, Modifier.padding(innerPadding))
            MainNavScreen.Browse -> BrowseScreen(nav, mainVM, Modifier.padding(innerPadding))
            MainNavScreen.Favorites -> FavoritesScreen(nav, mainVM, Modifier.padding(innerPadding))
            MainNavScreen.Create -> CreateEditScreen(nav, mainVM, Modifier.padding(innerPadding), isEdit = false)
            MainNavScreen.Detail -> nav.detailRecipeId?.let {
                RecipeDetailScreen(nav, mainVM, it, Modifier.padding(innerPadding))
            }
            MainNavScreen.Edit -> nav.detailRecipeId?.let {
                CreateEditScreen(nav, mainVM, Modifier.padding(innerPadding), isEdit = true, id = it)
            }
            MainNavScreen.Notes -> nav.detailRecipeId?.let {
                NotesScreen(nav, mainVM, it, Modifier.padding(innerPadding))
            }
            MainNavScreen.Search -> SearchScreen(nav, mainVM, Modifier.padding(innerPadding))
        }
    }
}

data class NavItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val screen: MainNavScreen)
