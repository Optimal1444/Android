package com.example.clienttest.UI.Adapters


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clienttest.R
import com.example.clienttest.UI.Contract.MainContract
import com.example.clienttest.UI.data.Product
import com.example.clienttest.UI.data.ProductByCat
import com.example.clienttest.databinding.ParentCardBinding


class ParentAdapter(val productsByCatList:ArrayList<ProductByCat>,val view: MainContract.ShowAllInterface):RecyclerView.Adapter<ParentAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ParentCardBinding):RecyclerView.ViewHolder(binding.root),OnClickListener {
        fun bind(p:ProductByCat) {
            binding.cat.text = p.cat
            binding.childRecyclerView.layoutManager =
                LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            binding.childRecyclerView.adapter = ChildAdapter(p.list, view)
           // itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {

           // if(v!!.id!=binding.childRecyclerView.id) {
               // Toast.makeText(binding.root.context,"ok",Toast.LENGTH_SHORT).show()
            //}
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    =ViewHolder(ParentCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    override fun getItemCount(): Int = productsByCatList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productsByCatList[position])
    }
}