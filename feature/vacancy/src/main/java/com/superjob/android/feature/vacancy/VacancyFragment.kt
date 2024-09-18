package com.superjob.android.feature.vacancy

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.superjob.android.core.model.Vacancy
import com.superjob.android.core.ui.R
import com.superjob.android.core.ui.base.BaseFragment
import com.superjob.android.core.ui.util.getDeclensionHuman
import com.superjob.android.core.ui.util.showToast
import com.superjob.android.feature.vacancy.adapter.QuestionsAdapter
import com.superjob.android.feature.vacancy.databinding.FragmentVacancyBinding
import com.superjob.android.feature.vacancy.di.DaggerVacancyComponent
import com.superjob.android.feature.vacancy.di.VacancyProvider
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class VacancyFragment:
    BaseFragment<FragmentVacancyBinding>(FragmentVacancyBinding::inflate)
{
    @Inject
    internal lateinit var viewModelFactory: Lazy<VacancyViewModel.Factory>
    private val viewModel: VacancyViewModel by viewModels { viewModelFactory.get() }

    private val args: VacancyFragmentArgs by navArgs()

    private var adapter: QuestionsAdapter? = null

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onAttach(context: Context) {
        val dependencies = (context.applicationContext as VacancyProvider).vacancyDependencies
        DaggerVacancyComponent.builder().dependencies(dependencies).build().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetBehavior = BottomSheetBehavior.from(binding.reactionInclude.reactionBottomSheet)
        adapter = QuestionsAdapter()
        observeViewModel()
        initBottomSheet()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getVacancy(args.vacancyId)
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    when (state) {
                        is VacancyUiState.Success -> {
                            adapter?.update(state.vacancy.questions)
                            initView(state.vacancy)
                        }
                        VacancyUiState.Loading -> {
                            Unit
                        }
                    }
                }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initView(item: Vacancy) = with(binding) {
        arrowBackImageView.setOnClickListener {
            findNavController().navigateUp()
        }

        favoriteImageView.setImageResource(
            if (item.isSelected) {
                R.drawable.ic_favorite
            } else {
                R.drawable.ic_favorite_border
            }
        )

        favoriteImageView.setOnClickListener {
            viewModel.updateFavoriteVacancy(item.id)
            if (item.isSelected) {
                requireContext().showToast(
                    requireContext().getString(R.string.vacancy_removed_from_favorites)
                )
            } else {
                requireContext().showToast(
                    requireContext().getString(R.string.vacancy_added_to_favorites)
                )
            }
        }

        titleTextView.text = item.title

        salaryTextView.text = item.salaryShort

        salaryTextView.visibility = if (item.salaryShort != "") View.VISIBLE else View.GONE

        experienceTextView.text = item.experiencePreviewText

        schedulesTextView.text = item.schedules.joinToString()

        appliedNumberTextView.text = item.appliedNumber.toString() + " " +
                getDeclensionHuman(requireContext(), item.appliedNumber) + " " +
                getString(R.string.people_have_already_responded)

        lookingNumberTextView.text = item.lookingNumber.toString() +
                " " + getDeclensionHuman(requireContext(), item.lookingNumber) +
                " " + getString(R.string.looking_at_it_now)

        descriptionTextView.text = item.description

        responsibilitiesTextView.text = item.responsibilities

        respondButton.setOnClickListener {
            reactionInclude.titleTextView.text = item.title
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        questionsRecyclerView.layoutManager = LinearLayoutManager(context)
        questionsRecyclerView.adapter = adapter
    }

    private fun initBottomSheet() = with(binding) {
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

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }
}