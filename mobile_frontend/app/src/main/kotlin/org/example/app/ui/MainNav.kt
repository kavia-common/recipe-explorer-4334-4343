package org.example.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

enum class MainNavScreen {
    Home, Browse, Favorites, Create, Detail, Edit, Notes, Search
}

class MainNav(
    var current: MainNavScreen = MainNavScreen.Home,
    var detailRecipeId: Long? = null,
) {
    fun go(screen: MainNavScreen, detailId: Long? = null) {
        current = screen
        detailRecipeId = detailId
    }
}

@Composable
fun rememberMainNav(): MainNav {
    var nav by remember { mutableStateOf(MainNav()) }
    return nav
}
