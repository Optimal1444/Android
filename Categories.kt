package com.example.clienttest.UI

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clienttest.R
import com.example.clienttest.UI.Adapters.CatAdapter
import com.example.clienttest.UI.Contract.MainContract
import com.example.clienttest.UI.ViewModel.SharedViewModel
import com.example.clienttest.databinding.FragmentCategoriesBinding
import com.example.clienttest.databinding.FragmentSpecificBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Categories : Fragment(), MainContract.CategoriesInterface {
    private lateinit var binding: FragmentCategoriesBinding
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentCategoriesBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager= LinearLayoutManager(binding.root.context)
        viewModel.catListLiveData.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty())
                binding.recyclerView.adapter=CatAdapter(it,this)
        }
        return binding.root
    }

    override fun setClickedCat(cat: String) {
        viewModel.setListByCat(cat)
        val t=parentFragmentManager.beginTransaction()
        t.replace(R.id.frameLayout,ShowForCat())
        t.commit()
    }

}