package com.inderbagga.oneinall.di

import android.content.Context
import com.inderbagga.oneinall.data.dao.FileDao
import com.inderbagga.oneinall.data.dao.FileDaoImpl
import com.inderbagga.oneinall.data.dao.RemoteDao
import com.inderbagga.oneinall.data.dao.RemoteDaoImpl
import com.inderbagga.oneinall.data.repo.Repo
import com.inderbagga.oneinall.repo.RepoImpl
import com.inderbagga.oneinall.ui.viewmodel.PostsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Module{

    @Singleton
    @Provides
    fun provideFileDao(fileDaoImpl: FileDaoImpl): FileDao= FileDaoImpl()

    @Singleton
    @Provides
    fun provideRemoteDao(remoteDaoImpl: RemoteDaoImpl): RemoteDao = RemoteDaoImpl()

    @Singleton
    @Provides
    fun provideRepo(@ApplicationContext context: Context, fileDao: FileDao, remoteDao: RemoteDao): Repo = RepoImpl(context,fileDao,remoteDao)

    @Singleton
    @Provides
    fun provideViewModelFactory(repo: Repo)= PostsViewModelFactory(repo)
}