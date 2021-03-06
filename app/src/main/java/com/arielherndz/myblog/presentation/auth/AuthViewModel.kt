package com.arielherndz.myblog.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.arielherndz.myblog.core.Result
import com.arielherndz.myblog.domain.auth.AuthRepo
import kotlinx.coroutines.Dispatchers

class AuthViewModel(private val repo: AuthRepo): ViewModel() {

    fun signIn(email: String, password: String) = liveData(Dispatchers.IO){
        emit(Result.Loading())

        try {
            emit(Result.Success(repo.sigIn(email,password)))
        }catch (e: Exception){
            emit(Result.Failure(e))
        }
    }

    fun signUp(email: String, password: String, username: String) = liveData(Dispatchers.IO){
        emit(Result.Loading())

        try {
            emit(Result.Success(repo.sigUp(email,password, username)))
        }catch (e: Exception){
            emit(Result.Failure(e))
        }
    }
}

class AuthViewModelFactory(private val repo: AuthRepo): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return AuthViewModel(repo) as T
    }
}