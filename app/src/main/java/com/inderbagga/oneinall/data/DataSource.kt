package com.inderbagga.oneinall.data

class DataSource private constructor() {

    var fileDao = FileDao()
        private set

    var remoteDao = RemoteDao()
        private set

    companion object {
        @Volatile private var instance: DataSource? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: DataSource().also { instance = it }
            }
    }
}