package com.learn.growthcodelab.jetpack.livedata.shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.learn.growthcodelab.R
import com.learn.growthcodelab.databinding.FragmentArticleListBinding
import com.learn.growthcodelab.fragment.BaseFragment

class ArticleListFragment : BaseFragment(){


    override fun getLayoutRes(): Int {
        return R.layout.fragment_article_list
    }

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return DataBindingUtil.inflate<FragmentArticleListBinding>(inflater, layoutRes, container, false).root
    }
}