package com.feylabs.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.feylabs.core.domain.model.News
import com.feylabs.favorite.databinding.FavoriteFragmentBinding
import com.feylabs.favorite.di.favoriteModule
import com.feylabs.lindungipeduli.ui.news.NewsDetailFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding as FavoriteFragmentBinding

    private var mAdapter : NewsFavoriteAdapter? = null

    override fun onDestroyView() {
        binding.rvFavNews.adapter = null;
        _binding = null
        super.onDestroyView()
    }

    val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.favorite_fragment, container, false)
        _binding = FavoriteFragmentBinding.bind(view)

        loadKoinModules(favoriteModule)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = NewsFavoriteAdapter()

        binding.rvFavNews.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.getFavoriteNews()

        mAdapter?.setAdapterInterfacez(object : NewsFavoriteAdapter.NewsItemInterface {
            override fun onclick(model: News?) {
                findNavController().navigate(
                    com.feylabs.lindungipeduli.R.id.newsDetailFragment,
                    bundleOf(
                        NewsDetailFragment.NEWS_ARG to model,
                    )
                )
            }

        })


        viewModel.favoriteLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                binding.imageError.visibility = View.VISIBLE
            } else {
                binding.imageError.visibility = View.GONE
            }
            it.toMutableList().let { it1 -> mAdapter?.setWithNewData(it1) }
        })
    }

}