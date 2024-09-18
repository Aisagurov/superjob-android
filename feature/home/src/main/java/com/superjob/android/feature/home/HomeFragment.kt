package com.superjob.android.feature.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.superjob.android.core.model.Employment
import com.superjob.android.core.model.HomeItem
import com.superjob.android.core.ui.base.BaseFragment
import com.superjob.android.feature.home.databinding.FragmentHomeBinding
import com.superjob.android.feature.home.di.DaggerHomeComponent
import com.superjob.android.feature.home.di.HomeProvider
import com.superjob.android.core.ui.R
import com.superjob.android.feature.home.adapter.home.HomeAdapter
import com.superjob.android.feature.home.adapter.offers.listener.OfferItemClickListener
import com.superjob.android.feature.home.adapter.vacancies.listener.VacanciesClickListener
import com.superjob.android.feature.home.adapter.vacancies.listener.VacancyFavoriteClickListener
import com.superjob.android.feature.home.adapter.vacancies.listener.VacancyItemClickListener
import com.superjob.android.feature.home.adapter.vacancies.listener.VacancyReactionClickListener
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class HomeFragment:
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    OfferItemClickListener,
    VacanciesClickListener,
    VacancyItemClickListener,
    VacancyFavoriteClickListener,
    VacancyReactionClickListener
{
    @Inject
    internal lateinit var viewModelFactory: Lazy<HomeViewModel.Factory>
    private val viewModel: HomeViewModel by viewModels { viewModelFactory.get() }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private var adapter: HomeAdapter? = null

    override fun onAttach(context: Context) {
        val dependencies = (context.applicationContext as HomeProvider).homeDependencies
        DaggerHomeComponent.builder().dependencies(dependencies).build().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetBehavior = BottomSheetBehavior.from(binding.reactionInclude.reactionBottomSheet)
        adapter = HomeAdapter(this, this, this, this, this)
        observeViewModel()
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    when (state) {
                        is HomeUiState.Success -> {
                            binding.errorTextView.visibility = View.GONE
                            binding.progressBar.visibility = View.GONE

                            updateHomeItem(state.isAllVacancies, state.employment)

                            binding.homeLayout.visibility = View.VISIBLE

                            showAllVacancies(state.isAllVacancies)
                        }
                        is HomeUiState.Error -> {
                            binding.homeLayout.visibility = View.GONE
                            binding.progressBar.visibility = View.GONE
                            binding.errorTextView.visibility = View.VISIBLE
                        }
                        HomeUiState.Loading -> {
                            binding.errorTextView.visibility = View.GONE
                            binding.homeLayout.visibility = View.GONE
                            binding.progressBar.visibility = View.VISIBLE
                        }
                    }
            }
        }
    }

    private fun updateHomeItem(isAllVacancies: Boolean, employment: Employment) {
        val offers = if (!isAllVacancies) {
            employment.offers
        } else {
            emptyList()
        }

        val title = if (!isAllVacancies) {
            getString(R.string.vacancies_for_you)
        } else {
            ""
        }

        val vacancies = if (isAllVacancies) {
            employment.vacancies
        } else {
            employment.vacancies.take(3)
        }

        val homeItems = listOf(
            HomeItem(
                offers = offers,
                title = title,
                vacancies = vacancies,
                vacanciesSize = employment.vacancies.size
            )
        )
        adapter?.update(homeItems)
    }

    private fun showAllVacancies(isAllVacancies: Boolean) {
        if (isAllVacancies) {
            binding.searchView.visibility = View.GONE
            binding.allVacanciesSearchView.visibility = View.VISIBLE
            binding.mapsButton.visibility = View.VISIBLE
        } else {
            binding.mapsButton.visibility = View.GONE
            binding.allVacanciesSearchView.visibility = View.GONE
            binding.searchView.visibility = View.VISIBLE
        }
    }

    private fun initView() = with(binding) {
        homeRecyclerView.layoutManager = LinearLayoutManager(context)
        homeRecyclerView.adapter = adapter

        allVacanciesSearchView.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.homeLayout.visibility = View.GONE
            viewModel.setAllVacanciesConfig(false)
        }

        reactionInclude.reactionButton.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }

        reactionInclude.reactionEditText.isFocusable = true

        reactionInclude.addCoverLetterTextView.setOnClickListener {
            reactionInclude.addCoverLetterTextView.visibility = View.GONE
            reactionInclude.reactionEditText.visibility = View.VISIBLE
            reactionInclude.reactionEditText.requestFocus()
        }
    }

    override fun showAllVacancies() {
        binding.progressBar.visibility = View.VISIBLE
        binding.homeLayout.visibility = View.GONE
        viewModel.setAllVacanciesConfig(true)
    }

    override fun onOfferItemClick(link: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        startActivity(intent)
    }

    override fun onVacancyItemClick(id: String) {
        val request = NavDeepLinkRequest.Builder
            .fromUri((getString(R.string.vacancy_uri) + id).toUri()).build()
        findNavController().navigate(request)
    }

    override fun onVacancyFavoriteClick(id: String) {
        viewModel.updateFavoriteVacancy(id)
    }

    override fun onVacancyReactionClick(title: String) {
        binding.reactionInclude.titleTextView.text = title
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }
}