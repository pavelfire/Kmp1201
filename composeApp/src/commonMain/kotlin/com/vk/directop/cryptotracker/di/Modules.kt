package com.vk.directop.cryptotracker.di

import com.vk.directop.cryptotracker.core.data.HttpClientFactory
import com.vk.directop.cryptotracker.data.networking.RemoteCoinDataSource
import com.vk.directop.cryptotracker.domain.CoinDataSource
import com.vk.directop.cryptotracker.presentation.coin_list.CoinListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
//    single { RemoteCoinDataSource(get()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
}