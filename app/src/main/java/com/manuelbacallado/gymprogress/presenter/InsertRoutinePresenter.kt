package com.manuelbacallado.gymprogress.presenter

import android.content.Intent
import android.text.Editable
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.activities.InsertRoutineActivity
import com.manuelbacallado.gymprogress.activities.RoutineActivity
import com.manuelbacallado.gymprogress.db.dao.RoutineDAO
import com.manuelbacallado.gymprogress.interfaces.InitDatabase
import com.manuelbacallado.gymprogress.interfaces.InsertFunctions
import com.manuelbacallado.gymprogress.interfaces.LoadIntentData
import com.manuelbacallado.gymprogress.models.Routine
import com.manuelbacallado.gymprogress.utils.Constants
import kotlinx.android.synthetic.main.insert_routine_item.*

class InsertRoutinePresenter(var insertRoutineView: InsertRoutineActivity) : InitDatabase, LoadIntentData, InsertFunctions{

    lateinit var routine: Routine
    var spinnerDay: String = ""
    var spinnerTypes: String = ""
    var load: Boolean = false
    private lateinit var db : RoutineDAO

    override fun initDatabase() {
        db = RoutineDAO(insertRoutineView)
    }

    override fun saveData() {
        var routineAux : Routine
        var id: Int
        routineAux = Routine(0,
            insertRoutineView.routineText.text.toString(),
            insertRoutineView.startDateText.text.toString(),
            insertRoutineView.finishDateText.text.toString(),
            spinnerDay.toInt(),
            spinnerTypes
        )
        if (!load) {
            id = 0
            routineAux.routineId = id
            db.addElement(routineAux)
        } else {
            routineAux.routineId = routine.routineId
            db.editElement(routineAux)
        }
        val intent = Intent(insertRoutineView.applicationContext, RoutineActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        insertRoutineView.startActivity(intent)
    }

    override fun loadIntentData() {
        load = insertRoutineView.intent.extras.getBoolean(Constants.LOAD_ROUTINE_BOOLEAN)
        if (load != null && load as Boolean) {
            loadData()
        }
    }

    override fun loadData() {
        routine = insertRoutineView.intent.getParcelableExtra<Routine>(Constants.ROUTINE)
        insertRoutineView.routineText.text = Editable.Factory.getInstance().newEditable(routine.name)
        insertRoutineView.startDateText.text = Editable.Factory.getInstance().newEditable(routine.startDate)
        insertRoutineView.finishDateText.text = Editable.Factory.getInstance().newEditable(routine.finishDate)
        fillTrainingDaysSpinner(routine.trainingDays)
        fillTrainingTypeSpinner(routine.trainingTypes)
    }

    private fun fillTrainingDaysSpinner(trainingDays: Int) {
        val numbersArray = insertRoutineView.resources.getStringArray(R.array.numbers)
        for (i in 0..numbersArray.size-1) {
            if (numbersArray.get(i).toInt() == trainingDays) {
                insertRoutineView.spinnerDays.setSelection(i)
            }
        }
    }

    private fun fillTrainingTypeSpinner(trainingTypes: String) {
        val typesArray = insertRoutineView.resources.getStringArray(R.array.trainingType)
        for (i in 0..typesArray.size-1) {
            if (typesArray.get(i).equals(trainingTypes)) {
                insertRoutineView.spinnerType.setSelection(i)
            }
        }
    }
}