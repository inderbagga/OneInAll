package com.inderbagga.oneinall.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inderbagga.oneinall.data.repo.Repo
import javax.inject.Inject

class PostsViewModelFactory @Inject constructor(private val repository: Repo)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostsViewModel(repository) as T
    }
}