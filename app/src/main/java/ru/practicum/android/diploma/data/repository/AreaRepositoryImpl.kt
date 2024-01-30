package ru.practicum.android.diploma.data.repository

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.data.conventers.AreaMapper
import ru.practicum.android.diploma.data.dto.AreaResponse
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.domain.api.AreaRepository
import ru.practicum.android.diploma.domain.models.Area
import ru.practicum.android.diploma.presentation.util.Resource

class AreaRepositoryImpl(
    private val networkClient: NetworkClient,
    private val context: Context,
    private val converter: AreaMapper
) : AreaRepository {

    override suspend fun getCountries(): Resource<List<Area>> {
        val response = networkClient.getAreas()
        Log.i("getCountries", "foundVacancies  ${converter.map(response as AreaResponse)}")
        if (response.resultCode == NO_CONNECTION) {
            return Resource.Error(ContextCompat.getString(context, R.string.no_internet))
        }
        if (response.resultCode == SUCCESS) {
            val areas = response as AreaResponse
            return Resource.Success(converter.map(areas))
        } else {
            return Resource.Error("Ошибка ${response.resultCode}")
        }
    }

    override suspend fun getCities(countryId: String): Resource<List<Area>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCitiesAll(): Resource<List<Area>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCountry(countryId: String): String {
        TODO("Not yet implemented")
    }


}
