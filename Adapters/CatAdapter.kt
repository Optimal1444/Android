package com.example.clienttest.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.clienttest.UI.Contract.MainContract
import com.example.clienttest.databinding.CatCardBinding

class CatAdapter(val catList:ArrayList<String>,val view: MainContract.CategoriesInterface):RecyclerView.Adapter<CatAdapter.ViewHolder>() {
    inner class ViewHolder(val binding:CatCardBinding):RecyclerView.ViewHolder(binding.root),
        OnClickListener{
        lateinit var cat:String
        fun bind(cat:String)
        {
            this.cat=cat
            binding.txt1.text=cat
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            view.setClickedCat(cat)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(CatCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount()=catList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(catList[position])
    }
}