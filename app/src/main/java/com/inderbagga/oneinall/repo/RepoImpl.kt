package com.inderbagga.oneinall.repo

import android.content.Context
import com.inderbagga.oneinall.data.dao.FileDao
import com.inderbagga.oneinall.data.dao.RemoteDao
import com.inderbagga.oneinall.data.model.Post
import com.inderbagga.oneinall.data.repo.Repo
import com.inderbagga.oneinall.utils.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepoImpl @Inject constructor(val context:Context, private val fileDao: FileDao, private val remoteDao: RemoteDao) : Repo{

       init {

        GlobalScope.launch(Dispatchers.IO) {
            fileDao.loadFile(context)
        }
    }

    override suspend fun getPosts(): List<Post>? = if(Network.isConnected(context)){
        remoteDao.getPosts()
    }else fileDao.getPosts()
}