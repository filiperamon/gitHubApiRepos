package com.example.findgithubrepos.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.findgithubrepos.R
import com.example.findgithubrepos.databinding.ListReposGitHubFragmentBinding
import com.example.findgithubrepos.databinding.ListReposPullFragmentBinding
import com.example.findgithubrepos.presentation.viewModel.ListPullsReposViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListPullReposFragment : DaggerFragment() {

    private val args: ListPullReposFragmentArgs by navArgs()

    lateinit var binding: ListReposPullFragmentBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ListPullsReposViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[ListPullsReposViewModel::class.java]
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
            Log.d("ListPullReposFragment", "listPullRepos: $it")
        }
    }
}