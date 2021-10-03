package com.meeweel.todolist.model

import android.os.Parcelable
import com.meeweel.todolist.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quest (
    var list: Int = 1,
    var title: String = "Title",
    var description: String = "Description",
    var image: Int = R.drawable.anig,
    var imageInt: Int = 0
    ) : Parcelable