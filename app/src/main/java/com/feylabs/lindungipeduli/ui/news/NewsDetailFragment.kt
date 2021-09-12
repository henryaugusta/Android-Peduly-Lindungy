package com.feylabs.lindungipeduli.ui.news

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.feylabs.lindungipeduli.R
import com.feylabs.lindungipeduli.core.domain.model.News
import com.feylabs.lindungipeduli.databinding.FragmentNewsDetailBinding
import com.feylabs.lindungipeduli.utils.MyHelper
import android.view.MotionEvent

import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.viewModel


class NewsDetailFragment : Fragment() {

    var _binding: FragmentNewsDetailBinding? = null
    val binding get() = _binding as FragmentNewsDetailBinding

    private val detailNewsViewModel: NewsViewModel by viewModel()

    var currentState = false


    companion object {
        const val NEWS_ARG = "news_arg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news_detail, container, false)
        _binding = FragmentNewsDetailBinding.bind(view)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val newsObject = arguments?.getParcelable<News>(NEWS_ARG)


        binding.apply {
            newsObject.let {
                tvAuthor.text = getString(R.string.ditulis_oleh) + it?.author
                tvContent.text = MyHelper.renderHTML(it?.content.toString())
                tvDate.text = it?.created_at
                tvMain.text = it?.title
                Glide.with(root).load(it?.cover_pict_url).into(ivMainImage)


            }
        }


        // Check is already added to favorite
        detailNewsViewModel.checkIfFavorite(newsObject?.id.toString()).observe(
            viewLifecycleOwner, Observer {
                if (it != null) {
                    currentState = it
                    newsObject?.is_favorite = it
                    binding.btnBookmark.isSelected = it

                    if (it) {
                        binding.labelBookmark.text = getString(R.string.already_added)
                    } else {
                        binding.labelBookmark.text = getString(R.string.not_yet_bookmark)
                    }
                }
            }
        )


        binding.btnBookmark.setOnClickListener {
            with(binding) {
                //if current state is liked, then remove from bookmark
                if (currentState) {
                    newsObject?.is_favorite = false
                    newsObject?.let { it1 ->
                        detailNewsViewModel.addToFavorite(it1, false)
                    }

                    labelBookmark.text = getString(R.string.not_yet_bookmark)
                    currentState = false
                    btnBookmark.isSelected = false
                } else {

                    newsObject?.is_favorite = true
                    newsObject?.let { it1 ->
                        detailNewsViewModel.addToFavorite(it1, true)
                    }

                    labelBookmark.text = getString(R.string.already_added)
                    currentState = true
                    btnBookmark.isSelected = true
                }
            }

        }


    }


}