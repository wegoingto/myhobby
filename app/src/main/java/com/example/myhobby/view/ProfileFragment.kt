package com.example.myhobby.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myhobby.viewmodel.AuthViewModel
import com.example.myhobby.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        val userInfo = viewModel.getUser(requireContext())
        binding.etPassword.setText(userInfo?.password)
        binding.etFirstName.setText(userInfo?.firstName)
        binding.etLastName.setText(userInfo?.lastName)
        binding.btnSave.setOnClickListener {
            val password = binding.etPassword.text.toString()
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()

            when {
                password.isEmpty() -> {
                    binding.etPassword.error = "Password is required"
                    binding.etPassword.requestFocus()
                }

                firstName.isEmpty() -> {
                    binding.etFirstName.error = "First Name is required"
                    binding.etFirstName.requestFocus()
                }

                lastName.isEmpty() -> {
                    binding.etLastName.error = "Last Name is required"
                    binding.etLastName.requestFocus()
                }

                else -> {
                    viewModel.editProfile(requireContext(), password, firstName, lastName)
                    Toast.makeText(requireContext(), "Profile has been changed", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logout(requireContext())
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}