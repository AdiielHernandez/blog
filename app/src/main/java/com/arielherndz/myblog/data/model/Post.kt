package com.arielherndz.myblog.data.model

import com.google.firebase.Timestamp


data class Post(
    val profile_pictures: String = "",
    val profile_name: String = "",
    val post_timestamp: Timestamp? = null,
    val post_image: String = "")