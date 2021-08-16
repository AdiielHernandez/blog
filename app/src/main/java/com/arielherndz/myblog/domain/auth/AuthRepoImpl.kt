package com.arielherndz.myblog.domain.auth

import com.arielherndz.myblog.data.remote.auth.AuthDataSource
import com.google.firebase.auth.FirebaseUser

class AuthRepoImpl(private val dataSource: AuthDataSource): AuthRepo {

    override suspend fun sigIn(email: String, password: String): FirebaseUser? = dataSource.signIn(email,password)
    override suspend fun sigUp(email: String, password: String, username: String): FirebaseUser? =
        dataSource.signUp(email, password, username)
}