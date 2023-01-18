package com.example.themedicalassistant.View

import androidx.recyclerview.widget.DiffUtil
import com.example.themedicalassistant.Model.Diseases

class DiseasesDiffUtil : DiffUtil.ItemCallback<Diseases>() {
    override fun areItemsTheSame(oldItem: Diseases, newItem: Diseases): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Diseases, newItem: Diseases): Boolean {
    return  oldItem == newItem


    }


}