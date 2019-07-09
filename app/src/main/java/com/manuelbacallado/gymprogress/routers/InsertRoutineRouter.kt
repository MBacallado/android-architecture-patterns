package com.manuelbacallado.gymprogress.routers

import android.content.Intent
import com.manuelbacallado.gymprogress.activities.*
import com.manuelbacallado.gymprogress.models.Routine
import com.manuelbacallado.gymprogress.utils.Constants

class InsertRoutineRouter(val insertRoutineView: InsertRoutineActivity) {

    lateinit var routine: Routine
    var load: Boolean = false

    fun goToCreate() {
        val intent = Intent(insertRoutineView.applicationContext, RoutineActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        insertRoutineView.startActivity(intent)
    }

    fun initData() {
        if (insertRoutineView.intent.extras != null) {
            load = insertRoutineView.intent.extras.getBoolean(Constants.LOAD_ROUTINE_BOOLEAN)
            if (load != null && load){
                routine = insertRoutineView.intent.getParcelableExtra<Routine>(Constants.ROUTINE)
            }
        }
    }
}