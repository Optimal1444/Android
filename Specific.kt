package com.example.clienttest.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.clienttest.R
import com.example.clienttest.UI.ViewModel.SharedViewModel
import com.example.clienttest.UI.data.Product
import com.example.clienttest.databinding.FragmentSpecificBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Specific : Fragment() {

    private lateinit var binding: FragmentSpecificBinding
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentSpecificBinding.inflate(inflater, container, false)
        viewModel.productLiveData.observe(viewLifecycleOwner){
            binding.txt1.text=it.name
            binding.txt2.text=it.descrption
            binding.txt3.text=it.price
            Glide.with(binding.root.context)
                .load(it.url)
                .placeholder(R.drawable.baseline_insert_photo_24)
                .into(binding.imageView)
            binding.btn1.setOnClickListener(View.OnClickListener {
                val t=parentFragmentManager.beginTransaction()
                t.replace(R.id.frameLayout,ShowAll())
                t.commit()
            })
        }
        return binding.root
    }


}