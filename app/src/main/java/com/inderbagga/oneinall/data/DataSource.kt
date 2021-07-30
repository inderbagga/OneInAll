package com.inderbagga.oneinall.data

import com.inderbagga.oneinall.data.dao.FileDao
import com.inderbagga.oneinall.data.dao.RemoteDao

interface DataSource {

    val fileDao: FileDao
    val remoteDao: RemoteDao
}