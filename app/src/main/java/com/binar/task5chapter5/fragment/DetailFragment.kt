package com.binar.task5chapter5.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.binar.task5chapter5.R
import com.binar.task5chapter5.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding?=null
    private val binding : FragmentDetailBinding get() = _binding!!
    private  val args : DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireActivity())
            .load(args.detail.image)
            .into(binding.posterImageView)
        binding.nameTextView.text=args.detail.name.toString()
        binding.textView5.text="Rp, "+args.detail.price.toString()
        binding.textView6.text=args.detail.category.toString()

        if (args.detail.status==true){
            binding.imageView3.visibility=View.VISIBLE
            binding.statustextView.text= "Unavailable"
           binding.button.isEnabled = false
        }else{
            binding.imageView4.visibility=View.VISIBLE
            binding.statustextView.text= "Available"
        }
    }

}