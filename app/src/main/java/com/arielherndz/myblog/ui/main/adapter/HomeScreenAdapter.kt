package com.arielherndz.myblog.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arielherndz.myblog.core.BaseViewHolder
import com.arielherndz.myblog.data.model.Post
import com.arielherndz.myblog.databinding.PostItemViewBinding
import com.bumptech.glide.Glide

class HomeScreenAdapter(private val postList: List<Post>): RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = PostItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeScreenViewHolder(itemBinding,parent.context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is HomeScreenViewHolder -> holder.bind(postList[position])
        }
    }

    override fun getItemCount(): Int = postList.size

    private inner class HomeScreenViewHolder(
        val binding: PostItemViewBinding, val context:Context
    ): BaseViewHolder<Post>(binding.root) {

        override fun bind(item: Post) {
            Glide.with(context).load(item.post_image).centerCrop().into(binding.postImage)
            Glide.with(context).load(item.profile_pictures).centerCrop().into(binding.profilePiture)

            binding.profileName.text = item.profile_name
            binding.postTimeTamp.text = "Hace 2 horas"

        }
    }
}