package com.bnawan.arsetup

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_fish_model.view.*

const val SELECTED_COLOR = Color.BLACK
const val UNSELECTED_COLOR = Color.TRANSPARENT

class FishModelAdapter(val fish: List<FishModel>):RecyclerView.Adapter<FishModelAdapter.ListViewHolder>() {

    var selectedModel = MutableLiveData<FishModel>()
    private var selectedModelIndex = 0

    inner class ListViewHolder(item: View): RecyclerView.ViewHolder(item){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fish_model, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = fish.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        if(selectedModelIndex == holder.layoutPosition){
            holder.itemView.setBackgroundColor(SELECTED_COLOR)
            selectedModel.value = fish[holder.layoutPosition]
        }else{
            holder.itemView.setBackgroundColor(UNSELECTED_COLOR)
        }
        holder.itemView.apply{
            thumbnail.setImageResource(fish[position].imageResourceId)
            thumbnail_text.text = fish[position].title

            setOnClickListener{
                selectModel(holder)
            }
        }
    }

    private fun selectModel(holder: FishModelAdapter.ListViewHolder) {
        if(holder.layoutPosition != selectedModelIndex){
            holder.itemView.setBackgroundColor(SELECTED_COLOR)
            notifyItemChanged(selectedModelIndex)
            selectedModelIndex = holder.adapterPosition
            selectedModel.value = fish[holder.layoutPosition]
        }
    }
}