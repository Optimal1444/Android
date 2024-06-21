package com.example.clienttest.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clienttest.R
import com.example.clienttest.UI.Adapters.ChildAdapter
import com.example.clienttest.UI.Adapters.ParentAdapter
import com.example.clienttest.UI.Contract.MainContract
import com.example.clienttest.UI.ViewModel.SharedViewModel
import com.example.clienttest.UI.data.Product
import com.example.clienttest.databinding.FragmentShowForCatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowForCat : Fragment(),MainContract.ShowAllInterface {

    private lateinit var binding: FragmentShowForCatBinding
    private val viewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentShowForCatBinding.inflate(inflater,container,false)

        val screenWidth =  resources.displayMetrics.widthPixels
        val desiredColumnWidthDp = 152
        val columnCount = screenWidth / (desiredColumnWidthDp* resources.displayMetrics.density).toInt()

        binding.recyclerView2.layoutManager= GridLayoutManager(binding.root.context,columnCount)
        viewModel.productForCatLiveData.observe(viewLifecycleOwner){
            if(!it.isNullOrEmpty())
                binding.recyclerView2.adapter= ChildAdapter(it,this)
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