package com.stone.mvixml

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.stone.mvixml.RecyclerView.MyAdapters
import com.stone.mvixml.databinding.ActivityMainBinding
import com.stone.mvixml.intent.MyIntents
import com.stone.mvixml.model.MyViewModel
import com.stone.mvixml.model.Posts
import com.stone.mvixml.view.MyStates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding?=null
    private val binding:ActivityMainBinding
        get() = _binding!!
    val viewmodel:MyViewModel by viewModels()
    val adapters=MyAdapters()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val post=Posts(12,12,"djhjfhsdj","dcjhjcsnd")
        binding.recycler.layoutManager= LinearLayoutManager(this)
        binding.recycler.adapter=adapters


        intents()
        getdata()
        binding.button.setOnClickListener {
            lifecycleScope.launch { viewmodel.intents.send(MyIntents.postdata(post)) }
        }
    }
    private fun intents(){
      lifecycleScope.launch{
          viewmodel.intents.send(MyIntents.Getpost)

      }
  }




    private fun getdata(){

        lifecycleScope.launch {
            viewmodel.state.collect{
                when(it){
                    is MyStates.Error -> {
                        binding.progress.visibility=View.GONE
                        Toast.makeText(this@MainActivity, "${it.msg.toString()}", Toast.LENGTH_SHORT).show()
                    }
                    MyStates.Idle -> TODO()
                    MyStates.Loading -> {
                        binding.progress.visibility=View.VISIBLE
                    }
                    is MyStates.Success -> {
                        binding.progress.visibility=View.GONE

                        adapters.setdata(it.data)
                    }
                }
            }
        }
    }
}