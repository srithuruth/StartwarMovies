package com.example.kathishan.startwarmovies


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kathishan.startwarmovies.R
import kotlinx.android.synthetic.main.item_title.view.*
import kotlinx.android.synthetic.main.item_title.view.*


class FilmAdapter : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    private val data = arrayListOf<Response>()


    fun setData(items: List<Response>) {
        data.clear()
        data.addAll(items)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_title, parent, false)
        return FilmViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(viewHolder: FilmViewHolder, position: Int) {
        viewHolder.bind(data[position])
    }


    class FilmViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(response: Response) {
            view.tvTitle.text = response.title.toString()

        }

    }
}

