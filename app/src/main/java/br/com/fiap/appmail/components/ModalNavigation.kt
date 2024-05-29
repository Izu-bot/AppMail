package br.com.fiap.appmail.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.appmail.modal.MyNavigationDrawer
import br.com.fiap.appmail.vvm.EnviarEmailScreen
import br.com.fiap.appmail.vvm.HomeScreen
import br.com.fiap.appmail.vvm.TelaScreen
import kotlinx.coroutines.launch

val itemPrincipal = listOf(
    MyNavigationDrawer(
        label = "Home",
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Outlined.Home,
        route = "home"
    ),
    MyNavigationDrawer(
        label = "Escreva",
        selectedIcon = Icons.Default.Create,
        unselectedIcon = Icons.Outlined.Create,
        route = "enviar"
    ),
    MyNavigationDrawer(
        label = "Criar marcador",
        selectedIcon = Icons.Default.AddCircle,
        unselectedIcon = Icons.Outlined.AddCircle,
        route = "tela"
    ),
    MyNavigationDrawer(
        label = "Calendario",
        selectedIcon = Icons.Default.DateRange,
        unselectedIcon = Icons.Outlined.DateRange,
        route = "tela"
    ),
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalNavigation() {
    val navController = rememberNavController()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    ModalNavigationDrawer(
        modifier = Modifier.fillMaxSize(),
        drawerContent = {
            ModalDrawerSheet {
                Text(text = "App Mail", modifier = Modifier.padding(16.dp))
                Divider()
                Spacer(modifier = Modifier.height(16.dp))
                itemPrincipal.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                            Text(text = item.label)
                        },
                        selected = index == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                            navController.navigate(item.route)
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.label
                            )
                        },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "App Mail")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            },
            content = {
                // Adicione seu conteúdo aqui
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(70.dp))
                        NavHost(
                            navController = navController,
                            startDestination = "home",
                            modifier = Modifier.fillMaxSize()
                        ) {
                            composable(route = "home") { HomeScreen(navController) }
                            composable(route = "enviar") { EnviarEmailScreen() }
                            composable(route = "tela") { TelaScreen() }
                        }
                    }
                }
            }
        )
    }
}