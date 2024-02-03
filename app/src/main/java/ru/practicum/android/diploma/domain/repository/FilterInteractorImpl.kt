package ru.practicum.android.diploma.domain.repository

import ru.practicum.android.diploma.domain.api.FilterInteractor
import ru.practicum.android.diploma.domain.api.FilterRepository
import ru.practicum.android.diploma.data.dto.Filter

class FilterInteractorImpl(private val filterRepository: FilterRepository) : FilterInteractor {
    override fun load(): Filter? {
        return filterRepository.load()
    }

    override fun write(filter: Filter) {
        filterRepository.write(filter)
    }
}
