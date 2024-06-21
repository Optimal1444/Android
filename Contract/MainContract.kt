package com.example.clienttest.UI.Contract

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.clienttest.UI.data.Acount
import com.example.clienttest.UI.data.Product
import com.example.clienttest.UI.data.ProductByCat

interface MainContract {
    interface FirebaseRepository{
        //fun getAllData():ArrayList<Product>
        fun getDataFromFireBase(liveData:MutableLiveData<ArrayList<ProductByCat>>,catListMutableLiveData:MutableLiveData<ArrayList<String>>)
        fun getAllByCat():ArrayList<ProductByCat>
        fun getByCat(cat:String):ArrayList<Product>
        fun getProductByName(name:String):Product
    }
    interface SharedPreferencesRepository{
        fun getData():Acount
        fun updateData(acount:Acount)
    }
    interface ShowAllInterface{
        fun setClickedProduct(product: Product)
    }
    interface CategoriesInterface
    {
        fun setClickedCat(cat:String)
    }
    interface MoreInterface{
        fun setClickedOption(option:String)
    }

}