package com.inderbagga.oneinall.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.inderbagga.oneinall.R
import com.inderbagga.oneinall.databinding.ActivityMainBinding
import com.inderbagga.oneinall.ui.adapter.PostsAdapter
import com.inderbagga.oneinall.ui.viewmodel.PostsViewModel
import com.inderbagga.oneinall.utils.Injector

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PostsViewModel
    private lateinit var postsAdapter: PostsAdapter
    private lateinit var binding: ActivityMainBinding

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

        viewModel=ViewModelProvider(this,Injector.providePostsViewModelFactory(this)).get(PostsViewModel::class.java)

        viewModel.posts.observe(this,  {

            it?.let {

                postsAdapter.submitList(it)
                viewModel.isLoading.postValue(false)
                viewModel.status.postValue(3)
                binding.progressBar.visibility= View.GONE
            }
        })

        viewModel.getPosts()

        viewModel.status.observe(this,{
            it?.let{

                supportActionBar?.apply {

                    when(it){
                        1 -> this.subtitle=resources.getString(R.string.fetching_remote_data_msg)
                        2 -> this.subtitle=resources.getString(R.string.file_json_msg)
                        3 -> this.subtitle=resources.getString(R.string.remote_data_msg)
                    }
                }
            }
        })

        binding.viewModel=this.viewModel
        binding.lifecycleOwner=this
    }
}