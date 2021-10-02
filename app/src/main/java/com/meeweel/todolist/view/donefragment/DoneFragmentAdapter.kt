package com.meeweel.todolist.view.donefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.todolist.databinding.DoneRecyclerItemBinding
import com.meeweel.todolist.model.*
import com.meeweel.todolist.view.mainfragment.MainFragment
import com.meeweel.todolist.view.mainfragment.MainFragmentAdapter

class DoneFragmentAdapter :
    RecyclerView.Adapter<DoneFragmentAdapter.MainViewHolder>() {

    private var questData: List<Quest> = listOf()
    private var onItemViewClickListener: DoneFragment.OnItemViewClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = DoneRecyclerItemBinding.inflate(
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

    inner class MainViewHolder(private val binding: DoneRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(quest: Quest) {
            binding.apply {
                mainFragmentRecyclerItemTextView.text = quest.title
                mainFragmentRecyclerItemImageView.setImageResource(quest.image)
                root.setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(quest)
                }
                deleteBtn.setOnClickListener {
                    deleteFromDone(quest)
                    notifyDataSetChanged()
                }
                restoreBtn.setOnClickListener {
                    doneToMain(quest)
                    notifyDataSetChanged()
                }
            }
        }
    }

    fun setOnItemViewClickListener(onItemViewClickListener: DoneFragment.OnItemViewClickListener){
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