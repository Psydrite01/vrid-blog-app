package com.psydrite.vrid_blog_app.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.psydrite.vrid_blog_app.data.model.BlogViewModel

@Composable
fun BlogList(){
    Text("this is Homepage")
    var viewModel : BlogViewModel = viewModel()
    viewModel.loadPosts(1)
}