package com.example.clienttest.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clienttest.R
import com.example.clienttest.UI.Adapters.ParentAdapter
import com.example.clienttest.UI.Contract.MainContract
import com.example.clienttest.UI.ViewModel.SharedViewModel
import com.example.clienttest.UI.data.Product
import com.example.clienttest.databinding.FragmentShowAllBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowAll : Fragment(),MainContract.ShowAllInterface {

    private lateinit var binding:FragmentShowAllBinding
    
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentShowAllBinding.inflate(inflater,container,false)
        binding.parentRecyclerView.layoutManager=LinearLayoutManager(binding.root.context)
        viewModel.productListLiveData.observe(viewLifecycleOwner){
            if(!it.isNullOrEmpty())
                binding.parentRecyclerView.adapter=ParentAdapter(it,this)
        }
        return binding.root
    }

    override fun setClickedProduct(product: Product) {
        viewModel.setProduct(product)
        val t=parentFragmentManager.beginTransaction()
        t.replace(R.id.frameLayout,Specific())
        t.commit()
    }

}