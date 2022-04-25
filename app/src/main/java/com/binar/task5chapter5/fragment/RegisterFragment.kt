package com.binar.task5chapter5.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.binar.task5chapter5.R
import com.binar.task5chapter5.data.RegisterAdmin
import com.binar.task5chapter5.databinding.FragmentRegisterBinding
import com.binar.task5chapter5.viewmodel.MainViewModel

class RegisterFragment : Fragment() {
    private var _binding : FragmentRegisterBinding?=null
    private val binding : FragmentRegisterBinding get() = _binding!!
    private val viewModel : MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButton.setOnClickListener {
            val objectRegister= RegisterAdmin(
                email = binding.emailEditTextText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )
            val confrmpasswd= binding.confpasswordEditText.text.toString()
            if (TextUtils.isEmpty(objectRegister.email)|| TextUtils.isEmpty(objectRegister.password)|| TextUtils.isEmpty(confrmpasswd)){
                Toast.makeText(requireContext(),"Fill All Fields",Toast.LENGTH_SHORT).show()
            }else if (objectRegister.password != confrmpasswd){
                Toast.makeText(requireContext(),"Confirmation Password Error",Toast.LENGTH_SHORT).show()
            }else{
                viewModel.registerAdmin(objectRegister)
            }
        }
        registerResult()
    }

    fun registerResult(){
        viewModel.getRegisterResponse().observe(viewLifecycleOwner){
            if(it == 201){
                Toast.makeText(requireContext(),"Register Sucess",Toast.LENGTH_SHORT).show()
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }else if(it == 400){
                Toast.makeText(requireContext(),"Email Already Exists",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"Password Must At Least 6 Char or invalid email",Toast.LENGTH_SHORT).show()
            }
        }
    }
}