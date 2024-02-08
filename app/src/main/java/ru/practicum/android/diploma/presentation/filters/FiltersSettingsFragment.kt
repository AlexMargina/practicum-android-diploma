package ru.practicum.android.diploma.presentation.filters

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentFiltersSettingsBinding
import ru.practicum.android.diploma.domain.models.Area
import ru.practicum.android.diploma.domain.models.Country
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.presentation.filters.viewmodel.FiltersSettingsViewModel

class FiltersSettingsFragment : Fragment() {
    private var _binding: FragmentFiltersSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FiltersSettingsViewModel by viewModel()
    private var country: String? = null
    private var area: String? = null

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFiltersSettingsBinding.inflate(inflater, container, false)
        initClickListeners()
        initClickListenersNav()
        initObservers()
        initTextChangedListeners()
        viewModel.loadData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedCountry = arguments?.getString(SelectWorkplaceFragment.SELECTED_COUNTRY)
        val selectedRegion = arguments?.getString(SelectWorkplaceFragment.SELECTED_REGION)
        val combinedText = if (!selectedRegion.isNullOrEmpty()) {
            "$selectedCountry, $selectedRegion"
        } else {
            selectedCountry.orEmpty()
        }
        binding.workplaceEditText.setText(combinedText)
    }

    private fun initClickListenersNav() {
        binding.filterSettingsHeaderBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.workplaceForward.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFiltersFragment_to_selectWorkplaceFragment)
        }
        binding.industryForward.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFiltersFragment_to_selectIndustryFragment)
        }
    }

    private fun initClickListeners() {
        binding.checkboxNoSalary.setOnClickListener {
            viewModel.saveSalaryCheckBox(binding.checkboxNoSalary.isChecked)
        }

        binding.workplaceClear.setOnClickListener {
            (binding.workplaceEditText as TextView).text = ""
            viewModel.compareFilters()
        }

        binding.industryClear.setOnClickListener {
            (binding.industryEditText as TextView).text = ""
            viewModel.compareFilters()
        }

        binding.salaryClear.setOnClickListener {
            (binding.salaryEditText as TextView).text = ""
            viewModel.saveExpectedSalary("0")
        }

        binding.bottonSettingsSave.setOnClickListener {
            viewModel.saveFiltersToSharedPrefs()
            findNavController().popBackStack()
        }
        binding.bottonSettingsReset.setOnClickListener {
            (binding.salaryEditText as TextView).text = ""
            (binding.industryEditText as TextView).text = ""
            (binding.workplaceEditText as TextView).text = ""
            viewModel.resetFilters()
        }
    }

    private fun initObservers() {
        viewModel.plainFiltersData.observe(viewLifecycleOwner) {
            if (it != null) {
                it.notShowWithoutSalary?.let { it1 -> renderCheckbox(it1) }
                it.expectedSalary?.let { it1 -> renderExpectedSalary(it1) }
            } else {
                renderCheckbox(false)
                renderExpectedSalary(-1)
            }
        }
        viewModel.countryData.observe(viewLifecycleOwner) {
            setCountryValue(it)
        }
        viewModel.areaData.observe(viewLifecycleOwner) {
            setAreaValue(it)
        }
        viewModel.industryData.observe(viewLifecycleOwner) {
            renderIndustryTextView(it)
        }
        viewModel.equalFilter.observe(viewLifecycleOwner) {
            renderBottonApply(it)
        }
        viewModel.changedFilter.observe(viewLifecycleOwner) {
            renderBottonApply(it)
        }
    }

    private fun initTextChangedListeners() {
        binding.salaryEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.saveExpectedSalary(s.toString())
            }

            override fun afterTextChanged(s: Editable?) = Unit
        })

        binding.workplaceEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                showClearBottonWorkplace()
            }

            override fun afterTextChanged(s: Editable?) = Unit
        })

        binding.industryEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                showClearBottonIndustry()
            }

            override fun afterTextChanged(s: Editable?) = Unit
        })

        binding.salaryEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                showClearBottonSalary()
            }

            override fun afterTextChanged(s: Editable?) = Unit
        })
    }

    private fun showClearBottonWorkplace() {
        val text: String = (binding.workplaceEditText as TextView).text.toString()
        if (text.isNotEmpty()) {
            binding.workplaceForward.visibility = View.GONE
            binding.workplaceClear.visibility = View.VISIBLE
        } else {
            binding.workplaceForward.visibility = View.VISIBLE
            binding.workplaceClear.visibility = View.GONE
        }
    }

    private fun showClearBottonIndustry() {
        val text: String = (binding.industryEditText as TextView).text.toString()
        if (text.isNotEmpty()) {
            binding.industryForward.visibility = View.GONE
            binding.industryClear.visibility = View.VISIBLE
        } else {
            binding.industryForward.visibility = View.VISIBLE
            binding.industryClear.visibility = View.GONE
        }
    }

    private fun showClearBottonSalary() {
        val text: String = (binding.salaryEditText as TextView).text.toString()
        if (text.isNotEmpty()) {
            binding.salaryClear.visibility = View.VISIBLE
        } else {
            binding.salaryClear.visibility = View.GONE
        }
    }

    private fun setCountryValue(value: Country?) {
        if (value != null) {
            country = value.name
        }
        renderWorkplaceTextView()
        viewModel.equalFilter
    }

    private fun setAreaValue(value: Area?) {
        if (value != null) {
            area = value.name
        }
        renderWorkplaceTextView()
        viewModel.equalFilter
    }

    private fun renderWorkplaceTextView() {
        if (country?.isNotEmpty() == true) {
            (binding.workplaceEditText as TextView).text = country
            if (area?.isNotEmpty() == true) {
                val text = "$country, $area"
                (binding.workplaceEditText as TextView).text = text
            }
        }
    }

    private fun renderIndustryTextView(value: Industry?) {
        if (value != null) {
            (binding.industryEditText as TextView).text = value.name
        }
    }

    private fun renderExpectedSalary(salary: Int) {
        val oldValue = (binding.salaryEditText as TextView).text.toString()
        if (salary > 0 && oldValue != salary.toString()) {
            (binding.salaryEditText as TextView).text = salary.toString()
        }
    }

    private fun renderCheckbox(isChecked: Boolean) {
        binding.checkboxNoSalary.isChecked = isChecked
    }

    private fun renderBottonReset(isChange: Boolean){
        val isAreaOrIndusrtyNotEmpty = binding.workplaceEditText.text.toString().isNotEmpty()
            || binding.industryEditText.text.toString().isNotEmpty()
        val isSalaryNotEmpty = binding.salaryEditText.text.toString().isNotEmpty()
        val isCheckboxChecked = binding.checkboxNoSalary.isChecked

        if (isAreaOrIndusrtyNotEmpty || isSalaryNotEmpty || isCheckboxChecked) {
            binding.bottonSettingsReset.visibility = View.VISIBLE
        } else {
            binding.bottonSettingsReset.visibility = View.GONE
        }
    }

    private fun renderBottonApply(isChange: Boolean) {
        binding.bottonSettingsSave.isVisible = isChange
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadFromShared()
        viewModel.loadData()
        viewModel.compareFilters()
    }

    companion object {
        private val TAG = FiltersSettingsFragment::class.java.simpleName
    }
}
