package com.meeweel.todolist.view.deletedfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.todolist.databinding.DeletedRecyclerItemBinding
import com.meeweel.todolist.databinding.DoneRecyclerItemBinding
import com.meeweel.todolist.model.*
import com.meeweel.todolist.view.donefragment.DoneFragment
import com.meeweel.todolist.view.donefragment.DoneFragmentAdapter
import com.meeweel.todolist.view.mainfragment.MainFragment
import com.meeweel.todolist.view.mainfragment.MainFragmentAdapter

class DeletedFragmentAdapter :
    RecyclerView.Adapter<DeletedFragmentAdapter.MainViewHolder>() {

    private var questData: List<Quest> = listOf()
    private var onItemViewClickListener: DeletedFragment.OnItemViewClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = DeletedRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(questData[position])
    }

    override fun getItemCount(): Int {
        return questData.size
    }

    inner class MainViewHolder(private val binding: DeletedRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(quest: Quest) {
            binding.apply {
                mainFragmentRecyclerItemTextView.text = quest.title
                mainFragmentRecyclerItemImageView.setImageResource(quest.image)
                root.setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(quest)
                }
                deleteBtn.setOnClickListener {
                    deleteFromDeleted(quest)
                    notifyDataSetChanged()
                }
                restoreBtn.setOnClickListener {
                    trashToMain(quest)
                    notifyDataSetChanged()
                }
            }
        }
    }

    fun setOnItemViewClickListener(onItemViewClickListener: DeletedFragment.OnItemViewClickListener){
        this.onItemViewClickListener = onItemViewClickListener
    }

    fun removeOnItemViewClickListener(){
        onItemViewClickListener = null
    }

    fun setQuest(data: List<Quest>) {
        questData = data
        notifyDataSetChanged()
    }

}