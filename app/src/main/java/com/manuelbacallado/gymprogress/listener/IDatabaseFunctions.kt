package com.manuelbacallado.gymprogress.listener

import android.database.Cursor

interface IDatabaseFunctions {
    fun addElement(obj: Any)
    fun editElement(obj: Any)
    fun deleteElement(obj: Any)
    fun getAllElements(obj: Any): List<Any>?
}