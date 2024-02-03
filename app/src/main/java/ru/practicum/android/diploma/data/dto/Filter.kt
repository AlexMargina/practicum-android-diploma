package ru.practicum.android.diploma.data.dto

data class Filter(
    val area: String?,
    val pageLimit: Int = 20,
    val showSalary: Boolean,
    val industry: String?,
    val salary: Int?
)
