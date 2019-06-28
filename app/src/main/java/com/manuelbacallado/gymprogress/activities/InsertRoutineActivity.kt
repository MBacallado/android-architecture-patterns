package com.manuelbacallado.gymprogress.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.db.RoutineDBOpenHelper
import com.manuelbacallado.gymprogress.models.Routine
import kotlinx.android.synthetic.main.insert_routine_item.*

class InsertRoutineActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var routine: Routine
    var spinnerDay: String = ""
    var spinnerTypes: String = ""
    var load: Boolean = false
    private lateinit var db : RoutineDBOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insert_routine_item)

        db = RoutineDBOpenHelper(this)
        setTrainingDaysSpinner()
        setTrainingTypeSpinner()
        load = intent.extras.getBoolean("loadRoutine")
        if (load != null && load as Boolean) {
            loadData()
        }
        saveData()
    }

    private fun saveData() {
        buttonSave.setOnClickListener() {
            var routineAux : Routine
            var id: Int
            routineAux = Routine(0,
                routineText.text.toString(),
                startDateText.text.toString(),
                finishDateText.text.toString(),
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
            startActivity(Intent(applicationContext, RoutineActivity::class.java))
        }
    }

    private fun loadData() {
        routine = intent.getParcelableExtra<Routine>("routine")
        //if (load != null && load as Boolean){
        routineText.text = Editable.Factory.getInstance().newEditable(routine.name)
        startDateText.text = Editable.Factory.getInstance().newEditable(routine.startDate)
        finishDateText.text = Editable.Factory.getInstance().newEditable(routine.finishDate)
        fillTrainingDaysSpinner(routine.trainingDays)
        fillTrainingTypeSpinner(routine.trainingTypes)
        //}
    }

    private fun setTrainingDaysSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDays.onItemSelectedListener = this
        spinnerDays.adapter = adapter
    }

    private fun fillTrainingDaysSpinner(trainingDays: Int) {
        val numbersArray = resources.getStringArray(R.array.numbers)
        for (i in 0..numbersArray.size-1) {
            if (numbersArray.get(i).toInt() == trainingDays) {
                Log.d("TRAINING DAY","TRAINING DAY: ${numbersArray.get(i).toString()}")
                spinnerDays.setSelection(i)
            }
        }
    }

    private fun setTrainingTypeSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.trainingType, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerType.onItemSelectedListener = this
        spinnerType.adapter = adapter
    }

    private fun fillTrainingTypeSpinner(trainingTypes: String) {
        val typesArray = resources.getStringArray(R.array.trainingType)
        for (i in 0..typesArray.size-1) {
            if (typesArray.get(i).equals(trainingTypes)) {
                Log.d("TRAINING TYPE","TRAINING TYPE: ${typesArray.get(i).toString()}")
                spinnerType.setSelection(i)
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id) {
            spinnerDays.id -> spinnerDay = parent?.getItemAtPosition(position).toString()
            spinnerType.id -> spinnerTypes = parent?.getItemAtPosition(position).toString()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
