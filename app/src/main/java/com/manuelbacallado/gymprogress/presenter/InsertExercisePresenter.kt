package com.manuelbacallado.gymprogress.presenter

import android.content.Intent
import android.text.Editable
import com.manuelbacallado.gymprogress.activities.ExerciseActivity
import com.manuelbacallado.gymprogress.activities.InsertExerciseActivity
import com.manuelbacallado.gymprogress.db.dao.ExerciseDAO
import com.manuelbacallado.gymprogress.interfaces.InitDatabase
import com.manuelbacallado.gymprogress.interfaces.InsertFunctions
import com.manuelbacallado.gymprogress.interfaces.LoadIntentData
import com.manuelbacallado.gymprogress.models.Exercise
import com.manuelbacallado.gymprogress.utils.Constants
import kotlinx.android.synthetic.main.insert_exercise_item.*

class InsertExercisePresenter(var insertExerciseView: InsertExerciseActivity) : InitDatabase, LoadIntentData, InsertFunctions {

    lateinit var exercise: Exercise
    var load: Boolean = false
    private var trainingId : Int = 0
    private lateinit var db : ExerciseDAO

    override fun initDatabase() {
        db = ExerciseDAO(insertExerciseView)
    }

    override fun saveData() {
        var id: Int
        var exerciseAux = Exercise(0,
            insertExerciseView.exerciseText.text.toString(),
            insertExerciseView.initialWeightText.text.toString().toInt(),
            insertExerciseView.finalWeightText.text.toString().toInt(),
            trainingId)
        if (!load) {
            id = 0
            exerciseAux.exerciseId = id
            db.addElement(exerciseAux)
        } else {
            exerciseAux.exerciseId = exercise.exerciseId
            db.editElement(exerciseAux)
        }
        val intent = Intent(insertExerciseView.applicationContext, ExerciseActivity::class.java)
        intent.putExtra(Constants.TRAINING_ID, trainingId)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        insertExerciseView.startActivity(intent)
    }

    override fun loadIntentData() {
        trainingId = insertExerciseView.intent.extras.getInt(Constants.TRAINING_ID)
        load = insertExerciseView.intent.extras.getBoolean(Constants.LOAD_EXERCISE_BOOLEAN)
        if (load != null && load as Boolean){
            loadData()
        }
    }

    override fun loadData() {
        exercise = insertExerciseView.intent.getParcelableExtra<Exercise>(Constants.EXERCISE)
        insertExerciseView.exerciseText.text = Editable.Factory.getInstance().newEditable(exercise.exerciseName)
        insertExerciseView.initialWeightText.text = Editable.Factory.getInstance().newEditable(exercise.initialWeight.toString())
        insertExerciseView.finalWeightText.text = Editable.Factory.getInstance().newEditable(exercise.finalWeight.toString())
    }
}