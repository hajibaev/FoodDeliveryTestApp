package com.example.fooddeliveryapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.presentation.utils.extensions.hideView
import com.example.fooddeliveryapp.presentation.utils.extensions.launchWhenViewStarted
import com.example.fooddeliveryapp.presentation.utils.extensions.makeToast
import com.example.fooddeliveryapp.presentation.utils.extensions.navigateTo
import com.example.fooddeliveryapp.presentation.utils.extensions.showView
import com.example.fooddeliveryapp.presentation.utils.navigation.NavigationCommand
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseFragment<V : ViewBinding, VM : BaseViewModel>(
    private val binder: (LayoutInflater, ViewGroup?, Boolean) -> V,
) : Fragment() {

    protected abstract val viewModel: VM

    private var viewBinding: V? = null

    protected fun binding(): V = checkNotNull(viewBinding)


    private val bottomNavigationView: BottomNavigationView? by lazy(LazyThreadSafetyMode.NONE) {
        requireActivity().findViewById<BottomNavigationView>(R.id.main_bottom_nav_view) ?: null
    }

    private val toolbarLayout: LinearLayout? by lazy(LazyThreadSafetyMode.NONE) {
        requireActivity().findViewById<LinearLayout>(R.id.toolbar_main_layout) ?: null
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = binder.invoke(inflater, container, false)
        this.viewBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeRecourse()
    }


    private fun observeRecourse() = with(viewModel) {
        launchWhenViewStarted {
            navCommand.observe(findNavController()::navigateTo)
            isErrorMessageIdFlow.observe { requireContext().makeToast(it.format(requireContext())) }
        }
        collectNavigation(viewLifecycleOwner) {
            it.getValue()?.let { navigationCommand ->
                handleNavigation(navigationCommand)
            }
        }
    }

    private fun handleNavigation(navCommand: NavigationCommand) {
        when (navCommand) {
            is NavigationCommand.ToDirection -> findNavController().navigate(navCommand.directions.actionId)
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }

    fun showBottomNavigationView() = bottomNavigationView?.apply { showView() }

    fun hideBottomNavigationView() = bottomNavigationView?.apply { hideView() }

    fun showToolbarLayout() = toolbarLayout?.apply { showView() }

    fun hideToolbarLayout() = toolbarLayout?.apply { hideView() }


    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}