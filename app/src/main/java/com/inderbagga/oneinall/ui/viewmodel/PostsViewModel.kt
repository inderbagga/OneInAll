package com.inderbagga.oneinall.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inderbagga.oneinall.data.model.Post
import com.inderbagga.oneinall.repo.RepoImpl
import kotlinx.coroutines.launch

class PostsViewModel( private val repo: RepoImpl) : ViewModel() {

    val posts: MutableLiveData<List<Post>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val status: MutableLiveData<Int> = MutableLiveData()

    fun getPosts(){

        viewModelScope.launch {
            isLoading.postValue(true)

            val response=repo.getPosts()
            posts.postValue(response)

        }
    }
}