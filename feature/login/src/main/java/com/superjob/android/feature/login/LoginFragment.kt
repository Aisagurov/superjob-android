package com.superjob.android.feature.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.superjob.android.core.ui.base.BaseFragment
import com.superjob.android.feature.login.databinding.FragmentLoginBinding

internal class LoginFragment: BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToConfirmationFragment()
            )
        }
    }
}