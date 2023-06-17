package com.example.fooddeliveryapp.presentation.ui.screen_profile

import android.os.Bundle
import android.view.View
import com.example.fooddeliveryapp.databinding.FragmentProfileBinding
import com.example.fooddeliveryapp.presentation.base.BaseFragment
import com.example.fooddeliveryapp.presentation.utils.extensions.makeToast
import com.example.fooddeliveryapp.presentation.utils.extensions.setOnDownEffectClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentProfile :
    BaseFragment<FragmentProfileBinding, FragmentProfileViewModel>(FragmentProfileBinding::inflate) {


    override val viewModel: FragmentProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigationView()
        hideToolbarLayout()
        binding().btn.setOnDownEffectClickListener {
            requireContext().makeToast("функция временно не недоступна")
        }
    }


}