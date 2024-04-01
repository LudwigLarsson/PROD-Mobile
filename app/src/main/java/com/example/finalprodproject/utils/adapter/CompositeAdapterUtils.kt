package com.example.finalprodproject.utils.adapter

object CompositeAdapterUtils {

    fun CompositeAdapter.addAllToTheStart(newItems: List<DelegateAdapterItem>) {
        if (currentList.size == 0) {
            submitList(newItems)
        } else {
            val supplementedItems = newItems.toMutableList()
            supplementedItems.addAll(currentList)
            submitList(supplementedItems)
        }
    }

    fun CompositeAdapter.addAllToTheEnd(newItems: List<DelegateAdapterItem>) {
        if (currentList.size == 0) {
            submitList(newItems)
        } else {
            val supplementedItems = currentList.toMutableList()
            supplementedItems.addAll(newItems)
            submitList(supplementedItems)
        }
    }

}