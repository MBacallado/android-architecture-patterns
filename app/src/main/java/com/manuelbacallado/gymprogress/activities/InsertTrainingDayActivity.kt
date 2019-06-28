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
import com.manuelbacallado.gymprogress.db.TrainingDaysDBOpenHelper
import com.manuelbacallado.gymprogress.models.TrainingDay
import kotlinx.android.synthetic.main.insert_training_day_item.*

class InsertTrainingDayActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var trainingDay: TrainingDay
    var spinnerTrainingDay: String = ""
    var load: Boolean = false
    private lateinit var db : TrainingDaysDBOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insert_training_day_item)

        db = TrainingDaysDBOpenHelper(this)
        setDaysSpinner()
        load = intent.extras.getBoolean("loadTrainingDay")
        if (load != null && load as Boolean){
            loadData()
        }
        saveData()
    }

    private fun saveData() {
        buttonSave.setOnClickListener() {
            var trainingDayAux : TrainingDay
            var id: Int
            trainingDayAux = TrainingDay(0,
                spinnerTrainingDay,
                timeText.text.toString().toInt(),
                groupText.text.toString(),
                intent.extras.getInt("routineId"))
            if (!load) {
                id = 0
                trainingDayAux.trainingDayId = id
                db.addElement(trainingDayAux)
            } else {
                trainingDayAux.trainingDayId = trainingDay.trainingDayId
                db.editElement(trainingDayAux)
            }
            startActivity(Intent(applicationContext, TrainingDayActivity::class.java))
        }
    }

    private fun loadData() {
        //load = intent.extras.getBoolean("loadTrainingDay")
        trainingDay = intent.getParcelableExtra<TrainingDay>("trainingDay")
        //if (load != null && load as Boolean){
        timeText.text = Editable.Factory.getInstance().newEditable(trainingDay.timeAmount.toString())
        groupText.text = Editable.Factory.getInstance().newEditable(trainingDay.group)
        fillTrainingDaysNameSpinner(trainingDay.day);
        //}
    }

    private fun setDaysSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTrainingDays.onItemSelectedListener = this
        spinnerTrainingDays.adapter = adapter
    }

    private fun fillTrainingDaysNameSpinner(trainingDaysName: String) {
        val trainingDaysArray = resources.getStringArray(R.array.days)
        for (i in 0..trainingDaysArray.size-1) {
            if (trainingDaysArray.get(i).equals(trainingDaysName)) {
                Log.d("TRAINING DAY","TRAINING DAY: ${trainingDaysArray.get(i).toString()}")
                spinnerTrainingDays.setSelection(i)
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        spinnerTrainingDay = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
