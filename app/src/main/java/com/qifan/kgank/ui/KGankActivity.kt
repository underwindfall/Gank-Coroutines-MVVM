package com.qifan.kgank.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.qifan.kgank.R
import com.qifan.kgank.viewmodel.KGankViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class KGankActivity : AppCompatActivity() {

    //di
    private val mViewModel: KGankViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        getViewModel<KGankViewModel>()
        mViewModel.fetchGankContent()
//        recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//        recyclerview.adapter = SimpleAdapter()
        mViewModel.gankContentLiveData.observe(this, Observer {
            Log.d("Qifan===========", it.results.toString())
            textView.text = it.results?.get(0).toString()
        })
    }

//    class SimpleAdapter : RecyclerView.Adapter {
//
//    }
}
