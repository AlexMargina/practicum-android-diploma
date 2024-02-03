package ru.practicum.android.diploma.data.sharing

import ru.practicum.android.diploma.data.dto.FiltersDto

interface FiltersStorage {
    fun doRequest(): FiltersDto
    fun doWrite(filtersDto: FiltersDto)
}
