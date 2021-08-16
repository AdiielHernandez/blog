package com.arielherndz.myblog.domain.home

import com.arielherndz.myblog.core.Result
import com.arielherndz.myblog.data.model.Post
import com.arielherndz.myblog.data.remote.home.HomeScreenDataSource

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource):
    HomeScreenRepo {

    // este metodo va al data source y busca la informacion
    override suspend fun getLasterPost(): Result<List<Post>> = dataSource.getLatersPosts()
}