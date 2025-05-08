package com.psydrite.vrid_blog_app

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BlogViewModel : ViewModel() {
    var blogList by mutableStateOf<List<BlogPost>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    private val repository = BlogRepository()

    fun loadPosts(page: Int = 1) {
        viewModelScope.launch {
            isLoading = true
            try {
                blogList = repository.fetchPosts(page)
                Log.d("BlogViewModel", "Fetching posts:${blogList.toString()}")
            } catch (e: Exception) {
                Log.d("BlogViewModel", "err:${e.toString()}}")
            }
            isLoading = false
        }
    }
}
