package com.bimobelajar.mynoterev.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bimobelajar.mynoterev.databinding.FragmentNoteBinding
import com.bimobelajar.mynoterev.viewmodel.NoteViewModel
import com.bimobelajar.mynoterev.data.Note

class NoteFragment : Fragment() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: FragmentNoteBinding
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)

        noteAdapter = NoteAdapter(
            onEdit = { note -> /* Navigate to edit */ },
            onDelete = { note -> noteViewModel.delete(note) }
        )
        binding.noteRecyclerView.adapter = noteAdapter

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.allNotes.observe(viewLifecycleOwner, {
            noteAdapter.submitList(it)
        })

        binding.addNoteButton.setOnClickListener {
            // Navigate to add note fragment
        }

        return binding.root
    }
}
