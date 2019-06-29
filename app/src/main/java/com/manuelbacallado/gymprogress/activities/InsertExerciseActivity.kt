package com.manuelbacallado.gymprogress.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.db.dao.ExerciseDAO
import com.manuelbacallado.gymprogress.models.Exercise
import com.manuelbacallado.gymprogress.utils.Constants
import kotlinx.android.synthetic.main.insert_exercise_item.*

class InsertExerciseActivity : AppCompatActivity() {

    lateinit var exercise: Exercise
    var load: Boolean = false
    private var trainingId : Int = 0
    private lateinit var db : ExerciseDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insert_exercise_item)

        db = ExerciseDAO(this)
        trainingId = intent.extras.getInt(Constants.TRAINING_ID)
        load = intent.extras.getBoolean(Constants.LOAD_TRAINING_BOOLEAN)
        if (load != null && load as Boolean){
            loadData()
        }
        saveData()
    }

    private fun saveData() {
        buttonSave.setOnClickListener() {
            var exerciseAux : Exercise
            var id: Int
            exerciseAux = Exercise(0,
                exerciseText.text.toString(),
                initialWeightText.text.toString().toInt(),
                finalWeightText.text.toString().toInt(),
                trainingId)
            if (!load) {
                id = 0
                exerciseAux.exerciseId = id
                db.addElement(exerciseAux)
            } else {
                exerciseAux.exerciseId = exercise.exerciseId
                db.editElement(exerciseAux)
            }
            val intent = Intent(applicationContext, ExerciseActivity::class.java)
            intent.putExtra(Constants.TRAINING_ID, trainingId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    private fun loadData() {
        exercise = intent.getParcelableExtra<Exercise>(Constants.EXERCISE)
        exerciseText.text = Editable.Factory.getInstance().newEditable(exercise.exerciseName)
        initialWeightText.text = Editable.Factory.getInstance().newEditable(exercise.initialWeight.toString())
        initialWeightText.text = Editable.Factory.getInstance().newEditable(exercise.finalWeight.toString())
    }
}
