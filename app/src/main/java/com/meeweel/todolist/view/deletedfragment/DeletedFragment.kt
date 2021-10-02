package com.meeweel.todolist.view.deletedfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meeweel.todolist.R
import com.meeweel.todolist.databinding.DeletedLayoutBinding
import com.meeweel.todolist.databinding.DoneLayoutBinding
import com.meeweel.todolist.model.AppState
import com.meeweel.todolist.model.Quest
import com.meeweel.todolist.view.DetailsFragment
import com.meeweel.todolist.view.donefragment.DoneFragment
import com.meeweel.todolist.view.donefragment.DoneFragmentAdapter
import com.meeweel.todolist.view.mainfragment.MainFragment
import com.meeweel.todolist.view.mainfragment.MainFragmentAdapter
import com.meeweel.todolist.viewmodel.MainViewModel

class DeletedFragment : Fragment() {

    companion object {
        fun newInstance() = DeletedFragment()
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private var _binding: DeletedLayoutBinding? = null
    private val binding
        get() = _binding!!
    private val adapter = DeletedFragmentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DeletedLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter.removeOnItemViewClickListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter.setOnItemViewClickListener(object: OnItemViewClickListener {
            override fun onItemViewClick(quest: Quest) {
                activity?.supportFragmentManager?.apply {
                    beginTransaction()
                        .replace(R.id.container, DetailsFragment.newInstance(Bundle().apply {
                            putParcelable(DetailsFragment.BUNDLE_EXTRA, quest)
                        }))
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
            }
        })

        binding.mainFragmentRecyclerView.adapter = adapter
        val observer = Observer<AppState> { a ->
            renderData(a)
        }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getDeletedQuestFromLocalSource()

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