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

class EditNoteFragment : Fragment() {
    private var noteId: Int? = null
    private lateinit var noteDao:NoteDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_edit_note, container, false)

        val noteTitleEditText = view.findViewById<EditText>(R.id.etNoteTitle)
        val noteContentEditText = view.findViewById<EditText>(R.id.etNoteContent)
        val saveButton = view.findViewById<Button>(R.id.btnSave)

        noteId = arguments?.getInt("noteId")

        // Retrieve the note data from the database or any other source
        noteId?.let { id ->
            val note = getNoteById(id)

            // Populate the EditText views with the note data
            noteTitleEditText.setText(note?.title)
            noteContentEditText.setText(note?.content)
        }

        noteDao = NoteDatabase.getDatabase(requireContext()).noteDao()

        // Set up click listener for the save button
        saveButton.setOnClickListener {
            // Retrieve the updated note data from the EditText views
            val updatedTitle = noteTitleEditText.text.toString()
            val updatedContent = noteContentEditText.text.toString()

            // Update the note with the new data
            noteId?.let { id ->
                updateNoteData(id, updatedTitle, updatedContent)
            }

            // Close the fragment
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }

    private fun getNoteById(noteId: Int): Note? {
        return noteDao.getNoteById(noteId)
    }

    private fun updateNoteData(noteId: Int, title: String, content: String) {
        val updatedNote = Note(noteId, title, content)
        noteDao.update(updatedNote)
    }
}