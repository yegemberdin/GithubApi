package com.example.myapplication

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar

class Repos : AppCompatActivity(), ReposAdapter.RecyclerViewItemClick {
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    private var reoposAdapter: ReposAdapter? = null



    private lateinit var viewModel: ReposViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginIntent = getIntent();
        val login: String = loginIntent.getStringExtra("login")
        var userValues = this@Repos.getApplicationContext() as UserModel
        userValues.setLogin(login)
        viewModel = ViewModelProviders.of(this).get(ReposViewModel::class.java)
        setContentView(R.layout.activity_repos)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        reoposAdapter = ReposAdapter(itemClickListener = this)
        recyclerView.adapter = reoposAdapter

        progressBar = findViewById(R.id.progressBar)

        viewModel.getRepos()
        viewModel.liveData.observe(this, Observer { result ->
            when (result) {
                is ReposResult.ShowLoading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is ReposResult.HideLoading -> {
                    progressBar.visibility = View.GONE
                }
                is ReposResult.ReposList -> {
                    reoposAdapter?.list = result.list
                    reoposAdapter?.notifyDataSetChanged()
                }


            }
        })
    }

    override fun itemClick(position: Int, item: ReposModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
