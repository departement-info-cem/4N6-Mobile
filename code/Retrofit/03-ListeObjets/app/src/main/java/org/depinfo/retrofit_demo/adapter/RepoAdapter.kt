package org.depinfo.retrofit_demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.depinfo.retrofit_demo.R
import org.depinfo.retrofit_demo.transfer.Repo



class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val repoName: TextView = itemView.findViewById(R.id.repoName)
    val repoId: TextView = itemView.findViewById(R.id.repoId)
}

class RepoAdapter : ListAdapter<Repo, RepoViewHolder>(RepoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.repoName.text = repo.name
        holder.repoId.text = repo.id.toString()
    }
}


class RepoDiffCallback : DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }
}