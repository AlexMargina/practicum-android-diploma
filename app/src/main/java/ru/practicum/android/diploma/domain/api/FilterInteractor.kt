package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.data.dto.Filter

interface FilterInteractor {
    fun load(): Filter?
    fun write(filter: Filter)
}
