package ru.practicum.android.diploma.data.repository

import android.content.SharedPreferences
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.data.dto.FilterDTO
import ru.practicum.android.diploma.domain.api.FilterRepository
import ru.practicum.android.diploma.domain.models.Filter
import ru.practicum.android.diploma.domain.models.Region
import ru.practicum.android.diploma.presentation.util.Resource

const val FILTER_STORAGE = "filter_storage"
const val FILTER_KEY = "filter_key"

class FilterRepositoryImpl(val sharedPreferences: SharedPreferences) : FilterRepository {
    override fun load(): Filter? {
        val json = sharedPreferences.getString(FILTER_KEY, null)
        val filterDto = Gson().fromJson(json, FilterDTO::class.java)
        return Filter(
            area = filterDto.area,
            pageLimit = filterDto.pageLimit,
            industry = filterDto.industry,
            showSalary = filterDto.showSalary,
            salary = filterDto.salary
        )
    }

    override fun write(filter: Filter) {
        val filterDto = Filter(
            area = filter.area,
            pageLimit = filter.pageLimit,
            industry = filter.industry,
            showSalary = filter.showSalary,
            salary = filter.salary
        )
        val json = Gson().toJson(filterDto)
        sharedPreferences.edit()
            .putString(FILTER_KEY, json)
            .apply()
    }

    override fun getRegions(countryId: String): Flow<Resource<List<Region>>> {
        TODO("Not yet implemented")
    }

    override fun applyRegionFilter(region: Region) {
        TODO("Not yet implemented")
    }

    override fun searchRegionByName(regionName: String): Flow<Resource<List<Region>>> {
        TODO("Not yet implemented")
    }

    override fun getSelectedRegion(): Region {
        TODO("Not yet implemented")
    }

    override fun clearRegionFilter() {
        TODO("Not yet implemented")
    }
}
