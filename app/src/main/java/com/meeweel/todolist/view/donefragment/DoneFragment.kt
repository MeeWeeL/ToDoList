package com.meeweel.todolist.view.donefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meeweel.todolist.databinding.DoneLayoutBinding
import com.meeweel.todolist.model.*
import com.meeweel.todolist.viewmodel.MainViewModel

class DoneFragment : Fragment() {

    companion object {
        fun newInstance() = DoneFragment()
    }
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private var _binding: DoneLayoutBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = DoneFragmentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DoneLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        adapter.removeOnItemViewClickListener()
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.mainFragmentRecyclerView.adapter = adapter
        val observer = Observer<AppState> { a ->
            renderData(a)
        }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getDoneQuestFromLocalSource()
    }


    private fun renderData(data: AppState) = when(data) {
        is AppState.Success -> {
            val questData = data.questData
            binding.loadingLayout.visibility = View.GONE
            adapter.setQuest(questData)
        }
        is AppState.Loading -> {
            binding.loadingLayout.visibility = View.VISIBLE
        }
        is AppState.Error -> {
            binding.loadingLayout.visibility = View.GONE

        }
    }
    interface OnItemViewClickListener {
        fun onItemViewClick(quest: Quest)
    }
}