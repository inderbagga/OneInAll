package com.inderbagga.oneinall.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.inderbagga.oneinall.R
import com.inderbagga.oneinall.data.model.Post
import com.inderbagga.oneinall.data.repo.Repo
import com.inderbagga.oneinall.databinding.ActivityMainBinding
import com.inderbagga.oneinall.ui.adapter.PostsAdapter
import com.inderbagga.oneinall.ui.viewmodel.PostsViewModel
import com.inderbagga.oneinall.ui.viewmodel.PostsViewModelFactory
import com.inderbagga.oneinall.utils.Asset
import com.inderbagga.oneinall.utils.Network

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PostsViewModel
    private lateinit var postsAdapter: PostsAdapter
    private lateinit var binding: ActivityMainBinding

    private var jsonPosts:String?= null
    private var listPosts:List<Post>?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivity)
            postsAdapter=PostsAdapter()
        }.also {
            it.adapter=postsAdapter
        }

        viewModel=ViewModelProvider(this,PostsViewModelFactory(Repo())).get(PostsViewModel::class.java)

        viewModel.posts.observe(this,  {

            it?.let {

                postsAdapter.submitList(it)
                viewModel.isLoading.postValue(false)
                binding.progressBar.visibility= View.GONE
                supportActionBar?.subtitle=resources.getString(R.string.remote_data_msg)
            }
        })

        if(Network.isConnected(this)){
            viewModel.getPosts()
            supportActionBar?.subtitle=resources.getString(R.string.fetching_remote_data_msg)
        }else {
            jsonPosts= Asset.getJsonDataFromAsset(this,"posts.json")

            listPosts=jsonPosts?.run {

                val gson = Gson()
                val listPostType = object : TypeToken<List<Post>>() {}.type
                gson.fromJson(this, listPostType)
            }

            viewModel.posts.value=listPosts
            supportActionBar?.subtitle=resources.getString(R.string.file_json_msg)
        }

        binding.viewModel=this.viewModel
        binding.lifecycleOwner=this
    }
}