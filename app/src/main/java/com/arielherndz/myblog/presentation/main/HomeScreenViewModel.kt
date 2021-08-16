package com.arielherndz.myblog.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.arielherndz.myblog.core.Result
import com.arielherndz.myblog.domain.home.HomeScreenRepo
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class HomeScreenViewModel (private val repo: HomeScreenRepo):ViewModel() {

    fun fecthLatesrposts() = liveData(Dispatchers.IO){

        emit(Result.Loading())
        try {
            emit(repo.getLasterPost())

        }catch (e:Exception){
            emit(Result.Failure(e))

        }
    }
}
// se impplementa la vista
class HomeScreenViewModelFactory(private val repo: HomeScreenRepo): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return modelClass.getConstructor(HomeScreenRepo::class.java).newInstance(repo)
    }
}
