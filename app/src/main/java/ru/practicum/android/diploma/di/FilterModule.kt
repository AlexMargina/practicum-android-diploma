package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.data.conventers.AreaMapper
import ru.practicum.android.diploma.data.repository.AreaRepositoryImpl
import ru.practicum.android.diploma.domain.api.AreaRepository

val filterModule = module {

    single<AreaRepository> {
        AreaRepositoryImpl(networkClient = get(), context = get(), converter = get())
    }

    factory { AreaMapper() }
}
