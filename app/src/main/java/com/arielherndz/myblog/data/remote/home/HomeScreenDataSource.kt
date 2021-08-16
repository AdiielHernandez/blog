package com.arielherndz.myblog.data.remote.home

import com.arielherndz.myblog.core.Result
import com.arielherndz.myblog.data.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class HomeScreenDataSource {

    suspend fun getLatersPosts(): Result<List<Post>> {
        val postList = mutableListOf<Post>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("post").get().await()

        for (post in querySnapshot.documents) {
            // Transforma los document en un pojo
            post.toObject(Post::class.java)?.let {
                postList.add(it)
            }

        }
        return Result.Success(postList)
    }
}