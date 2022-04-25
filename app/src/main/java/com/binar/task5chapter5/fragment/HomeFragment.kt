package com.binar.task5chapter5.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.task5chapter5.R
import com.binar.task5chapter5.adapter.MainAdapter
import com.binar.task5chapter5.databinding.FragmentHomeBinding
import com.binar.task5chapter5.databinding.ProfileDialogBinding
import com.binar.task5chapter5.viewmodel.MainViewModel


class HomeFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()
    private var _binding : FragmentHomeBinding?=null
    private val binding : FragmentHomeBinding get() = _binding!!
    var email :String?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginCek()
        fetchData()
        binding.usernameTextView.text=email
        binding.profileButton.setOnClickListener {
            profileView()
        }
    }
    private fun fetchData (){
        viewModel.getAllCars().observe(viewLifecycleOwner){
            val adapter = MainAdapter(it)
            val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.recycleview.layoutManager = layoutManager
            binding.recycleview.adapter = adapter
        }
        viewModel.getCode().observe(viewLifecycleOwner){
            if (it ==200){
                binding.progressBar.visibility=View.GONE
            }
        }
    }
    fun loginCek(){
        val sharedPreffile = "kotlinsharedpreferance"
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences(
            sharedPreffile,
            Context.MODE_PRIVATE
        )
        val codeValue= sharedPreferences.getInt("code_key", 0)
        val emailValue= sharedPreferences.getString("email_key", "default")
        email= emailValue
        if (codeValue!=201){
            findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToLoginFragment2())
        }

    }
    fun profileView(){
        val sharedPreffile = "kotlinsharedpreferance"
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences(
            sharedPreffile,
            Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val binding = ProfileDialogBinding.inflate(LayoutInflater.from(activity),null,false)
        val view= binding.root
        val dialogBuilder= AlertDialog.Builder(activity)
        dialogBuilder.setView(view)
        val dialog = dialogBuilder.create()
        binding.emailtextView4.text=email.toString()
        binding.button2.setOnClickListener {
            editor.clear()
            editor.apply()
            findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToLoginFragment2())
            dialog.dismiss()
        }

        dialog.show()
    }

}