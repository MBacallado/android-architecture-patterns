package com.manuelbacallado.gymprogress.routers

import android.content.Intent
import com.manuelbacallado.gymprogress.activities.InsertRoutineActivity
import com.manuelbacallado.gymprogress.activities.RoutineActivity
import com.manuelbacallado.gymprogress.activities.TrainingDayActivity
import com.manuelbacallado.gymprogress.interfaces.GoToCreate
import com.manuelbacallado.gymprogress.interfaces.GoToOthersScreens
import com.manuelbacallado.gymprogress.models.Routine
import com.manuelbacallado.gymprogress.utils.Constants

class RoutineRouter(val routineView: RoutineActivity) : GoToCreate, GoToOthersScreens {

    override fun goToCreate() {
        val intent = Intent(routineView.applicationContext, InsertRoutineActivity::class.java)
        intent.putExtra(Constants.LOAD_ROUTINE_BOOLEAN, false)
        routineView.startActivity(intent)
    }

    override fun goToNextSection(id: Int) {
        val intent = Intent(routineView.applicationContext, TrainingDayActivity::class.java)
        intent.putExtra(Constants.ROUTINE_ID, id)
        routineView.startActivity(intent)
    }

    override fun goToEdit(item: Any?) {
        val intent = Intent(routineView.applicationContext, InsertRoutineActivity::class.java)
        intent.putExtra(Constants.LOAD_ROUTINE_BOOLEAN, true)
        intent.putExtra(Constants.ROUTINE, item as Routine)
        routineView.startActivity(intent)
    }
}