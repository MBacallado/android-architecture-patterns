package com.manuelbacallado.gymprogress.presenter

import android.content.Intent
import android.text.Editable
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.activities.InsertTrainingDayActivity
import com.manuelbacallado.gymprogress.activities.TrainingDayActivity
import com.manuelbacallado.gymprogress.db.dao.TrainingDaysDAO
import com.manuelbacallado.gymprogress.interfaces.InitDatabase
import com.manuelbacallado.gymprogress.interfaces.InsertFunctions
import com.manuelbacallado.gymprogress.interfaces.LoadIntentData
import com.manuelbacallado.gymprogress.models.TrainingDay
import com.manuelbacallado.gymprogress.utils.Constants
import kotlinx.android.synthetic.main.insert_training_day_item.*

class InsertTrainingPresenter(var insertTrainingView: InsertTrainingDayActivity) : InitDatabase, LoadIntentData, InsertFunctions {

    lateinit var trainingDay: TrainingDay
    var spinnerTrainingDay: String = ""
    var load: Boolean = false
    private var routineId : Int = 0
    private lateinit var db : TrainingDaysDAO

    override fun initDatabase() {
        db = TrainingDaysDAO(insertTrainingView)
    }

    override fun saveData() {
        var trainingDayAux : TrainingDay
        var id: Int
        trainingDayAux = TrainingDay(0,
            spinnerTrainingDay,
            insertTrainingView.timeText.text.toString().toInt(),
            insertTrainingView.groupText.text.toString(),
            routineId)
        if (!load) {
            id = 0
            trainingDayAux.trainingDayId = id
            db.addElement(trainingDayAux)
        } else {
            trainingDayAux.trainingDayId = trainingDay.trainingDayId
            db.editElement(trainingDayAux)
        }
        val intent = Intent(insertTrainingView.applicationContext, TrainingDayActivity::class.java)
        intent.putExtra(Constants.ROUTINE_ID, routineId)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or  Intent.FLAG_ACTIVITY_CLEAR_TOP
        insertTrainingView.startActivity(intent)
    }

    override fun loadIntentData() {
        routineId = insertTrainingView.intent.extras.getInt(Constants.ROUTINE_ID)
        load = insertTrainingView.intent.extras.getBoolean(Constants.LOAD_TRAINING_BOOLEAN)
        if (load != null && load as Boolean){
            loadData()
        }
    }

    override fun loadData() {
        trainingDay = insertTrainingView.intent.getParcelableExtra<TrainingDay>(Constants.TRAININGDAY)
        insertTrainingView.timeText.text = Editable.Factory.getInstance().newEditable(trainingDay.timeAmount.toString())
        insertTrainingView.groupText.text = Editable.Factory.getInstance().newEditable(trainingDay.group)
        fillTrainingDaysNameSpinner(trainingDay.day);
    }

    private fun fillTrainingDaysNameSpinner(trainingDaysName: String) {
        val trainingDaysArray = insertTrainingView.resources.getStringArray(R.array.days)
        for (i in 0..trainingDaysArray.size-1) {
            if (trainingDaysArray.get(i).equals(trainingDaysName)) {
                insertTrainingView.spinnerTrainingDays.setSelection(i)
            }
        }
    }
}