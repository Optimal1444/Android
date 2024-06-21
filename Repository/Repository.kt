package com.example.clienttest.UI.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.clienttest.UI.Contract.MainContract
import com.example.clienttest.UI.ViewModel.SharedViewModel
import com.example.clienttest.UI.data.Product
import com.example.clienttest.UI.data.ProductByCat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.Locale.Category


class Repository:MainContract.FirebaseRepository {
    //list of all products in the realtime firebase
    private lateinit var productsList:ArrayList<Product>
    //list of different categories, from the firebase
    private lateinit var categoryList:ArrayList<String>
    //here we get the data, this is overided function we declared in the repository interface
    override fun getDataFromFireBase(productListMutableLiveData: MutableLiveData<ArrayList<ProductByCat>>,catListMutableLiveData:MutableLiveData<ArrayList<String>>){

        productsList=ArrayList<Product>()
        categoryList=ArrayList<String>()

        //declaration to get data from realtime database
        val database = Firebase.database
        val myRef = database.getReference("Products")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //we declared them here to make new ones every time we call the function
                productsList=ArrayList<Product>()
                categoryList=ArrayList<String>()
                //loop for all children(products on firebase=>under Products)
                for (child in dataSnapshot.getChildren()) {
                    //get the name of the product, which is the key
                    var name=child.key.toString()
                    //get the value, which includes the attributes
                    var item=arrayOf(child.value)[0]
                    //convert the value to string
                    var str=item.toString().subSequence(1,item.toString().length-1)
                    //split the attributes
                    var attributes=str.split(",")
                    //check that all attributes are loaded
                    if(attributes.size>4) {
                        var cat = attributes[4].substringAfter("=")
                        var des = attributes[3].substringAfter("=")
                        var price = attributes[1].substringAfter("=")
                        var url=attributes[2].substringAfter("=")
                        //add them to productsList
                        productsList.add(Product(name,cat,des,price,url))
                        //add the category to the categoryList if not exists
                        if(!categoryList.contains(cat))
                            categoryList.add(cat)
                    }

                }
                productListMutableLiveData.value=getAllByCat()
                catListMutableLiveData.value=categoryList
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    override fun getAllByCat(): ArrayList<ProductByCat> {
        val productListByCat=ArrayList<ProductByCat>()
        for(cat in  categoryList)
            productListByCat.add(ProductByCat(cat, getByCat(cat)))
        return productListByCat
    }

    override fun getByCat(cat:String): ArrayList<Product> {
        val productListWithCat=ArrayList<Product>()
        for (product in productsList)
            if (product.category == cat)
                productListWithCat.add(product)
        return productListWithCat
    }

    override fun getProductByName(name: String): Product {
        val product=productsList.find { product ->
            product.name==name
        }
        return product!!
    }


}