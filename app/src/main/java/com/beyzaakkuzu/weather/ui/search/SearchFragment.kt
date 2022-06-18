package com.beyzaakkuzu.weather.ui.search

import android.annotation.SuppressLint
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.beyzaakkuzu.weather.R
import com.beyzaakkuzu.weather.core.BaseFragment
import com.beyzaakkuzu.weather.databinding.FragmentSearchBinding
import com.beyzaakkuzu.weather.db.entity.CitiesForSearchEntity
import com.beyzaakkuzu.weather.domain.usecase.SearchCitiesUseCase
import com.beyzaakkuzu.weather.ui.main.MainActivity
import com.beyzaakkuzu.weather.ui.search.result.SearchResultAdapter
import com.beyzaakkuzu.weather.utils.extensions.hideKeyboard
import com.beyzaakkuzu.weather.utils.extensions.observeWith
import com.beyzaakkuzu.weather.utils.extensions.tryCatch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(
    R.layout.fragment_search,
    SearchViewModel::class.java
) {
    override fun init() {
        super.init()

        initSearchResultsAdapter()
        initSearchView()
        binding.viewModel?.getSearchViewState()?.observeWith(viewLifecycleOwner){
            binding.viewState = it
            it.data?.let { results -> initSearchResultsRecyclerView(results) }
        }

    }

    private fun initSearchView() {
        val searchEditText: EditText =
            binding.searchView.findViewById(androidx.appcompat.R.id.search_src_text)
        activity?.applicationContext?.let { ContextCompat.getColor(it, R.color.mainTextColor) }
            ?.let { searchEditText.setTextColor(it) }
        activity?.applicationContext?.let {
            ContextCompat.getColor(
                it,
                android.R.color.darker_gray
            )
        }
            ?.let { searchEditText.setHintTextColor(it) }
        binding.searchView.isActivated = true
        binding.searchView.setIconifiedByDefault(false)
        binding.searchView.isIconified = false

        val searchViewSearchIcon =
            binding.searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchViewSearchIcon.setImageResource(R.drawable.ic_search)

        binding.searchView.setOnQueryTextListener(
            object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(newText: String): Boolean {
                    if (newText.isNotEmpty() && newText.count() > 2) {
                        binding.viewModel?.setSearchParams(
                            SearchCitiesUseCase.SearchCitiesParams(newText)
                        )
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText?.isNotEmpty() == true && newText.count() > 2) {
                        binding.viewModel.setSearchParams(
                            SearchCitiesUseCase.SearchCitiesParams(newText)
                        )
                    }
                    return true
                }
            }
        )
    }

    @SuppressLint("CheckResult")
    private fun initSearchResultsAdapter() {
        val adapter = SearchResultAdapter { item ->
            item.coord?.let {
                binding.viewModel?.saveCoordsToSharedPref(it)
                    ?.subscribe { _, _ ->
                        tryCatch(tryBlock = {
                            hideKeyboard(activity as MainActivity)
                        })
                        findNavController().navigate(R.id.action_searchFragment_to_dashboardFragment)
                    }
            }
        }
        binding.rVSearchResult.adapter = adapter
        binding.rVSearchResult.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,false
        )

    }
    private fun initSearchResultsRecyclerView(list:List<CitiesForSearchEntity>){
        (binding.rVSearchResult.adapter as SearchResultAdapter).submitList(
            list.distinctBy { it.getFullName() }.sortedBy { it.importance }
        )
    }
}