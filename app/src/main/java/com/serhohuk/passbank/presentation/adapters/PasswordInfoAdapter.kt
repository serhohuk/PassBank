package com.serhohuk.passbank.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.serhohuk.passbank.R
import com.serhohuk.passbank.data.models.PasswordData

class PasswordInfoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class PassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val serviceName = itemView.findViewById<TextView>(R.id.tv_service)

        fun bind(item: PasswordData){
            serviceName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PassViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pass_info,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PassViewHolder).bind(items.currentList[position])
    }

    override fun getItemCount(): Int {
        return items.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<PasswordData>(){
        override fun areItemsTheSame(oldItem: PasswordData, newItem: PasswordData): Boolean {
            return oldItem.key == newItem.key
        }

        override fun areContentsTheSame(oldItem: PasswordData, newItem: PasswordData): Boolean {
            return oldItem == newItem
        }
    }

    val items = AsyncListDiffer(this,differCallback)
}