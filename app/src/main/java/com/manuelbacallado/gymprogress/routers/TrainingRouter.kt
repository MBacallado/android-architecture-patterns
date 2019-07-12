package com.manuelbacallado.gymprogress.routers

import android.content.Intent
import com.manuelbacallado.gymprogress.activities.*
import com.manuelbacallado.gymprogress.interfaces.GoToCreate
import com.manuelbacallado.gymprogress.interfaces.GoToOthersScreens
import com.manuelbacallado.gymprogress.interfaces.InitData
import com.manuelbacallado.gymprogress.models.TrainingDay
import com.manuelbacallado.gymprogress.utils.Constants

class TrainingRouter(val trainingView: TrainingDayActivity) : GoToCreate, GoToOthersScreens, InitData {

    var parentId : Int = 0

    override fun goToCreate() {
        val intent = Intent(trainingView.applicationContext, InsertTrainingDayActivity::class.java)
        intent.putExtra(Constants.LOAD_TRAINING_BOOLEAN, false)
        intent.putExtra(Constants.ROUTINE_ID, parentId)
        trainingView.startActivity(intent)
    }

    override fun goToNextSection(id: Int) {
        val intent = Intent(trainingView.applicationContext, ExerciseActivity::class.java)
        intent.putExtra(Constants.TRAINING_ID, id)
        trainingView.startActivity(intent)
    }

    override fun goToEdit(item: Any?) {
        val intent = Intent(trainingView.applicationContext, InsertTrainingDayActivity::class.java)
        intent.putExtra(Constants.LOAD_TRAINING_BOOLEAN, true)
        intent.putExtra(Constants.TRAININGDAY, item as TrainingDay)
        intent.putExtra(Constants.ROUTINE_ID, parentId)
        trainingView.startActivity(intent)
    }

    override fun initData() {
        if (trainingView.intent.extras != null) {
            parentId = trainingView.intent.extras.getInt(Constants.ROUTINE_ID)
        }
    }
}