package com.bimobelajar.mynoterev.data
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note): Long

    @Update
    fun update(note: Note): Int
    @Delete
    fun delete(note: Note): Int
}

