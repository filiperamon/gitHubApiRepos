package com.example.findgithubrepos.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.findgithubrepos.R
import com.example.findgithubrepos.databinding.ListReposPullFragmentBinding
import com.example.findgithubrepos.presentation.adapter.ItemPullRequestAdapter
import com.example.findgithubrepos.presentation.listener.OnPullRequestAdapterListener
import com.example.findgithubrepos.presentation.viewModel.ListPullsReposViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ListPullReposFragment : DaggerFragment(), OnPullRequestAdapterListener {

    private val args: ListPullReposFragmentArgs by navArgs()

    lateinit var binding: ListReposPullFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ListPullsReposViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ListPullsReposViewModel::class.java]
    }

    private lateinit var adapter: ItemPullRequestAdapter

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
            R.layout.list_repos_pull_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setListReposLiveData(args.repoItem)
        observables()
    }

    private fun observables() {
        viewModel.listPullReposLiveData.observe(viewLifecycleOwner) {
            adapter = ItemPullRequestAdapter(it, viewModel.itemReposLiveData.value!!, this)
            initRecycle()
        }

        viewModel.iLoadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            binding.loading.isVisible = isLoading
        }
    }

    private fun initRecycle() {
        binding.apply {
            recyclerviewPullRepositories.setHasFixedSize(true)
            recyclerviewPullRepositories.layoutManager =
                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            recyclerviewPullRepositories.adapter = adapter
        }
    }

    override fun showPullRequest(link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}