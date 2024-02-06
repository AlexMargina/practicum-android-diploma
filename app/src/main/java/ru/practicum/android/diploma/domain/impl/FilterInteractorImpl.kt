package ru.practicum.android.diploma.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.api.FilterInteractor
import ru.practicum.android.diploma.domain.api.FilterRepository
import ru.practicum.android.diploma.domain.models.Country
import ru.practicum.android.diploma.domain.models.Filter
import ru.practicum.android.diploma.domain.models.Region
import ru.practicum.android.diploma.presentation.util.Resource

class FilterInteractorImpl(private val filterRepository: FilterRepository) : FilterInteractor {
    override fun load(): Filter? {
        return filterRepository.load()
    }
    override fun write(filter: Filter) {
        filterRepository.write(filter)
    }
    override fun getCountries(): Flow<Resource<List<Country>>> {
        TODO(NOT_IMPLEMENTED_YET)
    }
    override fun applyCountryFilter(country: Country) {
        TODO(NOT_IMPLEMENTED_YET)
    }
    override fun getRegions(countryId: String): Flow<Resource<List<Region>>> {
        return filterRepository.getRegions(countryId)
    }
    override fun getSelectedCountry(): Country {
        TODO(NOT_IMPLEMENTED_YET)
    }
    override fun clearCountryFilter() {
        TODO(NOT_IMPLEMENTED_YET)
    }
    override fun applyRegionFilter(region: Region) {
        return filterRepository.applyRegionFilter(region)
    }
    override fun searchRegionByName(regionName: String): Flow<Resource<List<Region>>> {
        return filterRepository.searchRegionByName(regionName)
    }
    override fun getSelectedRegion(): Region {
        return filterRepository.getSelectedRegion()
    }
    override fun clearRegionFilter() {
        return filterRepository.clearRegionFilter()
    }
    companion object {
        private const val NOT_IMPLEMENTED_YET = "Not yet implemented"
    }
}
