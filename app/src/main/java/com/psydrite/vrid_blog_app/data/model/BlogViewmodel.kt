package com.psydrite.vrid_blog_app.data.model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psydrite.vrid_blog_app.data.GlobalBlogList
import com.psydrite.vrid_blog_app.data.LatestPage
import com.psydrite.vrid_blog_app.data.repository.BlogRepository
import kotlinx.coroutines.launch

class BlogViewModel : ViewModel() {
    var isLoading by mutableStateOf(false)

    private val repository = BlogRepository()

    fun loadPosts(page: Int = 1) {

        if (page == 1) GlobalBlogList = emptyList()   // clear the list if first page load
        if (LatestPage >= page) return                // return if already loaded

        viewModelScope.launch {
            isLoading = true
            try {
                GlobalBlogList += repository.fetchPosts(page)
            } catch (e: Exception) {
                Log.d("BlogViewModel", "err:${e.toString()}}")
            }finally {
                LatestPage = page
                isLoading = false
            }
        }
    }
}
