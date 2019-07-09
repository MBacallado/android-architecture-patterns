package com.manuelbacallado.gymprogress.routers

import android.content.Intent
import com.manuelbacallado.gymprogress.activities.*
import com.manuelbacallado.gymprogress.models.TrainingDay
import com.manuelbacallado.gymprogress.utils.Constants

class InsertTrainingRouter(val insertTrainingView: InsertTrainingDayActivity) {

    lateinit var trainingDay: TrainingDay
    var load: Boolean = false
    var parentId : Int = 0

    fun goToCreate() {
        val intent = Intent(insertTrainingView.applicationContext, TrainingDayActivity::class.java)
        intent.putExtra(Constants.ROUTINE_ID, parentId)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or  Intent.FLAG_ACTIVITY_CLEAR_TOP
        insertTrainingView.startActivity(intent)
    }

    fun initData() {
        if (insertTrainingView.intent.extras != null) {
            parentId = insertTrainingView.intent.extras.getInt(Constants.ROUTINE_ID)
            load = insertTrainingView.intent.extras.getBoolean(Constants.LOAD_TRAINING_BOOLEAN)
            if (load != null && load){
                trainingDay = insertTrainingView.intent.getParcelableExtra<TrainingDay>(Constants.TRAININGDAY)
            }
        }
    }
}