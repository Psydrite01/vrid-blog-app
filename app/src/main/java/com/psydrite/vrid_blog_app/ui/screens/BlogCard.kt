package com.psydrite.vrid_blog_app.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.psydrite.vrid_blog_app.data.formatDate
import com.psydrite.vrid_blog_app.data.model.BlogPost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BlogCard(index: Int, blog: BlogPost){
    Row (
        modifier = Modifier.fillMaxWidth()
    ){
        Text(index.toString())
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column (
                modifier = Modifier.padding(3.dp)
            ){
                Text(blog.title.rendered.toString())
                Text(formatDate(blog.date_gmt.toString()))
            }
        }
    }
}