package com.bimobelajar.mynoterev.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bimobelajar.mynoterev.data.Note
import com.bimobelajar.mynoterev.databinding.NoteItemBinding

class NoteAdapter(private val onEdit: (Note) -> Unit, private val onDelete: (Note) -> Unit) :
    ListAdapter<Note, NoteAdapter.NoteViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
        }
    }

    inner class NoteViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.noteTitle.text = note.title
            binding.noteContent.text = note.content
            binding.editButton.setOnClickListener { onEdit(note) }
            binding.deleteButton.setOnClickListener { onDelete(note) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }
}
