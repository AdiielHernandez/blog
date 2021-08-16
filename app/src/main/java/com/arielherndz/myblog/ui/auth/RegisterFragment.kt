package com.arielherndz.myblog.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.arielherndz.myblog.R
import com.arielherndz.myblog.core.Result
import com.arielherndz.myblog.data.remote.auth.AuthDataSource
import com.arielherndz.myblog.databinding.FragmentRegisterBinding
import com.arielherndz.myblog.domain.auth.AuthRepoImpl
import com.arielherndz.myblog.presentation.auth.AuthViewModel
import com.arielherndz.myblog.presentation.auth.AuthViewModelFactory


class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var _binding: FragmentRegisterBinding
    private val viewModel by viewModels<AuthViewModel> {
        AuthViewModelFactory( AuthRepoImpl( AuthDataSource()))}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterBinding.bind(view)

        SignUp()
    }

    private fun SignUp(){

        _binding.btnSignUp.setOnClickListener {

            val username = _binding.editTextUseName.text.toString().trim()
            val email = _binding.editTextEmail.text.toString().trim()
            val password = _binding.editTextPassword.text.toString().trim()
            val confirmPassword = _binding.editTextPasswordConfirm.text.toString().trim()

            if (validateUserData(username,  email, password,confirmPassword)) return@setOnClickListener

            if (password != confirmPassword){
                _binding.editTextPasswordConfirm.error = "Password does not match"
                _binding.editTextPassword.error = "Password does not match"
//                _binding.editTextPasswordLayout.isPasswordVisibilityToggleEnabled = false
//                _binding.editTextPasswordConfirmLayout.isPasswordVisibilityToggleEnabled = false
                return@setOnClickListener
            }

            createUser(email, password, username)
        }

    }

    private fun createUser(email: String, password: String, username: String) {
        viewModel.signUp(email,password,username).observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Result.Loading -> {
                    _binding.progressBar.visibility = View.VISIBLE
                    _binding.btnSignUp.isEnabled = false

                }
                is Result.Success -> {
                    _binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_registerFragment_to_homeScreenFragment)
                }
                is Result.Failure -> {
                    _binding.progressBar.visibility = View.GONE
                    _binding.btnSignUp.isEnabled = true
                }
            }

        })
    }

    private fun validateUserData( username: String,email: String,password: String, confirmPassword: String
    ): Boolean {
        if (username.isEmpty()) {
            _binding.editTextUseName.error = "Username is empty"
            return true
        }
        if (email.isEmpty()) {
            _binding.editTextEmail.error = "Email is empty"
            return true
        }
        if (password.isEmpty()) {
            _binding.editTextPassword.error = "Password is empty"
            return true
        }
        if (confirmPassword.isEmpty()) {
            _binding.editTextPasswordConfirm.error = "Confirm password is empty"
            return true
        }
        return false
    }

}