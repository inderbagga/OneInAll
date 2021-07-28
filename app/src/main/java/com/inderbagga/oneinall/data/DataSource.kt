package com.inderbagga.oneinall.data

interface DataSource {

    val fileDao: FileDao
    val remoteDao: RemoteDao
}