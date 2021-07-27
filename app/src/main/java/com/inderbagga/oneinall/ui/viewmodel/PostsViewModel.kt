package com.inderbagga.oneinall.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inderbagga.oneinall.data.model.Post
import com.inderbagga.oneinall.data.repo.Repo
import kotlinx.coroutines.launch

class PostsViewModel(private val repository: Repo) : ViewModel() {

    val postsLiveData: MutableLiveData<List<Post>> = MutableLiveData()

    fun getPosts(){

        viewModelScope.launch {

            postsLiveData.value=repository.getPosts()

        }
    }

}