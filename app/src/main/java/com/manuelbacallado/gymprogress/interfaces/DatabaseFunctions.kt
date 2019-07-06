package com.manuelbacallado.gymprogress.interfaces

interface DatabaseFunctions {
    fun addElement(obj: Any)
    fun editElement(obj: Any)
    fun deleteElement(obj: Any)
    fun getAllElements(id: Int): List<Any>?
}