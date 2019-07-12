package com.manuelbacallado.gymprogress.interfaces

interface IDatabaseFunctions {
    fun addElement(obj: Any)
    fun editElement(obj: Any)
    fun deleteElement(obj: Any)
    fun getAllElements(id: Int): List<Any>?
}