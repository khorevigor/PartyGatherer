package com.dsphoenix.partygatherer.ui.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

open class ViewBindingFragment<VB: ViewBinding>(
    private val inflate: Inflate<VB>
): Fragment() {
    private var bindingMutable: VB? = null
    val binding get() = bindingMutable ?: throw IllegalStateException("Binding is not inflated")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingMutable = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingMutable = null
    }
}
