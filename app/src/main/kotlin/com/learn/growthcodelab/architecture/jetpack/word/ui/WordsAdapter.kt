package com.learn.growthcodelab.architecture.jetpack.word.ui

import com.learn.growthcodelab.R
import com.learn.growthcodelab.architecture.jetpack.word.model.Word
import com.learn.growthcodelab.recycler.BaseDataBindingAdapter

class WordsAdapter : BaseDataBindingAdapter() {

    private val wordList: MutableList<Word> = mutableListOf()

    override fun getItemByPosition(position: Int): Any {
        return wordList[position]
    }

    override fun getLayoutIdByPosition(position: Int) = R.layout.itme_word

    override fun getItemCount(): Int = wordList.size

    fun updateWords(words: List<Word>?) {
        words?.let {
            if (!it.isEmpty()) {
                wordList.clear()
                wordList.addAll(it)
                notifyDataSetChanged()
            }
        }
    }
}