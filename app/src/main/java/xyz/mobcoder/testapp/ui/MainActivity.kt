package xyz.mobcoder.testapp.ui

import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import xyz.mobcoder.testapp.adapter.ListAdapter
import xyz.mobcoder.testapp.api.ApiService
import xyz.mobcoder.testapp.databinding.ActivityMainBinding
import xyz.mobcoder.testapp.sqlite.DBOpenHelper
import xyz.mobcoder.testapp.utils.App
import xyz.mobcoder.testapp.viewmodel.MainViewModel
import xyz.mobcoder.testapp.viewmodel.factories.MainModelFactory
import xyz.mobcoder.testapp.viewmodel.repositories.MainRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ListAdapter.AdapterListener {
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var dbOpenHelper: DBOpenHelper

    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as App).injector.inject(this)
        val mainRepository = MainRepository(apiService)

        binding.rvList.adapter = ListAdapter(this, dbOpenHelper)

        viewModel = ViewModelProvider(this, MainModelFactory(mainRepository)).get(MainViewModel::class.java)

        viewModel.plan.observe(this, {
            binding.planView.visibility = View.VISIBLE
            val data = it.items.map { item -> item.data }.firstOrNull()
            binding.planView.setData(data)
            binding.planView.reDraw()
        })
    }

    override fun onClick(hash: String) {
        viewModel.getPlan(hash)
    }

}