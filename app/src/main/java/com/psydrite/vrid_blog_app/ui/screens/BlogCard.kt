package com.psydrite.vrid_blog_app.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.psydrite.vrid_blog_app.data.formatDate
import com.psydrite.vrid_blog_app.data.model.BlogPost
import com.psydrite.vrid_blog_app.data.model.BlogViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BlogCard(index: Int, blog: BlogPost){
    var viewModel: BlogViewModel = viewModel()
    LaunchedEffect(Unit) {
        viewModel.LoadImage(blog.id)
    }
    Row (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    (index+1).toString(),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground)
                Column (
                    modifier = Modifier.padding(3.dp)
                ){
                    Text(
                        blog.title.rendered.toString(),
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground)
                    Text(formatDate(blog.date_gmt.toString()))
                }
            }
        }
    }
}