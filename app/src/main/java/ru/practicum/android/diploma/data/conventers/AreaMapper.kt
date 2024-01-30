package ru.practicum.android.diploma.data.conventers

import ru.practicum.android.diploma.data.dto.AreaResponse
import ru.practicum.android.diploma.domain.models.Area

class AreaMapper {
    fun map(response: AreaResponse): List<Area> {
        val responseItems = response.items
        val data = responseItems.map {
            Area(
                id = it.id,
                name = it.name,
                parentId = it.parentId,
                countryId = ""
            )
        }
        return data

    }

//    fun mapCity(response: AreaResponse): List<Area> {
//        val responseItems = response.items
//        val nestedCities = response.items.map { it.areas }
//
//        }
//        return data
//
//    }
}
