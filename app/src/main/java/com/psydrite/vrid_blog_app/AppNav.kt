package com.psydrite.vrid_blog_app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


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