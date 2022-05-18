package com.chaudharynabin6.notes

import android.text.Editable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(
//    other columns
    @ColumnInfo(name = "note") val note: String,
){
//    primary key
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
