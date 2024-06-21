package com.example.clienttest.UI.ViewModel

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clienttest.UI.Contract.MainContract
import com.example.clienttest.UI.Hilt.FBRepo
import com.example.clienttest.UI.Hilt.spRepo
import com.example.clienttest.UI.Repository.Repository
import com.example.clienttest.UI.Repository.SharedPreferencesRepository
import com.example.clienttest.UI.data.Acount
import com.example.clienttest.UI.data.Product
import com.example.clienttest.UI.data.ProductByCat
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SharedViewModel @Inject constructor(@spRepo private val sp_repo:MainContract.SharedPreferencesRepository,@FBRepo private val fb_repo:MainContract.FirebaseRepository): ViewModel() {
    //there are 4 objects
    //get object from repository class to get the data
    //list of "category"=>list of products related to.
    //private object to keep it from outside classes
    private val productListMutableLiveData=MutableLiveData<ArrayList<ProductByCat>>()
    //not private with the same value, so we can read it outside this class
    val productListLiveData:LiveData<ArrayList<ProductByCat>> =productListMutableLiveData

    //for specific product(the product choosed by the user)
    private val productMutableLiveData=MutableLiveData<Product>()
    val productLiveData:LiveData<Product> =productMutableLiveData

    //list of all categories only
    private val catListMutableLiveData=MutableLiveData<ArrayList<String>>()
    val catListLiveData:LiveData<ArrayList<String>> =catListMutableLiveData

    //list of products under specific category
    private val productForCatMutableLiveData=MutableLiveData<ArrayList<Product>>()
    val productForCatLiveData:LiveData<ArrayList<Product>> =productForCatMutableLiveData

    //acount
    private val acountMutableLiveData=MutableLiveData<Acount>()
    val acountLiveData:LiveData<Acount> =acountMutableLiveData
    //prepare all data and update the lists
    init{
        fb_repo.getDataFromFireBase(productListMutableLiveData,catListMutableLiveData)
        acountMutableLiveData.value=sp_repo.getData()
    }
    //update the value for the specific product(the product chosen by the user)
    fun setProduct(product:Product)
    {
        productMutableLiveData.value=product
    }
    //update the chosen category and prepare its products
    fun setListByCat(cat:String){
        productForCatMutableLiveData.value=fb_repo.getByCat(cat)
    }
    fun setAcount(acount:Acount)
    {
        sp_repo.updateData(acount)
        acountMutableLiveData.value=sp_repo.getData()

    }

}