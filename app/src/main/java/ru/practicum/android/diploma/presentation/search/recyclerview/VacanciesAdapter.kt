package ru.practicum.android.diploma.presentation.search.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.LayoutSearchVacancyBinding
import ru.practicum.android.diploma.databinding.VacancyItemBinding
import ru.practicum.android.diploma.domain.models.Vacancy

class VacanciesAdapter(
    val vacancies: ArrayList<Vacancy>,
    private val clickListener: VacancyClickListener
) : RecyclerView.Adapter<VacancyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        return VacancyViewHolder(
            VacancyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = vacancies.size

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        holder.bind(vacancies[position])
        holder.itemView.setOnClickListener {
            clickListener.onVacancyClick(vacancies.get(position))
        }
    }

    fun interface VacancyClickListener {
        fun onVacancyClick(track: Vacancy)
    }

}
