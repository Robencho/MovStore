package com.rubio.movstore.domain.usecases

import com.rubio.movstore.data.models.UserParcelable
import com.rubio.movstore.data.repository.login.LoginRepository
import javax.inject.Inject

class CreateNewUser @Inject constructor(private val loginRepository:LoginRepository) {

    suspend fun insertNewUser(user:UserParcelable, response: (data: Boolean) -> Unit){
        loginRepository.insertNewUser(user, response = {
            response(it)
        })
    }

    suspend fun gertUser(response:(data:List<UserParcelable>)->Unit){
        var respon = listOf<UserParcelable>()
        loginRepository.getUser {
            respon = it
        }
            response(respon)
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

    suspend fun closeSession(userName:String, password:String, response: (data: Boolean) -> Unit){
        loginRepository.closeSession(userName, password, response = {
            response(it)
        })
    }
}