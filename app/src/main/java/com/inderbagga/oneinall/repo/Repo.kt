package com.inderbagga.oneinall.data.repo

import com.inderbagga.oneinall.data.model.Post
import com.inderbagga.oneinall.remote.ApiClient

class Repo {

    suspend fun getPosts() : List<Post> =
        ApiClient.service.getPosts()
}