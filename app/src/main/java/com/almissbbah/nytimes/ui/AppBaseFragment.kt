package com.almissbbah.nytimes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

abstract class AppBaseFragment : DaggerFragment() {
    lateinit var mContext: AppBaseFragment

    abstract fun initViewModel()

    abstract fun initViews()

    abstract fun subscribe()

    abstract fun unSubscribe()

    abstract fun onCreateView(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = this
        val resId = onCreateView()
        return inflater.inflate(resId, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        subscribe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroy() {
        unSubscribe()
        super.onDestroy()
    }

}