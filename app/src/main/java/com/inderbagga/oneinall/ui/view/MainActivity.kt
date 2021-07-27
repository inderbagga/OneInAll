package com.inderbagga.oneinall.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.inderbagga.oneinall.R
import com.inderbagga.oneinall.data.model.Post
import com.inderbagga.oneinall.ui.adapter.PostsAdapter
import com.inderbagga.oneinall.utils.AssetManager

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    var jsonPosts:String?= null
    var listPosts:List<Post>?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recyclerView)
        progressBar=findViewById(R.id.progressBar)

        jsonPosts=AssetManager.getJsonDataFromAsset(this,"posts.json")

        listPosts=jsonPosts?.run {

            val gson = Gson()
            val listPostType = object : TypeToken<List<Post>>() {}.type
            gson.fromJson(this, listPostType)
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=PostsAdapter(listPosts as ArrayList<Post>)

        }.also {
            it.visibility=View.VISIBLE
            progressBar.visibility= View.GONE
        }
    }
}

