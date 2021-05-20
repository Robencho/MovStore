package com.rubio.movstore.ui.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rubio.movstore.data.models.User
import com.rubio.movstore.domain.usecases.CreateNewUser
import com.rubio.movstore.utils.PreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val createNewUser: CreateNewUser) : ViewModel() {

    val existUser = MutableLiveData<Boolean>()
    val initSetup = MutableLiveData<Boolean>()
    var user: User = User(0)
    val validateSessionInit = MutableLiveData<Boolean>()
    var validateStateSession = MutableLiveData<Boolean>()
    var closeSessionResponse = MutableLiveData<Boolean>()

    fun insertNewUser(user: User, context: Context) {
        user.let {
            viewModelScope.launch {
                createNewUser.insertNewUser(user, response = {
                    PreferencesHelper(context).prefUserName = user.userName.toString()
                    PreferencesHelper(context).prefUserPassword = user.userPassword.toString()
                    validateSessionInit.postValue(it)
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
                } else {
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

    fun initSession(userName: String, password: String) {
        viewModelScope.launch {
            createNewUser.initSession(userName, password, response = {
                validateStateSession.value = it
            })
        }
    }

    fun closeSession(userName: String, password: String) {
        viewModelScope.launch {
            createNewUser.closeSession(userName, password, response = {
                validateStateSession.value = false
                closeSessionResponse.value = it
            })
        }
    }
}