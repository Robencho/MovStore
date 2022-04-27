package com.rubio.movstore.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserParcelable(
    @PrimaryKey(autoGenerate = true) val user_id: Int,

    @ColumnInfo(name = "user_name")
    val userName: String? = "",

    @ColumnInfo(name = "user_document")
    val userDocument: Int? = 0,

    @ColumnInfo(name = "user_email")
    val userEmail: String? = "",

    @ColumnInfo(name = "user_password")
    val userPassword: String? = "",

    @ColumnInfo(name = "user_login_state")
    val userLoginState:Boolean? = false
) {
}