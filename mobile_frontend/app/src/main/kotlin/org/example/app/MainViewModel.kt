package org.example.app

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.example.app.data.*

class MainViewModel(
    private val repository: RecipeRepository
): ViewModel() {
    var recipes = mutableListOf<Recipe>()
    var favorites = mutableListOf<Recipe>()
    var categories = mutableListOf<String>()
    var notes = mutableListOf<Note>()
    var searchResults = mutableListOf<Recipe>()

    val recipesLive = MutableLiveData<List<Recipe>>()
    val favoritesLive = MutableLiveData<List<Recipe>>()
    val categoriesLive = MutableLiveData<List<String>>()
    val notesLive = MutableLiveData<List<Note>>()
    val searchLive = MutableLiveData<List<Recipe>>()
    val recipeDetail = MutableLiveData<Recipe?>()

    // PUBLIC_INTERFACE
    fun refreshRecipes() {
        viewModelScope.launch {
            val list = repository.getRecipes()
            recipes = list.toMutableList()
            recipesLive.value = recipes
            categories = repository.getCategories().toMutableList()
            categoriesLive.value = categories
            favorites = repository.getFavorites().toMutableList()
            favoritesLive.value = favorites
        }
    }
    // PUBLIC_INTERFACE
    fun loadRecipe(id: Long) {
        viewModelScope.launch {
            recipeDetail.value = repository.getRecipeDetail(id)
        }
    }
    // PUBLIC_INTERFACE
    fun setFavorite(id: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.setFavorite(id, isFavorite)
            refreshRecipes()
        }
    }
    // PUBLIC_INTERFACE
    fun addOrUpdateRecipe(recipe: Recipe, isEdit: Boolean) {
        viewModelScope.launch {
            if (isEdit) {
                repository.updateRecipe(recipe)
            } else {
                repository.addRecipe(recipe)
            }
            refreshRecipes()
        }
    }
    // PUBLIC_INTERFACE
    fun search(query: String) {
        viewModelScope.launch {
            searchResults = repository.search(query).toMutableList()
            searchLive.value = searchResults
        }
    }
    // PUBLIC_INTERFACE
    fun loadNotes(recipeId: Long) {
        viewModelScope.launch {
            notes = repository.getNotesForRecipe(recipeId).toMutableList()
            notesLive.value = notes
        }
    }
    // PUBLIC_INTERFACE
    fun addNote(recipeId: Long, content: String) {
        viewModelScope.launch {
            repository.addNote(Note(recipeId = recipeId, content = content))
            loadNotes(recipeId)
        }
    }
    // PUBLIC_INTERFACE
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
            loadNotes(note.recipeId)
        }
    }
}

class MainViewModelFactory(
    private val repository: RecipeRepository
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
