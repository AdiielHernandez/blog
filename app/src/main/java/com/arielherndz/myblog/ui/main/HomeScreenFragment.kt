package com.arielherndz.myblog.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.arielherndz.myblog.R
import com.arielherndz.myblog.core.Resource
import com.arielherndz.myblog.data.remote.home.HomeScreenDataSource
import com.arielherndz.myblog.databinding.FragmentHomeScreenBinding
import com.arielherndz.myblog.domain.home.HomeScreenRepoImpl
import com.arielherndz.myblog.presentation.main.HomeScreenViewModel
import com.arielherndz.myblog.presentation.main.HomeScreenViewModelFactory
import com.arielherndz.myblog.ui.main.adapter.HomeScreenAdapter



class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {


    lateinit var _binding: FragmentHomeScreenBinding

    // Se crea una instancia de HomeScreenViewModel
    private val viewModel by viewModels<HomeScreenViewModel> {
        HomeScreenViewModelFactory(
            HomeScreenRepoImpl(
                HomeScreenDataSource()
            )
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeScreenBinding.bind(view)

        viewModel.fecthLatesrposts().observe(viewLifecycleOwner, Observer{ result ->
            when(result){
                is Resource.Loading -> {
                    _binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success ->{
                    _binding.progressBar.visibility = View.GONE
                    _binding.rvHome.adapter = HomeScreenAdapter(result.data)
                }
                is Resource.Failure -> {
                    _binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Ocurrio un error${result.exception}", Toast.LENGTH_SHORT).show()
                Log.d("Error", "${result.exception}")
                }
            }
        })

    }
}