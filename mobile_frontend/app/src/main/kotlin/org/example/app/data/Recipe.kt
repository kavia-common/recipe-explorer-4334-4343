package org.example.app.data

import androidx.room.*

/**
 * Room entity for recipes.
 */
@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val category: String,
    val instructions: String,
    val imageRes: String? = null,
    val isFavorite: Boolean = false
)

@Entity(
    tableName = "notes",
    foreignKeys = [ForeignKey(
        entity = Recipe::class,
        parentColumns = ["id"],
        childColumns = ["recipeId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value= ["recipeId"])]
)
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val recipeId: Long,
    val content: String
)

data class RecipeWithNotes(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val notes: List<Note>
)
