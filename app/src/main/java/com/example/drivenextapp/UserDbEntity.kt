package com.example.drivenextapp
import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDbEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "email")
    var email: String?,
    @ColumnInfo(name = "password")
    val password: String?,
    @ColumnInfo(name = "lastname")
    val lastname: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "surname")
    val surname: String?,
    @ColumnInfo(name = "gender")
    val gender: String?,
    @ColumnInfo(name = "avatar")
    val avatarUri: String?
)