package com.manuelbacallado.gymprogress.listener

interface RecyclerViewListeners {
    fun onClick(concrete: Any, position: Int)
    fun onLongClick(concrete: Any, position: Int)
}