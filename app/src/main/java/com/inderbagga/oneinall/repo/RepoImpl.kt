package com.inderbagga.oneinall.repo

import android.content.Context
import com.inderbagga.oneinall.data.FileDao
import com.inderbagga.oneinall.data.RemoteDao
import com.inderbagga.oneinall.data.model.Post
import com.inderbagga.oneinall.data.repo.Repo
import com.inderbagga.oneinall.utils.Network

class RepoImpl private constructor(val context:Context, private val fileDao: FileDao, private val remoteDao: RemoteDao) : Repo{

    init {
        fileDao.loadFile(context)
    }

    companion object {
        @Volatile private var instance: RepoImpl? = null

        fun getInstance(context:Context,fileDao: FileDao,remoteDao: RemoteDao) =
            instance ?: synchronized(this) {
                instance ?: RepoImpl(context,fileDao,remoteDao).also { instance = it }
            }
    }

    override suspend fun getPosts(): List<Post>? = if(Network.isConnected(context)){
        remoteDao.getPosts()
    }else fileDao.getPosts()
}