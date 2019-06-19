package com.manuelbacallado.gymprogress.models

import java.util.*

data class Routine(var name : String, var startDate : Date, var finishDate : Date,
                   var trainingDays : Int, var trainingTypes : String)