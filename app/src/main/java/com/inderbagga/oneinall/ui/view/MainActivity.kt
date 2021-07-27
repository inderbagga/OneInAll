package com.inderbagga.oneinall.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.inderbagga.oneinall.R
import com.inderbagga.oneinall.data.model.Post
import com.inderbagga.oneinall.data.repo.Repo
import com.inderbagga.oneinall.ui.adapter.PostsAdapter
import com.inderbagga.oneinall.ui.viewmodel.PostsViewModel
import com.inderbagga.oneinall.ui.viewmodel.PostsViewModelFactory
import com.inderbagga.oneinall.utils.Asset
import com.inderbagga.oneinall.utils.Network

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var postsAdapter: PostsAdapter

    var jsonPosts:String?= null
    var listPosts:List<Post>?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recyclerView)
        progressBar=findViewById(R.id.progressBar)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivity)
            postsAdapter=PostsAdapter(ArrayList())
        }.also {
            it.adapter=postsAdapter
        }

        val viewModel=ViewModelProvider(this,PostsViewModelFactory(Repo())).get(PostsViewModel::class.java)

        viewModel.postsLiveData.observe(this, Observer {

            it?.let {
                postsAdapter.update(it)
                recyclerView.visibility= View.VISIBLE
                progressBar.visibility= View.GONE
            }
        })

        if(Network.isConnected(this)){
            viewModel.getPosts()
            supportActionBar?.subtitle=resources.getString(R.string.remote_data_msg)
        }else {
            jsonPosts= Asset.getJsonDataFromAsset(this,"posts.json")

            listPosts=jsonPosts?.run {

                val gson = Gson()
                val listPostType = object : TypeToken<List<Post>>() {}.type
                gson.fromJson(this, listPostType)
            }

            viewModel.postsLiveData.value=listPosts
            supportActionBar?.subtitle=resources.getString(R.string.file_json_msg)
        }
    }
}

