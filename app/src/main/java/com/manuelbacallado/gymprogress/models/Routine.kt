package com.manuelbacallado.gymprogress.models

import java.time.LocalDate

data class Routine(var routineId : Int, var name : String, var startDate : LocalDate, var finishDate : LocalDate,
                   var trainingDays : Int, var trainingTypes : String)