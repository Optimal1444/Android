package com.example.clienttest.UI

import android.content.ClipData
import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TableRow
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.clienttest.R
import com.example.clienttest.UI.ViewModel.SharedViewModel
import com.example.clienttest.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        replaceFragment(ShowAll())
        val navView: BottomNavigationView = binding.navView
        navView?.setOnItemSelectedListener { item->
            when(item.itemId)
            {
                R.id.showAll->replaceFragment(ShowAll())
                R.id.categories->replaceFragment(Categories())
                R.id.specific->replaceFragment(More())

            }
            return@setOnItemSelectedListener true
        }

//        val navController = findNavController(R.id.frameLayout)
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.showAll, R.id.categories, R.id.specific
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)


    }
    fun replaceFragment(fragmet:Fragment)
    {
        var t=supportFragmentManager.beginTransaction()
        t.replace(R.id.frameLayout,fragmet)
        t.commit()
    }
}