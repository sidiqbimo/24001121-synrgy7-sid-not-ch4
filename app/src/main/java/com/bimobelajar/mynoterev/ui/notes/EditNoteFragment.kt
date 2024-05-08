package com.bimobelajar.mynoterev.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.bimobelajar.mynoterev.R
import com.bimobelajar.mynoterev.data.Note
import com.bimobelajar.mynoterev.data.NoteDao
import com.bimobelajar.mynoterev.data.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditNoteFragment : Fragment() {
    private var noteId: Int? = null
    private lateinit var noteDao:NoteDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        noteDao = NoteDatabase.getDatabase(requireContext()).noteDao()

        val view = inflater.inflate(R.layout.fragment_edit_note, container, false)

        val noteTitleEditText = view.findViewById<EditText>(R.id.etNoteTitle)
        val noteContentEditText = view.findViewById<EditText>(R.id.etNoteContent)
        val saveButton = view.findViewById<Button>(R.id.btnSave)

        noteId = arguments?.getInt("noteId")

        noteId?.let { id ->
            lifecycleScope.launch {
                val note = getNoteById(id)

                // Populate the EditText views with the note data
                noteTitleEditText.setText(note?.title)
                noteContentEditText.setText(note?.content)
            }
        }


        saveButton.setOnClickListener {
            val updatedTitle = noteTitleEditText.text.toString()
            val updatedContent = noteContentEditText.text.toString()

            // Update the note with the new data
            noteId?.let { id ->
                lifecycleScope.launch {
                    updateNoteData(id, updatedTitle, updatedContent)
                }
            }

            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }

    private suspend fun getNoteById(noteId: Int): Note? {
        return withContext(Dispatchers.Default) {
            noteDao.getNoteById(noteId)
        }
    }

    private suspend fun updateNoteData(noteId: Int, title: String, content: String) {
        withContext(Dispatchers.Default) {
            val updatedNote = Note(noteId, title, content)
            noteDao.update(updatedNote)
        }
    }
}