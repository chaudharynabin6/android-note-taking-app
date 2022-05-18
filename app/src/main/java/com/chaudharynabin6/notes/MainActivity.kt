package com.chaudharynabin6.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaudharynabin6.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),NoteListAdapter.INoteListAdapter {
    lateinit var binding: ActivityMainBinding

    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NotesApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        view binding
        this.binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(this.binding.root)

//         recycler view setup
        val recyclerView = findViewById<RecyclerView>(R.id.note_recycler_view)
        val adapter = NoteListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        // observer setup
        noteViewModel.allNotes.observe(  this) { notes ->
            // Update the cached copy of the words in the adapter.
            notes.let { adapter.submitList(it) }
        }


    }
// INoteListAdapter interface implementation
    override fun onClickDeleteButton(note: Note) {
        noteViewModel.delete(note)
        Toast.makeText(this,"${note.note} : Deleted",Toast.LENGTH_SHORT).show()
    }
// onClick addNote handlerZ
    fun addNote(view: View) {
        val text = this.binding.input.text.toString()
        if(text.isNotEmpty()){
            noteViewModel.insert(Note(text))
            Toast.makeText(this,"$text : Added",Toast.LENGTH_SHORT).show()
        }
    }

}