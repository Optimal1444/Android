package com.example.clienttest.UI.Adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clienttest.R
import com.example.clienttest.UI.Contract.MainContract
import com.example.clienttest.databinding.MoreCardBinding

class MoreAdapter( val view:MainContract.MoreInterface):RecyclerView.Adapter<MoreAdapter.ViewHolder>() {
    lateinit var options:ArrayList<String>
    inner class ViewHolder(val binding:MoreCardBinding):RecyclerView.ViewHolder(binding.root),OnClickListener
    {
        lateinit var option:String
        fun bind(option:String)
        {
            this.option=option
            binding.txt1.text=option
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            view.setClickedOption(option)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        options=ArrayList<String>()
        options.add("acount")
        return ViewHolder(MoreCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =options.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(options[position])
    }
}