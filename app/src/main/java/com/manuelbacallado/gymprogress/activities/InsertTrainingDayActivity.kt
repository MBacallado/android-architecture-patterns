package com.manuelbacallado.gymprogress.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.db.dao.TrainingDaysDAO
import com.manuelbacallado.gymprogress.models.TrainingDay
import com.manuelbacallado.gymprogress.utils.Constants
import kotlinx.android.synthetic.main.insert_training_day_item.*

class InsertTrainingDayActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var trainingDay: TrainingDay
    var spinnerTrainingDay: String = ""
    var load: Boolean = false
    private var routineId : Int = 0
    private lateinit var db : TrainingDaysDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insert_training_day_item)

        db = TrainingDaysDAO(this)
        routineId = intent.extras.getInt(Constants.ROUTINE_ID)
        setDaysSpinner()
        load = intent.extras.getBoolean(Constants.LOAD_TRAINING_BOOLEAN)
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
                routineId)
            if (!load) {
                id = 0
                trainingDayAux.trainingDayId = id
                db.addElement(trainingDayAux)
            } else {
                trainingDayAux.trainingDayId = trainingDay.trainingDayId
                db.editElement(trainingDayAux)
            }
            val intent = Intent(applicationContext, TrainingDayActivity::class.java)
            intent.putExtra(Constants.ROUTINE_ID, routineId)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or  Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    private fun loadData() {
        trainingDay = intent.getParcelableExtra<TrainingDay>(Constants.TRAININGDAY)
        timeText.text = Editable.Factory.getInstance().newEditable(trainingDay.timeAmount.toString())
        groupText.text = Editable.Factory.getInstance().newEditable(trainingDay.group)
        fillTrainingDaysNameSpinner(trainingDay.day);
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
