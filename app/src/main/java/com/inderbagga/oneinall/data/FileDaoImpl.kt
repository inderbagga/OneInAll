package com.inderbagga.oneinall.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.inderbagga.oneinall.data.model.Post
import com.inderbagga.oneinall.utils.Asset

class FileDaoImpl : FileDao {

    var _posts: List<Post>?=null

    override suspend fun loadFile(context: Context) {

        var jsonPosts:String?= Asset.getJsonDataFromAsset(context,"posts.json")

        _posts=jsonPosts?.run {

            val gson = Gson()
            val listPostType = object : TypeToken<List<Post>>() {}.type
            gson.fromJson(this, listPostType)
        }
    }

    override suspend fun getPosts() = _posts
}