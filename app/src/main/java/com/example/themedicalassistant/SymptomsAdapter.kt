package com.example.themedicalassistant

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themedicalassistant.View.SymptomsDiffUtil
import com.example.themedicalassistant.databinding.SymptomsRowBinding


class SymptomsAdapter(private val clickListener: ClickListener) :
    ListAdapter<String, SymptomsAdapter.ItemViewHolder>(
    SymptomsDiffUtil()
) {
    class ItemViewHolder(var binding: SymptomsRowBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(SymptomsRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        )
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        var symptom = getItem(position)
        var state = 0
        holder.binding.apply {
            tvSymptoms.text = symptom

            rgAnswer.setOnCheckedChangeListener{
                radioGroup,id->

                if ( id == 2131362164 )
                    state = 1
                else if (id == 2131362163)
                    state = 0

                clickListener.onClick(state , "$position")
            }

        }

    }

    interface ClickListener{
        fun onClick(number: Int , position: String)

    }


}

