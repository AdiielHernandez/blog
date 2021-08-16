package com.arielherndz.myblog.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.arielherndz.myblog.core.Resource
import com.arielherndz.myblog.domain.auth.LoginRepo
import kotlinx.coroutines.Dispatchers

class LoginScreenViewModel(private val repo: LoginRepo): ViewModel() {

    fun signIn(email: String, password: String) = liveData(Dispatchers.IO){
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.sigIn(email,password)))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}

class LoginScreenViewModelFactory(private val repo: LoginRepo): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return LoginScreenViewModel(repo) as T
    }
}