package com.rubio.movstore.domain.usecases

import com.rubio.movstore.data.models.UserParcelable
import com.rubio.movstore.data.repository.login.LoginRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkClass
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CreateNewUserTest {

    private var createNewUserTest: CreateNewUser? = null
    private lateinit var loginRepository: LoginRepository
    private lateinit var user: UserParcelable

    @BeforeEach
    fun setUp() {
        user = mockk()
        loginRepository = mockkClass(LoginRepository::class)
        createNewUserTest = CreateNewUser(loginRepository)
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun insertNewUser() {
        val expec = false
        var response = false
        coEvery {
            createNewUserTest?.insertNewUser(user, response = {
                response = it
            })
        } returns Unit

        assertEquals(expec, response)
    }

    @Test
    fun gertUser() {
        var response: List<UserParcelable>? = null
        val expected = ArrayList<Any>()
        coEvery { createNewUserTest?.gertUser { response = it } } returns Unit
        assertEquals(expected, response)
    }

    @Test
    fun getStateLoginByUser() {

    }

    @Test
    fun initSession() {
    }

    @Test
    fun closeSession() {
    }
}