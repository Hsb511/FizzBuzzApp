package com.example.fizzbuzzapp.data.modules

import com.example.fizzbuzzapp.data.repositories.FormRoomRepository
import com.example.fizzbuzzapp.domain.repositories.FormRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RoomRepositoriesModule {
    @Binds
    abstract fun bindFormRepository(formRoomRepository: FormRoomRepository): FormRepository
}