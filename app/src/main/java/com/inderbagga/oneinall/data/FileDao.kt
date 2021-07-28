package com.inderbagga.oneinall.data

import android.content.Context
import com.inderbagga.oneinall.data.model.Post

interface FileDao {

    suspend fun loadFile(context: Context)

    suspend fun getPosts(): List<Post>?
}