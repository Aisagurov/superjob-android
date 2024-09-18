package com.superjob.android.feature.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.widget.doOnTextChanged
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.superjob.android.core.ui.base.BaseFragment
import com.superjob.android.core.ui.util.hideKeyboard
import com.superjob.android.feature.login.databinding.FragmentConfirmationBinding
import com.superjob.android.core.ui.R

internal class ConfirmationFragment:
    BaseFragment<FragmentConfirmationBinding>(FragmentConfirmationBinding::inflate)
{
    private val code by lazy {
        arrayOf(
            binding.firstEditText,
            binding.secondEditText,
            binding.thirdEditText,
            binding.fourEditText,
        )
    }
    private val symbolLength = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        code.forEach { edit ->
            edit.doOnTextChanged { text, _, _, _ ->
                text?.let {
                    val currentIndex = code.indexOf(edit)
                    if (it.length == symbolLength) {
                        if (currentIndex == code.size - 1) {
                            view?.hideKeyboard()
                        } else {
                            code[currentIndex + 1].requestFocus()
                        }
                    }
                }
            }
        }

        binding.firstEditText.requestFocus()

        binding.confirmButton.setOnClickListener {
            val code =
                StringBuilder().also { code.forEach { tv -> it.append(tv.text.toString()) } }.toString()
            if (code.length == 4) {
                val request = NavDeepLinkRequest.Builder
                    .fromUri(getString(R.string.home_uri).toUri()).build()
                findNavController().navigate(request)
            } else {
                Toast.makeText(context, R.string.password_length, Toast.LENGTH_SHORT).show()
            }
        }
    }
}