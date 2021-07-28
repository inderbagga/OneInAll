package com.inderbagga.oneinall.utils

import android.content.Context
import com.inderbagga.oneinall.data.DataSourceImpl.Companion.getInstance
import com.inderbagga.oneinall.repo.RepoImpl
import com.inderbagga.oneinall.ui.viewmodel.PostsViewModelFactory

object Injector {

    fun providePostsViewModelFactory(context: Context): PostsViewModelFactory {

        val dataSource= getInstance()
        val fileDao=dataSource.fileDao
        val remoteDao=dataSource.remoteDao
        val repository = RepoImpl.getInstance(context,fileDao,remoteDao)
        return PostsViewModelFactory(repository)
    }
}