package com.meeweel.todolist.model

import com.meeweel.todolist.R


val images: List<Int> = listOf(
    R.drawable.anig,
    R.drawable.anig1,
    R.drawable.anig2,
    R.drawable.anig3,
    R.drawable.anig4,
    R.drawable.anig5,
    R.drawable.anig6,
    R.drawable.anig7,
    R.drawable.anig8,
    R.drawable.anig9,
    R.drawable.anig10,
    R.drawable.anig11,
    R.drawable.anig12,
    R.drawable.anig13,
    R.drawable.anig14,
    R.drawable.anig15,
    R.drawable.anig16,
    R.drawable.anig17
)
var defaultQuest = Quest(1,"","",images[0],0)

var changingQuest: MutableList<Quest> = mutableListOf()