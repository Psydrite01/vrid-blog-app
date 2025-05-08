package com.psydrite.vrid_blog_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.psydrite.vrid_blog_app.ui.screens.BlogList


@Composable
fun AppNav(
    navController: NavHostController
) {
    val NavController = navController

    NavHost(
        navController = NavController,
        startDestination = "BlogList") {

        composable("BlogList") {
            BlogList()
        }
    }
}