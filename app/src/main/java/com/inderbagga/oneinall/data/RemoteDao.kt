package com.inderbagga.oneinall.data

import com.inderbagga.oneinall.data.model.Post
import com.inderbagga.oneinall.remote.ApiClient

class RemoteDao {

     suspend fun getPosts(): List<Post>?
            = ApiClient.service.getPosts()
}