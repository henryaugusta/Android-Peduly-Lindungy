package com.feylabs.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.feylabs.favorite.databinding.ItemNewsHorizontalBinding
import com.feylabs.lindungipeduli.utils.MyHelper

@SuppressLint("NotifyDataSetChanged")
class NewsFavoriteAdapter : RecyclerView.Adapter<NewsFavoriteAdapter.NewsViewHolder>() {

    val data = mutableListOf<com.feylabs.core.domain.model.News>()
    lateinit var adapterInterface: NewsItemInterface

    fun setWithNewData(data: MutableList<com.feylabs.core.domain.model.News>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData(){
        this.data.clear()
        notifyDataSetChanged()
    }

    fun setAdapterInterfacez(obj: NewsItemInterface) {
        this.adapterInterface = obj
    }

    inner class NewsViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var binding: ItemNewsHorizontalBinding = ItemNewsHorizontalBinding.bind(itemView)

        fun onBInd(model: com.feylabs.core.domain.model.News?) {
            binding.labelTitle.text = model?.title
            binding.tvPenulis.text = model?.author
            binding.tvContent.text= MyHelper.renderHTML(model?.content.toString())

            binding.root.setOnClickListener {
                adapterInterface.onclick(model)
            }

            binding.etDate.text = model?.created_at

            Glide.with(binding.root)
                .load(model?.cover_pict_url)
                .into(binding.ivMainImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news_horizontal, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBInd(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface NewsItemInterface {
        fun onclick(model: com.feylabs.core.domain.model.News?)
    }
}