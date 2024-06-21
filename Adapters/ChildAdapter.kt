package com.example.clienttest.UI.Adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clienttest.R
import com.example.clienttest.UI.Contract.MainContract
import com.example.clienttest.UI.data.Product
import com.example.clienttest.databinding.ChildCardBinding


class ChildAdapter(val products:ArrayList<Product>,val view:MainContract.ShowAllInterface):RecyclerView.Adapter<ChildAdapter.ViewHolder>() {


    inner class ViewHolder(val binding:ChildCardBinding):RecyclerView.ViewHolder(binding.root)
    ,OnClickListener
    {
        private lateinit var product:Product
        fun bind(product: Product)
        {
            this.product=product
            binding.name.text=product.name
            binding.des.text=product.descrption
            binding.price.text="${product.price} ج.م"
            binding.cat.text=product.category
            Glide.with(itemView.context)
                .load(product.url)
                .placeholder(R.drawable.baseline_insert_photo_24)
                .into(binding.imageView)
            itemView.setOnClickListener(this)
            //binding.imageView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            //if(v!!.id==binding.imageView.id) {
                val product = Product(
                    product.name,
                    product.category,
                    product.descrption,
                    product.price,
                    product.url
                )
                view.setClickedProduct(product)
            //}
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    =ViewHolder(ChildCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }
}