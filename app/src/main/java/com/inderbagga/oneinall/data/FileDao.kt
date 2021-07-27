package com.inderbagga.oneinall.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.inderbagga.oneinall.data.model.Post
import com.inderbagga.oneinall.utils.Asset

class FileDao {

    var _posts: List<Post>?=null

    fun loadFile(context: Context) {

        var jsonPosts:String?= Asset.getJsonDataFromAsset(context,"posts.json")

        _posts=jsonPosts?.run {

            val gson = Gson()
            val listPostType = object : TypeToken<List<Post>>() {}.type
            gson.fromJson(this, listPostType)
        }
    }

    fun getPosts() = _posts
}