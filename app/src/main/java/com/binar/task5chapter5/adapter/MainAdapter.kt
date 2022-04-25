package com.binar.task5chapter5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.binar.task5chapter5.databinding.ItemCarsBinding
import com.binar.task5chapter5.fragment.HomeFragmentDirections
import com.binar.task5chapter5.response.GetCarsResponseItem
import com.bumptech.glide.Glide

class MainAdapter(private val cars: List<GetCarsResponseItem>): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    class MainViewHolder(val binding : ItemCarsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemCarsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(cars[position].image)
            .into(holder.binding.posterImageView2)
        holder.binding.titleTextView.text=cars[position].name
        holder.binding.priceTextView.text=cars[position].price.toString()
        if (cars[position].status==true){
            holder.binding.imageView3.visibility= View.VISIBLE
            holder.binding.statustextView.text="Unvailable"
        }else{
            holder.binding.imageView4.visibility=View.VISIBLE
            holder.binding.statustextView.text="Available"
        }

        holder.itemView.setOnClickListener {
            val objectDetail= GetCarsResponseItem(
                 image = cars[position].image,
                name = cars[position].name,
                price = cars[position].price,
                status = cars[position].status,
                category = cars[position].category
            )
          it.findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToDetailFragment(objectDetail))
        }
    }

    override fun getItemCount(): Int {
       return cars.size
    }
}