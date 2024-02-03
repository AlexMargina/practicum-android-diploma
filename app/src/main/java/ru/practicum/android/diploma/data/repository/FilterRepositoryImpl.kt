package ru.practicum.android.diploma.data.repository


import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.data.dto.Filter
import ru.practicum.android.diploma.domain.api.FilterRepository

const val FILTER_STORAGE = "filter_storage"
const val FILTER_KEY = "filter_key"

class FilterRepositoryImpl(val sharedPreferences: SharedPreferences) : FilterRepository {
    override fun load(): Filter? {
        val json = sharedPreferences.getString(FILTER_KEY, null)
        return Gson().fromJson(json, Filter::class.java)
    }

    override fun write(filter: Filter) {
        val json = Gson().toJson(filter)
        sharedPreferences.edit()
            .putString(FILTER_KEY, json)
            .apply()
    }
}
