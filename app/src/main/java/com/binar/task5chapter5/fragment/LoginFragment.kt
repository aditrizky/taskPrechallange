package com.binar.task5chapter5.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.binar.task5chapter5.data.AdminLogin
import com.binar.task5chapter5.viewmodel.MainViewModel
import com.binar.task5chapter5.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private var _binding : FragmentLoginBinding?=null
    private val binding : FragmentLoginBinding get() = _binding!!
    private val viewModel : MainViewModel by activityViewModels()
    private var code : Int = 0
    var email: String?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toCreateTextView.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
        binding.loginButton.setOnClickListener {
            val objectAdmin= AdminLogin(
                email = binding.emailEditTextText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )
            if (TextUtils.isEmpty(objectAdmin.password) || TextUtils.isEmpty(objectAdmin.password)){
                Toast.makeText(requireContext(),"Fill All Fields",Toast.LENGTH_SHORT).show()
            }else{
                viewModel.authLogin(objectAdmin)
                email= objectAdmin.email
            }
        }
        loginResult()
    }

    private fun loginResult(){
        viewModel.getLoginResponse().observe(viewLifecycleOwner){
            if(it == 201){
                saveLoginInfo(it)
                Toast.makeText(requireContext(),"Login Sucess",Toast.LENGTH_SHORT).show()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment2())
            }else if(it == 400){
                Toast.makeText(requireContext(),"Password Was Wrong",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"Email Not Found",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun saveLoginInfo(code:Int){
       val sharedPreffile = "kotlinsharedpreferance"
       val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences(
            sharedPreffile,
            Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt("code_key", code)
        editor.putString("email_key", email)
        editor.apply()
    }

}