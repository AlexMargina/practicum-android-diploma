package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.Country
import ru.practicum.android.diploma.domain.models.Filter
import ru.practicum.android.diploma.domain.models.Region
import ru.practicum.android.diploma.presentation.util.Resource

interface FilterInteractor {
    fun load(): Filter?
    fun write(filter: Filter)
    fun getCountries(): Flow<Resource<List<Country>>>
    fun applyCountryFilter(country: Country)
    fun getSelectedCountry(): Country
    fun clearCountryFilter()
    //
    fun getRegions(countryId: String): Flow<Resource<List<Region>>>
    fun applyRegionFilter(region: Region)
    fun searchRegionByName(regionName: String): Flow<Resource<List<Region>>>
    fun getSelectedRegion(): Region
    fun clearRegionFilter()
}
