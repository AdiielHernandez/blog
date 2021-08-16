package com.arielherndz.myblog.domain.auth

import com.google.firebase.auth.FirebaseUser

interface LoginRepo {
    suspend fun sigIn(email: String, password: String): FirebaseUser?
    
}