package com.example.clienttest.UI

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clienttest.R
import com.example.clienttest.UI.Adapters.CatAdapter
import com.example.clienttest.UI.Adapters.MoreAdapter
import com.example.clienttest.UI.Contract.MainContract
import com.example.clienttest.UI.ViewModel.SharedViewModel
import com.example.clienttest.databinding.FragmentCategoriesBinding
import com.example.clienttest.databinding.FragmentMoreBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class More : Fragment(),MainContract.MoreInterface {
    private lateinit var binding: FragmentMoreBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentMoreBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager= LinearLayoutManager(binding.root.context)
        binding.recyclerView.adapter= MoreAdapter(this)
        return binding.root

    }

    override fun setClickedOption(option: String) {
        val t=parentFragmentManager.beginTransaction()
        if(option==getString(R.string.acount))
            t.replace(R.id.frameLayout,Acount())
        t.commit()
    }


}