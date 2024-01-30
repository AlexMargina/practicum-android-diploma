package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.domain.models.Area
import ru.practicum.android.diploma.presentation.util.Resource

interface AreaInteractor {
    suspend fun getCountries(): Resource<List<Area>>
}
