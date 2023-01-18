package com.example.themedicalassistant.View

import androidx.recyclerview.widget.DiffUtil
import com.example.themedicalassistant.Model.Diseases

class SymptomsDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
    return  oldItem == newItem


    }


}