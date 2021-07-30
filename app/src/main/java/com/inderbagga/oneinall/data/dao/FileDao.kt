package com.inderbagga.oneinall.data.dao

import android.content.Context
import com.inderbagga.oneinall.data.model.Post

interface FileDao {

    suspend fun loadFile(context: Context)

    suspend fun getPosts(): List<Post>?
}