package com.arielherndz.myblog.domain.auth

import com.arielherndz.myblog.data.remote.auth.LoginDataSource
import com.google.firebase.auth.FirebaseUser

class LoginRepoImpl(private val dataSource: LoginDataSource): LoginRepo {

    override suspend fun sigIn(email: String, password: String): FirebaseUser? = dataSource.signIn(email,password)
}