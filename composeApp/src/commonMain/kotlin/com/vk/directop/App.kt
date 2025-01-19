package com.vk.directop

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vk.directop.cryptotracker.Route
import com.vk.directop.cryptotracker.presentation.coin_list.CoinListScreen
import com.vk.directop.cryptotracker.presentation.coin_list.CoinListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {

        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.CoinList
        ) {

            composable<Route.CoinList>(
                exitTransition = { slideOutHorizontally() },
                popEnterTransition = { slideInHorizontally() }
            ) {
                val viewModel = koinViewModel<CoinListViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                CoinListScreen(
                    state = state,
                    onAction = {
                        viewModel.onAction(action = it)
                    }
                )
            }

            composable<Route.CoinDetail>(
                enterTransition = {
                    slideInHorizontally { initialOffset ->
                        initialOffset
                    }
                },
                exitTransition = {
                    slideOutHorizontally { initialOffset ->
                        initialOffset
                    }
                }
            ) {
                Text("ds")
            }
        }
    }
}