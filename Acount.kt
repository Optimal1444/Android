package com.example.clienttest.UI

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.clienttest.R
import com.example.clienttest.UI.ViewModel.SharedViewModel
import com.example.clienttest.UI.data.Acount
import com.example.clienttest.databinding.FragmentAcountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Acount : Fragment() {
    private lateinit var binding: FragmentAcountBinding
    private val viewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentAcountBinding.inflate(inflater, container, false)
        viewModel.acountLiveData.observe(viewLifecycleOwner){
            binding.txt1.setText(it.name)
            binding.txt2.setText(it.address)
            binding.txt3.setText(it.phone)
            binding.txt4.setText(it.floor)
            binding.txt5.setText(it.house_number)
            binding.saveBtn.setOnClickListener(View.OnClickListener {
                viewModel.setAcount(
                    Acount(binding.txt1.text.toString(),
                    binding.txt3.text.toString(),
                    binding.txt2.text.toString(),
                    binding.txt4.text.toString(),
                    binding.txt5.text.toString())
                )
            })
            binding.btn1.setOnClickListener(View.OnClickListener {
                val t=parentFragmentManager.beginTransaction()
                t.replace(R.id.frameLayout,ShowAll())//change to more
                t.commit()
            })
        }

        return binding.root
    }


}