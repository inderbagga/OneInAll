package com.inderbagga.oneinall.remote

import com.inderbagga.oneinall.data.model.Post
import retrofit2.http.GET

interface API {

    @GET("posts")
    suspend fun getPosts(): List<Post>?
}