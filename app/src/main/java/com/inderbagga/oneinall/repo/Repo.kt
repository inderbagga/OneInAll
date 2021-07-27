package com.inderbagga.oneinall.data.repo

import com.inderbagga.oneinall.data.model.Post

interface Repo {

    suspend fun getPosts() : List<Post>?
}