package com.chaudharynabin6.notes

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

// use sql query for Dao implementation
@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table ORDER BY note ASC")
    fun getAlphabetizedNotes(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

}