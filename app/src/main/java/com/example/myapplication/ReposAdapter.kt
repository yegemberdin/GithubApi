package com.example.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ReposAdapter(
    var list: List<ReposModel>? = null,
    val itemClickListener: RecyclerViewItemClick? = null) : RecyclerView.Adapter<ReposAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_repos, p0, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(p0: PostViewHolder, p1: Int) {
        p0.bind(list?.get(p1))
    }

    inner class PostViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bind(repos: ReposModel?) {
            val tvName = view.findViewById<TextView>(R.id.tvName)
            val tvReposId = view.findViewById<TextView>(R.id.tvReposId)
            val tvDescription = view.findViewById<TextView>(R.id.tvDescription)

            tvName.text = repos?.name
            tvReposId.text = repos?.id.toString()
            tvDescription.text = repos?.description

            view.setOnClickListener {
                itemClickListener?.itemClick(adapterPosition, repos!!)
            }
        }
    }

    interface RecyclerViewItemClick {

        fun itemClick(position: Int, item: ReposModel)
    }
}