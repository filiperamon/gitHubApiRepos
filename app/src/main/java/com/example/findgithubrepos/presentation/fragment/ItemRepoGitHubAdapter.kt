package com.example.findgithubrepos.presentation.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findgithubrepos.R
import com.example.findgithubrepos.domain.model.RepositoryItemResponse
import com.bumptech.glide.Glide

class ItemRepoGitHubAdapter(
    itemResponse: List<RepositoryItemResponse>,
    itemListener: OnRepoAdapterListener
) : RecyclerView.Adapter<ItemRepoGitHubAdapter.ItemRepoGitHubViewHolder>(){

    private var item = itemResponse
    private val itemListener = itemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRepoGitHubViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repositiry_info_item, parent, false)
        return ItemRepoGitHubViewHolder(view, this.itemListener)
    }

    override fun getItemCount(): Int  = item.count()

    override fun onBindViewHolder(holder: ItemRepoGitHubViewHolder, position: Int) {
        holder.bindView(item[position])
    }

    fun updateList(tvShowList: List<RepositoryItemResponse>, oldCount: Int, tvShowListSize: Int) {
        this.item = tvShowList
        notifyDataSetChanged()
        notifyItemRangeInserted(oldCount, tvShowListSize)
    }

    inner class ItemRepoGitHubViewHolder(itemView: View, listener: OnRepoAdapterListener) : RecyclerView.ViewHolder(itemView) {

        private val tvAuthor = itemView.findViewById<TextView>(R.id.tvRepoName)
        private val tvDescription = itemView.findViewById<TextView>(R.id.tvRepoDesc)
        private val tvStars = itemView.findViewById<TextView>(R.id.tvRepoStars)
        private val tvForks = itemView.findViewById<TextView>(R.id.tvRepoForc)
        private val tvOwner = itemView.findViewById<TextView>(R.id.tvAuthor)
        private val tvOwnerAvatar = itemView.findViewById<ImageView>(R.id.thumbnailAuthor)
        private val listener = listener

        fun bindView(repo: RepositoryItemResponse){
            tvAuthor.text = repo.name
            tvDescription.text = repo.description
            tvStars.text = repo.stargazersCount.toString()
            tvForks.text = repo.forksCount.toString()
            tvOwner.text = repo.owner.login

            Glide.with(itemView)
                .load(repo.owner.avatarUrl)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(tvOwnerAvatar)

            itemView.setOnClickListener {
                listener.showRepoDetails(repo)
            }
        }

    }

}