package br.com.fiap.appmail.model

import androidx.compose.ui.graphics.vector.ImageVector

data class MyNavigationDrawer(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)