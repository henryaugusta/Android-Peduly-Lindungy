package com.feylabs.lindungipeduli.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feylabs.lindungipeduli.R
import com.feylabs.lindungipeduli.core.domain.model.News
import com.feylabs.lindungipeduli.databinding.ItemNewsBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    val data = mutableListOf<News>()
    lateinit var adapterInterface: NewsItemInterface

    fun setWithNewData(data: MutableList<News>) {
        this.data.clear()
        this.data.addAll(data)
    }

    fun setAdapterInterfacez(obj: NewsItemInterface) {
        this.adapterInterface = obj
    }

    inner class NewsViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var binding: ItemNewsBinding = ItemNewsBinding.bind(itemView)

        fun onBInd(model: News?) {
            binding.tvMain.text = model?.title

            binding.root.setOnClickListener {
                adapterInterface.onclick(model)
            }

            binding.tvSecondary.text = model?.created_at

            Glide.with(binding.root)
                .load(model?.cover_pict_url)
                .into(binding.ivMainImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBInd(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface NewsItemInterface {
        fun onclick(model: News?)
    }
}