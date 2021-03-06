package com.rubio.movstore.data.datasource.local.userdao

import androidx.room.*
import com.rubio.movstore.data.models.UserParcelable

@Dao
interface UserDao {
    @Transaction
    suspend fun updateUsers(user: UserParcelable, response:(data:Boolean)->Unit){
        insertUser(user)
        val stateLogin = getSession(user.userName.toString())
        response(stateLogin)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserParcelable)

    @Query("select * from user_table")
    suspend fun getUser(): List<UserParcelable>

    @Query("select user_login_state from user_table where user_id =:userId")
    suspend fun getLoginState(userId:Int?):Boolean

    @Query("UPDATE user_table set user_login_state = 1 where user_name =:userName and user_password =:passWord")
    suspend fun initSession(userName:String, passWord:String)

    @Query("select user_login_state from user_table where user_name =:userName")
    suspend fun getSession(userName:String):Boolean

    @Query("UPDATE user_table set user_login_state = 0 where user_name = :userName and user_password =:password")
    suspend fun closeSession(userName:String, password:String)
}