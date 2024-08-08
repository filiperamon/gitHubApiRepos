package com.example.findgithubrepos.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findgithubrepos.R
import com.example.findgithubrepos.databinding.ListReposGitHubFragmentBinding
import com.example.findgithubrepos.domain.model.RepositoryItemResponse
import com.example.findgithubrepos.presentation.viewModel.ListReposGitHubViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListReposGitHubFragment : DaggerFragment(), OnRepoAdapterListener {

    lateinit var binding: ListReposGitHubFragmentBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var currentPage = 1
    private var availablePages = 33 //Only the first 1000 search results are available
    private var listToShow : MutableList<RepositoryItemResponse> = mutableListOf()
    private lateinit var adapter: ItemRepoGitHubAdapter

    private val viewModel: ListReposGitHubViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ListReposGitHubViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.list_repos_git_hub_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycle()

        viewModel.listReposGitHubLiveData.observe(viewLifecycleOwner, Observer { listItems ->
            listToShow.addAll(listItems)
            adapter.updateList(listToShow, listToShow.size - listItems.size, listItems.size)

        })

        viewModel.iLoadingLiveData.observe(viewLifecycleOwner, Observer { isVisible ->
            binding.loadMoreProgress.isVisible = isVisible
        })
    }

    private fun getReposStarsGitHub(page: Int) {
        viewModel.getReposStarsGitHub(page)
    }


    private fun initRecycle() {
        binding.run {

            recyclerviewRepositories.setHasFixedSize(true)
            recyclerviewRepositories.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ItemRepoGitHubAdapter(listToShow, this@ListReposGitHubFragment)
            recyclerviewRepositories.adapter = adapter

            recyclerviewRepositories.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!binding.recyclerviewRepositories.canScrollVertically(1)){
                        if (currentPage <= availablePages) {
                            currentPage += 1

                            binding.loadMoreProgress.isVisible = true
                            getReposStarsGitHub(currentPage)
                        }
                    }
                }
            })

            getReposStarsGitHub(currentPage)
        }
    }

    override fun showRepoDetails(item: RepositoryItemResponse) {
        Log.d("ListReposGitHubFragment", "showRepoDetails: ${item.name}")
    }
}