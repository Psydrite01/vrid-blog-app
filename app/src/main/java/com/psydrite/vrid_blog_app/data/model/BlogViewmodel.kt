package com.psydrite.vrid_blog_app.data.model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psydrite.vrid_blog_app.data.errorMessage
import com.psydrite.vrid_blog_app.data.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class BlogViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: BlogRepository
): ViewModel() {

    private val _blogList = MutableStateFlow<List<BlogPost>>(
        savedStateHandle.get<List<BlogPost>>("blog_list") ?: emptyList()
    )
    val blogList = _blogList.asStateFlow()


    var latestPage: Int
        get() = savedStateHandle.get<Int>("latest_page") ?: 0
        set(value) {
            savedStateHandle["latest_page"] = value
        }


    private val _isDataFetching = MutableStateFlow(false)
    var isDataFetching= _isDataFetching.asStateFlow()

    private fun updateSavedState(blogList: List<BlogPost>, page: Int) {
        savedStateHandle["blog_list"] = blogList
        savedStateHandle["latest_page"] = page
    }

    fun updateBlogList(newBlogList: List<BlogPost>) {
        _blogList.value = newBlogList
        updateSavedState(newBlogList, latestPage)
    }
    fun addtoBlogList(newBlogList: List<BlogPost>) {
        _blogList.value = _blogList.value + newBlogList
        updateSavedState(_blogList.value, latestPage)
    }

    fun loadPosts(page: Int = 1) {
        if (latestPage >= page) return                // return if already loaded

        var templist = emptyList<BlogPost>()
        viewModelScope.launch {
            _isDataFetching.value = true
            try {
                templist = repository.fetchPosts(page)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }finally {
                if (page<=1){   //first page
                    updateBlogList(templist)
                }else{     //other pages
                    addtoBlogList(templist)
                }
                if (templist.isNotEmpty()){
                    latestPage = page          //update latest page
                    errorMessage = ""          //reset var on successful loading
                }
                _isDataFetching.value = false
            }
        }
    }
}
