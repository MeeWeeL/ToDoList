package com.meeweel.todolist.view.donefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.todolist.databinding.DoneRecyclerItemBinding
import com.meeweel.todolist.model.data.Quest
import com.meeweel.todolist.model.repository.changingQuest

class DoneFragmentAdapter :
    RecyclerView.Adapter<DoneFragmentAdapter.MainViewHolder>() {

    private var questData: MutableList<Quest> = mutableListOf()
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
                    changingQuest.add(Quest(0, quest.title,quest.description,quest.image,quest.imageInt))
                    questData.remove(quest)
                    notifyDataSetChanged()
                }
                restoreBtn.setOnClickListener {
                    changingQuest.add(Quest(1, quest.title,quest.description,quest.image,quest.imageInt))
                    questData.remove(quest)
                    notifyDataSetChanged()
                }
            }
        }
    }

    fun removeOnItemViewClickListener(){
        onItemViewClickListener = null
    }

    fun setQuest(data: MutableList<Quest>) {
        questData = data
        notifyDataSetChanged()
    }

}