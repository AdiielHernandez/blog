package com.arielherndz.myblog.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.arielherndz.myblog.R
import com.arielherndz.myblog.core.Result
import com.arielherndz.myblog.data.remote.auth.AuthDataSource
import com.arielherndz.myblog.databinding.FragmentLoginBinding
import com.arielherndz.myblog.domain.auth.AuthRepoImpl
import com.arielherndz.myblog.presentation.auth.AuthViewModel
import com.arielherndz.myblog.presentation.auth.AuthViewModelFactory
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var _binding: FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val viewModel by viewModels<AuthViewModel> {AuthViewModelFactory(AuthRepoImpl(
        AuthDataSource()
    ))  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)
        isUserLoggedIn()
        doLogin()
        gotToSingUpPage()

    }

    private fun isUserLoggedIn(){
        firebaseAuth.currentUser?.let {
            findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
        }
    }

    private fun doLogin(){
        _binding.btnSignIn.setOnClickListener {

            val email = _binding.editTextEmail.text.toString().trim()
            val password = _binding.editTextPassword.text.toString().trim()
            validateCredential(email,password)
            singIn(email,password)

        }
    }

    private fun gotToSingUpPage(){
        _binding.txtSignIn.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun validateCredential(email: String, password: String){

        if (email.isEmpty()){
            _binding.editTextEmail.error = "E-mail is empty"
            return
        }

        if (password.isEmpty()){
            _binding.editTextEmail.error = "Password is empty"
            return
        }
    }

    private fun singIn(email: String, password: String){
        viewModel.signIn(email,password).observe(viewLifecycleOwner, Observer { result ->
            when(result){

                is Result.Loading -> {
                    _binding.progressBar.visibility = View.VISIBLE
                    _binding.btnSignIn.isEnabled = false
                }
                is Result.Success -> {
                    _binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)

                }
                is Result.Failure -> {
                    _binding.progressBar.visibility = View.GONE
                    _binding.btnSignIn.isEnabled = true
                    Toast.makeText(requireContext(), "Error: ${result.exception}", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

}