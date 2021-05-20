package com.rubio.movstore.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rubio.movstore.data.models.User
import com.rubio.movstore.domain.usecases.CreateNewUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val createNewUser: CreateNewUser) : ViewModel() {

    val existUser = MutableLiveData<Boolean>()
    val initSetup = MutableLiveData<Boolean>()
    var user: User = User(0)
    val stateLoginInit = MutableLiveData<Boolean>()
    var stateLogin = MutableLiveData<Boolean>()
    var closeSessionState = MutableLiveData<Boolean>()

    fun insertNewUser(user: User) {
        user.let {
            viewModelScope.launch {
                createNewUser.insertNewUser(user, response = {
                    stateLoginInit.postValue(it)
                })
            }
        }
    }

    fun gertUser() {
        viewModelScope.launch {
            createNewUser.gertUser {
                if (it.isNotEmpty()) {
                    user = User(
                        it.get(0).user_id,
                        it.get(0).userName,
                        it.get(0).userDocument,
                        it.get(0).userEmail,
                        it.get(0).userPassword,
                        it.get(0).userLoginState
                    )
                    existUser.value = true
                }else{
                    existUser.value = false
                }
            }
        }
    }

    fun getStateLoginByUser(userId: Int) {
        viewModelScope.launch {
            createNewUser.getStateLoginByUser(userId, response = {
                initSetup.value = it
            })
        }
    }

    fun initSession(userName:String, password:String ){
        viewModelScope.launch {
            createNewUser.initSession(userName, password, response = {
                stateLogin.value = it
            })
        }
    }

    fun closeSession(userName:String, password:String){
        viewModelScope.launch {
            createNewUser.closeSession(userName,password, response = {
                stateLogin.value = false
                closeSessionState.value = it
            })
        }
    }
}