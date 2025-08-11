package org.example.app.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepository(
    private val recipeDao: RecipeDao,
    private val noteDao: NoteDao
) {
    // PUBLIC_INTERFACE
    suspend fun getRecipes(): List<Recipe> = withContext(Dispatchers.IO) {
        recipeDao.getAll()
    }
    // PUBLIC_INTERFACE
    suspend fun getCategories(): List<String> = withContext(Dispatchers.IO) {
        recipeDao.getAll().map { it.category }.distinct().sorted()
    }
    // PUBLIC_INTERFACE
    suspend fun getByCategory(category: String): List<Recipe> = withContext(Dispatchers.IO) {
        recipeDao.getByCategory(category)
    }
    // PUBLIC_INTERFACE
    suspend fun getRecipeDetail(id: Long): Recipe? = withContext(Dispatchers.IO) {
        recipeDao.getById(id)
    }
    // PUBLIC_INTERFACE
    suspend fun addRecipe(recipe: Recipe): Long = withContext(Dispatchers.IO) {
        recipeDao.insert(recipe)
    }
    // PUBLIC_INTERFACE
    suspend fun updateRecipe(recipe: Recipe) = withContext(Dispatchers.IO) {
        recipeDao.update(recipe)
    }
    // PUBLIC_INTERFACE
    suspend fun setFavorite(id: Long, isFavorite: Boolean) = withContext(Dispatchers.IO) {
        recipeDao.setFavorite(id, isFavorite)
    }
    // PUBLIC_INTERFACE
    suspend fun getFavorites(): List<Recipe> = withContext(Dispatchers.IO) {
        recipeDao.getFavorites()
    }
    // PUBLIC_INTERFACE
    suspend fun search(query: String): List<Recipe> = withContext(Dispatchers.IO) {
        recipeDao.search(query)
    }

    // PUBLIC_INTERFACE
    suspend fun getNotesForRecipe(recipeId: Long): List<Note> = withContext(Dispatchers.IO) {
        noteDao.getNotesForRecipe(recipeId)
    }

    // PUBLIC_INTERFACE
    suspend fun addNote(note: Note): Long = withContext(Dispatchers.IO) {
        noteDao.insert(note)
    }

    // PUBLIC_INTERFACE
    suspend fun deleteNote(note: Note) = withContext(Dispatchers.IO) {
        noteDao.delete(note)
    }
}
