package com.inderbagga.oneinall.data.dao

import com.inderbagga.oneinall.data.model.Post

interface RemoteDao {

     suspend fun getPosts(): List<Post>?
}