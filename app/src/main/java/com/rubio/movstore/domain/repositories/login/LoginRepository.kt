package com.rubio.movstore.domain.repositories.login

import com.rubio.movstore.data.db.userdao.UserDao
import com.rubio.movstore.data.models.User
import javax.inject.Inject

class LoginRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insertNewUser(user: User, response: (data: Boolean) -> Unit) {
        userDao.updateUsers(user, response = {
            response(it)
        })
    }

    suspend fun getUser(response: (data: List<User>) -> Unit) {
        response(userDao.getUser())
    }

    suspend fun getStateLoginByUser(userId: Int?, response: (data: Boolean) -> Unit) {
        response(userDao.getLoginState(userId))
    }

    suspend fun initSession(userName: String, password: String, response: (data: Boolean) -> Unit) {
        userDao.initSession(userName, password)
        response(userDao.getSession(userName))
    }

}