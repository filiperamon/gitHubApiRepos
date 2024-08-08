package com.example.findgithubrepos.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findgithubrepos.R
import com.example.findgithubrepos.domain.model.RepositoryItemResponse
import com.bumptech.glide.Glide
import com.example.findgithubrepos.domain.model.PullRequestResponse
import com.example.findgithubrepos.presentation.listener.OnPullRequestAdapterListener
import com.example.findgithubrepos.presentation.listener.OnRepoAdapterListener

class ItemPullRequestAdapter(
    itemResponse: List<PullRequestResponse>,
    repoOriginItem: RepositoryItemResponse,
    itemListener: OnPullRequestAdapterListener
) : RecyclerView.Adapter<ItemPullRequestAdapter.ItemPullRequestViewHolder>(){

    private var item = itemResponse
    private var repo = repoOriginItem
    private val itemListener = itemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPullRequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pull_request_info_item, parent, false)
        return ItemPullRequestViewHolder(view, this.itemListener)
    }

    override fun getItemCount(): Int  = item.count()

    override fun onBindViewHolder(holder: ItemPullRequestViewHolder, position: Int) {
        holder.bindView(item[position], repo)
    }

    inner class ItemPullRequestViewHolder(itemView: View, listener: OnPullRequestAdapterListener) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle = itemView.findViewById<TextView>(R.id.tvPullTitle)
        private val tvPullName = itemView.findViewById<TextView>(R.id.tvPullName)
        private val tvPullBody = itemView.findViewById<TextView>(R.id.tvPullBody)
        private val tvPullDate = itemView.findViewById<TextView>(R.id.tvPullDate)
        private val tvOwnerAvatar = itemView.findViewById<ImageView>(R.id.thumbnailAuthorPull)
        private val listener = listener

        fun bindView(pullItem: PullRequestResponse, repo: RepositoryItemResponse){
            tvTitle.text = pullItem.title
            tvPullName.text = repo.name
            tvPullBody.text = pullItem.body
            tvPullDate.text = pullItem.createdAt
                .split("T")
                .first()

            Glide.with(itemView)
                .load(repo.owner.avatarUrl)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(tvOwnerAvatar)

            itemView.setOnClickListener {
                listener.showPullRequest(pullItem.url)
            }
        }
    }
}