package com.manuelbacallado.gymprogress.interfaces

interface RecyclerViewListeners {
    fun onClick(concrete: Any, position: Int)
    fun onLongClick(concrete: Any, position: Int)
}