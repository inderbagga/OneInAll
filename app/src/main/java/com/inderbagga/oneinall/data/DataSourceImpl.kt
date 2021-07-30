package com.inderbagga.oneinall.data

import com.inderbagga.oneinall.data.dao.FileDao
import com.inderbagga.oneinall.data.dao.FileDaoImpl
import com.inderbagga.oneinall.data.dao.RemoteDao
import com.inderbagga.oneinall.data.dao.RemoteDaoImpl

class DataSourceImpl :DataSource {

    override val fileDao: FileDao
            = FileDaoImpl()

    override val remoteDao: RemoteDao
            = RemoteDaoImpl()
}