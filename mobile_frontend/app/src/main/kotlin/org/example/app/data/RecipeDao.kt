package org.example.app.data

import androidx.room.*

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes ORDER BY title ASC")
    suspend fun getAll(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE category=:category ORDER BY title ASC")
    suspend fun getByCategory(category: String): List<Recipe>

    @Query("SELECT * FROM recipes WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): Recipe?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe): Long

    @Update
    suspend fun update(recipe: Recipe)

    @Delete
    suspend fun delete(recipe: Recipe)

    @Query("SELECT * FROM recipes WHERE isFavorite = 1 ORDER BY title ASC")
    suspend fun getFavorites(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE title LIKE '%' || :query || '%' OR instructions LIKE '%' || :query || '%'")
    suspend fun search(query: String): List<Recipe>

    @Query("UPDATE recipes SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun setFavorite(id: Long, isFavorite: Boolean)
}

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes WHERE recipeId = :recipeId ORDER BY id DESC")
    suspend fun getNotesForRecipe(recipeId: Long): List<Note>

    @Insert
    suspend fun insert(note: Note): Long

    @Delete
    suspend fun delete(note: Note)
}
