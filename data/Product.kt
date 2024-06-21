package com.example.clienttest.UI.data

data class Product(val name:String,val category:String,
                   val descrption:String,val price:String,
                   val url:String)

data class ProductByCat(val cat:String,val list:ArrayList<Product>)
data class Acount(val name:String,val phone:String,val address:String,val floor:String,val house_number:String)