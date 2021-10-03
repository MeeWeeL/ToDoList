package com.meeweel.todolist.view.mainfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.todolist.databinding.MainRecyclerItemBinding
import com.meeweel.todolist.model.data.Quest
import com.meeweel.todolist.model.repository.changingQuest

class MainFragmentAdapter :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var questData: MutableList<Quest> = mutableListOf()
    private var onItemViewClickListener: MainFragment.OnItemViewClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = MainRecyclerItemBinding.inflate(
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

    inner class MainViewHolder(private val binding: MainRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(quest: Quest) {
            binding.apply {
                mainFragmentRecyclerItemTextView.text = quest.title
                mainFragmentRecyclerItemImageView.setImageResource(quest.image)
                itemDescription.text = quest.description
                root.setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(quest)
                }
                deleteBtn.setOnClickListener {
                    changingQuest.add(Quest(3, quest.title,quest.description,quest.image,quest.imageInt))
                    questData.remove(quest)
                    notifyDataSetChanged()
                }
                doneBtn.setOnClickListener {
                    changingQuest.add(Quest(2, quest.title,quest.description,quest.image,quest.imageInt))
                    questData.remove(quest)
                    notifyDataSetChanged()
                }
            }
        }
    }

    fun setOnItemViewClickListener(onItemViewClickListener: MainFragment.OnItemViewClickListener){
        this.onItemViewClickListener = onItemViewClickListener
    }

    fun removeOnItemViewClickListener(){
        onItemViewClickListener = null
    }

    fun setQuest(data: MutableList<Quest>) {
        questData = data
        notifyDataSetChanged()
    }

}