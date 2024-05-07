package com.bimobelajar.mynoterev.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bimobelajar.mynoterev.R
import com.bimobelajar.mynoterev.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        binding.loginButton.setOnClickListener {
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()

            // Check credentials from SharedPreferences
            if (sharedPreferences.getString("username", "") == username &&
                sharedPreferences.getString("password", "") == password) {
                findNavController().navigate(R.id.action_loginFragment_to_noteFragment)
            } else {
                Toast.makeText(requireContext(), "Invalid credentials!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }
}
