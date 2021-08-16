package com.arielherndz.myblog.domain.home

import com.arielherndz.myblog.core.Result
import com.arielherndz.myblog.data.model.Post

interface HomeScreenRepo {
    suspend fun getLasterPost(): Result<List<Post>>
}