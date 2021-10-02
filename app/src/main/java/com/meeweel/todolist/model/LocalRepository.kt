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
var defaultQuest = Quest("","",images[0],0)
var localMyQuestList: MutableList<Quest> = mutableListOf(
)
var localDeletedQuestList: MutableList<Quest> = mutableListOf(
)
var localDoneQuestList: MutableList<Quest> = mutableListOf(
)
fun getMyLocalQuestList() : List<Quest> {
    return localMyQuestList
}
fun getDeletedLocalQuestList() : List<Quest> {
    return localDeletedQuestList
}
fun getDoneLocalQuestList() : List<Quest> {
    return localDoneQuestList
}
fun mainToTrash(quest: Quest) {
    localMyQuestList.remove(quest)
    localDeletedQuestList.add(quest)
}
fun mainToDone(quest: Quest) {
    localMyQuestList.remove(quest)
    localDoneQuestList.add(quest)
}
fun trashToMain(quest: Quest) {
    localMyQuestList.add(quest)
    localDeletedQuestList.remove(quest)
}
fun doneToMain(quest: Quest) {
    localMyQuestList.add(quest)
    localDoneQuestList.remove(quest)
}
fun deleteFromDone(quest: Quest) {
    localDoneQuestList.remove(quest)
}
fun deleteFromDeleted(quest: Quest) {
    localDeletedQuestList.remove(quest)
}