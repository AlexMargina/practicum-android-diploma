package ru.practicum.android.diploma.data.sharing

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.data.dto.FiltersDto

class SharedPrefStorage(val sharedPrefrs:SharedPreferences):FiltersStorage {

    private var filters: FiltersDto =
        FiltersDto(countryName = null, countryId = null, areasNames = null, areasId = null, industriesName = null, industriesId = null,  salary = 0, onlyWithSalary = false)

    companion object{
        const val FILTER_KEY = "FILTER_KEY"
    }
    override fun doRequest(): FiltersDto {
        val json = sharedPrefrs.getString(FILTER_KEY, null) ?:return filters
        return Gson().fromJson(json, FiltersDto::class.java)
    }

    override fun doWrite(filtersDto: FiltersDto) {
        val json = Gson().toJson(filtersDto)
        sharedPrefrs.edit().putString(FILTER_KEY, json).apply()
    }
}
