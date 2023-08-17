package com.example.retrofitdogapi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitdogapi.databinding.ListItemBinding
import com.example.retrofitdogapi.networkUtils.BreedDetails

class BreedViewHolder (
    private val binding: ListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, data: BreedDetails) {
        binding.itemBreed.text = data.name
        binding.itemCategory.text = data.category
        binding.itemOrigin.text = data.origin

        // TODO: Add the image.
        Glide.with(context).load("$imageUrlPrefix${data.image}$imageUrlPostfix").into(binding.itemImage)
    }
}

class DogBreedAdapter(
    private val context: Context,
    private var breeds: List<BreedDetails>
): RecyclerView.Adapter<BreedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        return BreedViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = breeds.size

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bind(context, breeds[position])
    }

    fun refreshList(newDataList: List<BreedDetails>) {
        breeds = newDataList
        notifyDataSetChanged()
    }

}