package org.example.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.app.MainViewModel
import org.example.app.data.Note

@Composable
fun NotesScreen(nav: MainNav, mainVM: MainViewModel, recipeId: Long, modifier: Modifier = Modifier) {
    var noteInput by remember { mutableStateOf("") }
    val notes by mainVM.notesLive.observeAsState(emptyList())

    LaunchedEffect(recipeId) { mainVM.loadNotes(recipeId) }

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Your Notes", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = noteInput,
            onValueChange = { noteInput = it },
            label = { Text("Add a note") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Button(
            enabled = noteInput.isNotBlank(),
            onClick = {
                mainVM.addNote(recipeId, noteInput)
                noteInput = ""
            }) {
            Text("Add Note")
        }
        Spacer(Modifier.height(16.dp))
        Divider()
        Spacer(Modifier.height(10.dp))
        if (notes.isEmpty()) Text("No notes yet.")
        LazyColumn {
            items(notes.size) { idx ->
                val note = notes[idx]
                ListItem(
                    headlineContent = { Text(note.content) },
                    trailingContent = {
                        IconButton(onClick = { mainVM.deleteNote(note) }) {
                            Icon(Icons.Default.Delete, "Delete")
                        }
                    }
                )
                Divider()
            }
        }
    }
}
