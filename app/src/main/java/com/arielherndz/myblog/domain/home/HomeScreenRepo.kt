package com.arielherndz.myblog.domain.home

import com.arielherndz.myblog.core.Resource
import com.arielherndz.myblog.data.model.Post

interface HomeScreenRepo {
    suspend fun getLasterPost(): Resource<List<Post>>
}