package com.inderbagga.oneinall.utils

import android.content.Context
import com.inderbagga.oneinall.data.DataSource
import com.inderbagga.oneinall.repo.RepoImpl
import com.inderbagga.oneinall.ui.viewmodel.PostsViewModelFactory

object Injector {

    fun providePostsViewModelFactory(context: Context): PostsViewModelFactory {
        val dataSource=DataSource.getInstance()
        val repository = RepoImpl.getInstance(context,dataSource.fileDao,dataSource.remoteDao)
        return PostsViewModelFactory(repository)
    }
}