package com.rubio.movstore.data.repository.login

import com.rubio.movstore.data.datasource.local.userdao.UserDao
import com.rubio.movstore.data.models.UserParcelable
import javax.inject.Inject

class LoginRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insertNewUser(user: UserParcelable, response: (data: Boolean) -> Unit) {
        userDao.updateUsers(user, response = {
            response(it)
        })
    }

    suspend fun getUser(response: (data: List<UserParcelable>) -> Unit) {
        response(userDao.getUser())
    }

    suspend fun getStateLoginByUser(userId: Int?, response: (data: Boolean) -> Unit) {
        response(userDao.getLoginState(userId))
    }

    suspend fun initSession(userName: String, password: String, response: (data: Boolean) -> Unit) {
        userDao.initSession(userName, password)
        response(userDao.getSession(userName))
    }

    suspend fun closeSession(userName:String, password:String, response: (data: Boolean) -> Unit){
       userDao.closeSession(userName, password)
        response(userDao.getSession(userName))
    }

}