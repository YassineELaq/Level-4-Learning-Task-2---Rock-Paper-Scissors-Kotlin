package com.example.task2level4yassine

import kotlin.random.Random

/**
 * @author Yassine el Aatiaoui
 * Student Nr 500767860
 */

/**
 * here is a Ramdom Enum class to get random integer for the choices
 */

class RandumEnum {

    companion object CompanionObject {
        private  val randomNumber = Math.random().toInt()
        private val random = Random(randomNumber)

        //    Kies een willekeurige waarde van de options enum.
        fun randomEnum():Options{
            return Options.values()[random.nextInt(Options.values().size)]
        }
    }



}