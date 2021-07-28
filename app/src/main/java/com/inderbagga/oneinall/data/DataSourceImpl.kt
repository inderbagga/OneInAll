package com.inderbagga.oneinall.data

class DataSourceImpl :DataSource {

    override val fileDao: FileDao
            = FileDaoImpl()

    override val remoteDao: RemoteDao
            = RemoteDaoImpl()

    companion object {
        @Volatile private var instance: DataSourceImpl? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: DataSourceImpl().also { instance = it }
            }
    }
}