package com.manuelbacallado.gymprogress.interfaces

import android.content.Context

interface PresenterInteractorFunctions {
    fun getItem(longClickItemPosition: Int): Any?

    fun getItems() : List<Any>?

    fun deleteItem(longClickItemPosition: Int)
}