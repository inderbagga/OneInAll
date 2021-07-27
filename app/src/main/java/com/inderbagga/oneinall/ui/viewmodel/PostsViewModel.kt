package com.inderbagga.oneinall.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inderbagga.oneinall.data.model.Post
import com.inderbagga.oneinall.data.repo.Repo
import kotlinx.coroutines.launch

class PostsViewModel(private val repository: Repo) : ViewModel() {

    val posts: MutableLiveData<List<Post>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getPosts(){

        viewModelScope.launch {
            isLoading.postValue(true)
            posts.postValue(repository.getPosts())
        }
    }
}