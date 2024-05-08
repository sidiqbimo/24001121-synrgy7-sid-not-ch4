package com.bimobelajar.mynoterev.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bimobelajar.mynoterev.R
import com.bimobelajar.mynoterev.databinding.FragmentAddNoteBinding
import com.bimobelajar.mynoterev.viewmodel.NoteViewModel
import com.bimobelajar.mynoterev.data.Note

class AddNoteFragment : Fragment() {
    private lateinit var viewModel: NoteViewModel
    private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnAddNote.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            if (title.isNotBlank() && content.isNotBlank()) {
                val note = Note(title = title, content = content)
                viewModel.insert(note)
                Toast.makeText(context, "Catatan berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addNoteFragment_to_noteFragment)
            } else {
                Toast.makeText(context, "Pastikan sudah mengisi judul dan isi catatan", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}
