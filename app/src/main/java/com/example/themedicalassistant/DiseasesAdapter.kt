package com.example.themedicalassistant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themedicalassistant.Model.Diseases
import com.example.themedicalassistant.View.DiseasesDiffUtil
import com.example.themedicalassistant.databinding.DiseaseRowBinding



class DiseasesAdapter(private val clickListener: ClickListener) :
    ListAdapter<Diseases, DiseasesAdapter.ItemViewHolder>(
    DiseasesDiffUtil()
) {
    class ItemViewHolder(var binding: DiseaseRowBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(DiseaseRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        )
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        var diseases = getItem(position)

        holder.binding.apply {
            btnDisease.text = diseases.name

            btnDisease.setOnClickListener(){
                clickListener.onClickDisease(diseases)
            }

            ivDisease.setOnClickListener{
                clickListener.onClickImage(diseases)
            }
        }

    }

    interface ClickListener{
        fun onClickDisease(disease: Diseases)
        fun onClickImage(disease: Diseases)

    }


}

