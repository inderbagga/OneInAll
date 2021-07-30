package com.inderbagga.oneinall.data.dao

import com.inderbagga.oneinall.data.model.Post
import com.inderbagga.oneinall.remote.ApiClient
import javax.inject.Inject

class RemoteDaoImpl @Inject constructor(): RemoteDao {

     override suspend fun getPosts(): List<Post>? =
          ApiClient.service.getPosts()
}