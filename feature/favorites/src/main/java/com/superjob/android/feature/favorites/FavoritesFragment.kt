package com.superjob.android.feature.favorites

import android.annotation.SuppressLint
import android.content.Context
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
import com.superjob.android.core.ui.R
import com.superjob.android.core.ui.base.BaseFragment
import com.superjob.android.core.ui.util.getDeclensionVacancies
import com.superjob.android.feature.favorites.adapter.FavoritesAdapter
import com.superjob.android.feature.favorites.adapter.listener.VacancyFavoriteClickListener
import com.superjob.android.feature.favorites.adapter.listener.VacancyItemClickListener
import com.superjob.android.feature.favorites.adapter.listener.VacancyReactionClickListener
import com.superjob.android.feature.favorites.databinding.FragmentFavoritesBinding
import com.superjob.android.feature.favorites.di.DaggerFavoritesComponent
import com.superjob.android.feature.favorites.di.FavoritesProvider
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class FavoritesFragment:
    BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate),
    VacancyItemClickListener,
    VacancyFavoriteClickListener,
    VacancyReactionClickListener
{
    @Inject
    internal lateinit var viewModelFactory: Lazy<FavoritesViewModel.Factory>
    private val viewModel: FavoritesViewModel by viewModels { viewModelFactory.get() }

    private var adapter: FavoritesAdapter? = null

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onAttach(context: Context) {
        val dependencies = (context.applicationContext as FavoritesProvider).favoritesDependencies
        DaggerFavoritesComponent.builder().dependencies(dependencies).build().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetBehavior = BottomSheetBehavior.from(binding.reactionInclude.reactionBottomSheet)
        adapter = FavoritesAdapter(this, this, this)
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
                        is FavoritesUiState.Success -> {
                            binding.emptyTextView.visibility = View.GONE
                            adapter?.update(state.vacancies)
                            binding.favoritesSizeTextView.visibility = View.VISIBLE
                            binding.favoritesSizeTextView.text = state.vacancies.size.toString() +
                                    " " + getDeclensionVacancies(requireContext(), state.vacancies.size)
                        }
                        FavoritesUiState.Empty -> {
                            adapter?.update(emptyList())
                            binding.favoritesSizeTextView.visibility = View.GONE
                            binding.emptyTextView.visibility = View.VISIBLE
                        }
                    }
                }
        }
    }

    private fun initView() = with(binding) {
        favoritesRecyclerView.layoutManager = LinearLayoutManager(context)
        favoritesRecyclerView.adapter = adapter

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

    override fun onVacancyItemClick(id: String) {
        val request = NavDeepLinkRequest.Builder
            .fromUri((getString(R.string.vacancy_uri) + id).toUri()).build()
        findNavController().navigate(request)
    }

    override fun onVacancyReactionClick(title: String) {
        binding.reactionInclude.titleTextView.text = title
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onFavoriteVacancyClick(id: String) {
        viewModel.updateFavoriteVacancy(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }
}