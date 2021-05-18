package com.rubio.movstore.domain.usecases

import com.rubio.movstore.data.models.User
import com.rubio.movstore.domain.repositories.login.LoginRepository
import javax.inject.Inject

class CreateNewUser @Inject constructor(private val loginRepository:LoginRepository) {

    suspend fun insertNewUser(user:User, response: (data: Boolean) -> Unit){
        loginRepository.insertNewUser(user, response = {
            response(it)
        })
    }

    suspend fun gertUser(response:(data:List<User>)->Unit){
        loginRepository.getUser {
            response(it)
        }
    }
    suspend fun getStateLoginByUser(userId:Int?, response: (data: Boolean) -> Unit){
        loginRepository.getStateLoginByUser(userId, response = {
            response(it)
        })
    }

    suspend fun initSession(userName:String, password:String, response: (data: Boolean) -> Unit){
        loginRepository.initSession(userName, password,response = {
            response(it)
        })
    }
}