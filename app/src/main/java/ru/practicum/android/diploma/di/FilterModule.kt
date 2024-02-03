package ru.practicum.android.diploma.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import org.koin.dsl.module
import ru.practicum.android.diploma.data.conventers.AreaMapper
import ru.practicum.android.diploma.data.conventers.IndustryMapper
import ru.practicum.android.diploma.data.repository.FILTER_STORAGE
import ru.practicum.android.diploma.data.repository.FilterRepositoryImpl
import ru.practicum.android.diploma.data.repository.IndustryRepositoryImpl
import ru.practicum.android.diploma.domain.api.FilterInteractor
import ru.practicum.android.diploma.domain.api.FilterRepository
import ru.practicum.android.diploma.data.repository.AreaRepositoryImpl
import ru.practicum.android.diploma.data.repository.IndustryRepositoryImpl
import ru.practicum.android.diploma.domain.api.AreaInteractor
import ru.practicum.android.diploma.domain.api.AreaRepository
import ru.practicum.android.diploma.domain.api.IndustryInteractor
import ru.practicum.android.diploma.domain.api.IndustryRepository
import ru.practicum.android.diploma.domain.impl.AreaInteractorImpl
import ru.practicum.android.diploma.domain.impl.IndustryInteractorImpl
import ru.practicum.android.diploma.domain.repository.FilterInteractorImpl

val filterModule = module {

    single<IndustryInteractor> {
        IndustryInteractorImpl(repository = get())
    }

    single<IndustryRepository> {

        IndustryRepositoryImpl(networkClient = get(), context = get(), mapper = get())
    }
    factory { IndustryMapper() }
    
     single<SharedPreferences>() {
        val application = get<Application>()
        application.getSharedPreferences(
            FILTER_STORAGE,
            MODE_PRIVATE
        )
    }

    single<FilterRepository> {
        FilterRepositoryImpl(sharedPreferences = get())
    }

    single<FilterInteractor> {
        FilterInteractorImpl(filterRepository = get())
    }
    
      single<AreaInteractor> {
        AreaInteractorImpl(repository = get())
    }

    single<AreaRepository> {

        AreaRepositoryImpl(networkClient = get(), context = get(), converter = get())
    }
    factory { AreaMapper() }

}
