package com.example.findgithubrepos.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.findgithubrepos.R
import com.example.findgithubrepos.databinding.ListReposGitHubFragmentBinding
import com.example.findgithubrepos.presentation.viewModel.ListReposGitHubViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListReposGitHubFragment : DaggerFragment() {

    lateinit var binding: ListReposGitHubFragmentBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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

        viewModel.getReposStarsGitHub()

        viewModel.testTitle.observe(viewLifecycleOwner, Observer {
            binding.tvTeste.text = it
        })
    }
}