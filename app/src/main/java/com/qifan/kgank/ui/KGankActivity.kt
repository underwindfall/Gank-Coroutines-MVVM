package com.qifan.kgank.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.qifan.kgank.R
import com.qifan.kgank.base.observeEvent
import com.qifan.kgank.viewmodel.KGankViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class KGankActivity : AppCompatActivity() {

    //di
    private val mViewModel: KGankViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            mViewModel.fetchGankContent()
        }
        mViewModel.spinner.observe(this, Observer { visible ->
            spinner.visibility = if (visible) View.VISIBLE else View.GONE
            textView.visibility = if (!visible) View.VISIBLE else View.GONE
        })

        mViewModel.gankContentLiveData.observe(this, Observer {
            textView.text = it.results?.get(0).toString()
        })

        mViewModel.snackBar.observeEvent(this) { content ->
            Snackbar.make(textView, content, Snackbar.LENGTH_INDEFINITE).show()
        }
    }

}
