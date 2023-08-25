package com.example.myhouse.data.di

import com.example.myhouse.data.network.MyHouseRepository
import com.example.myhouse.data.network.MyHouseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun binMyHouseRepository(repo: MyHouseRepositoryImpl): MyHouseRepository
}
