package com.example.awesome_robot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.awesome_robot.databinding.ActivityMainBinding
import com.example.awesome_robot.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), RepositoryRecyclerViewAdapter.OnItemClickListener {
    lateinit var binding: ActivityMainBinding

    private val repositoryRecyclerViewAdapter = RepositoryRecyclerViewAdapter(arrayListOf(), this)

    //    var viewModel = MainViewModel()
    var repository = Repository(
        "Medium Android Repository Article", "Mladen Rakonjac",
        1000, true
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        binding.apply {
//            repositoryName.text = "Medium Android Repository Article"
//            repositoryOwner.text = "Mladen Rakonjac"
//            numberOfStarts.text = "1000 stars"
//        }
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.executePendingBindings()


        binding.repositoryRv.layoutManager = LinearLayoutManager(this)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter
        viewModel.repositories.observe(this, Observer<ArrayList<Repository>> {
            it?.let { repositoryRecyclerViewAdapter.replaceData(it) }
        })

//        Handler().postDelayed({
//            repository.repositoryName = "New Name"
//            binding.repository = repository
//            binding.executePendingBindings()
//        }, 2000)
    }

    override fun onItemClick(position: Int) {

    }
}