package com.psydrite.vrid_blog_app.ui.screens

import android.text.Layout.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.psydrite.vrid_blog_app.data.GlobalBlogList
import com.psydrite.vrid_blog_app.data.model.BlogViewModel
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun BlogList(){
    var viewModel : BlogViewModel = viewModel()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {    // load the data first time on entering
        viewModel.loadPosts(1)
    }

    LaunchedEffect(listState) {   // trigger next page load when reaching the end
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .distinctUntilChanged()
            .collect { lastVisibleItemIndex ->
                if (lastVisibleItemIndex != null) {
                    if (lastVisibleItemIndex >= GlobalBlogList.lastIndex - 3) {  // -3 so that loading is faster
                        viewModel.loadPosts(GlobalBlogList.size / 10 + 1)
                    }
                }
            }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ){
        Spacer(Modifier.height(50.dp))
        LazyColumn(state = listState) {
            itemsIndexed(GlobalBlogList) { index, blog ->
                BlogCard(index, blog)
            }
        }
    }
}