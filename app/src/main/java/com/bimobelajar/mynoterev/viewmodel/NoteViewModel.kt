package com.bimobelajar.mynoterev.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bimobelajar.mynoterev.data.Note
import com.bimobelajar.mynoterev.data.NoteDatabase
import com.bimobelajar.mynoterev.data.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import androidx.databinding.InverseMethod

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>

    // Add newTitle and newContent properties
    private val _newTitle = MutableLiveData<String>()
    val newTitle: LiveData<String>
        get() = _newTitle

    private val _newContent = MutableLiveData<String>()
    val newContent: LiveData<String>
        get() = _newContent

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.allNotes
    }

    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO) { repository.insert(note) }
    fun update(note: Note) = viewModelScope.launch(Dispatchers.IO) { repository.update(note) }
    fun delete(note: Note) = viewModelScope.launch(Dispatchers.IO) { repository.delete(note) }

    fun setNewTitle(title: String) {
        _newTitle.value = title
    }

    fun setNewContent(content: String) {
        _newContent.value = content
    }

    fun convertTitleToString(title: String): String {
        return title
    }

    fun convertContentToString(content: String): String {
        return content
    }
}