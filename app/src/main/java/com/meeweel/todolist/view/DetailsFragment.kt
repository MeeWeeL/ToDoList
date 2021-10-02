package com.meeweel.todolist.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.meeweel.todolist.R
import com.meeweel.todolist.databinding.EditLayoutBinding
import com.meeweel.todolist.model.Quest
import com.meeweel.todolist.model.images
import com.meeweel.todolist.model.localMyQuestList
import com.meeweel.todolist.view.mainfragment.MainFragment

class DetailsFragment : Fragment() {

    private var imageInt: Int = 0
    private var _binding: EditLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EditLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Quest>(BUNDLE_EXTRA)?.let {
                quest -> populateData(quest)
        }
    }

    private fun populateData(questData: Quest) {
        with(binding) {
            image.setImageResource(questData.image)
            title.setText(questData.title)
            descriptionValue.setText(questData.description)
            imageInt = questData.imageInt
            nextBtn.setOnClickListener {
                imageInt++
                if (imageInt == images.size) imageInt = 0
                image.setImageResource(images[imageInt])
            }
            backBtn.setOnClickListener {
                imageInt--
                if (imageInt < 0) imageInt = images.size - 1
                image.setImageResource(images[imageInt])
            }
            saveBtn.setOnClickListener {
                for (item in localMyQuestList) {
                    if (item == questData) {
                        item.description = descriptionValue.text.toString()
                        item.title = title.text.toString()
                        item.image = images[imageInt]
                        item.imageInt = imageInt
                        break
                    }
                }
                activity?.supportFragmentManager?.beginTransaction()?.replace(id, MainFragment())?.commitNow()
            }
            cancelBtn.setOnClickListener {
                activity?.supportFragmentManager?.beginTransaction()?.replace(id, MainFragment())?.commitNow()
            }
        }
    }
    companion object {
        const val BUNDLE_EXTRA = "quest"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}