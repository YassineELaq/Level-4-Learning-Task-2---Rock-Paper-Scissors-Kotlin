package com.example.task2level4yassine

/**
 * @author Yassine el Aatiaoui
 * Student Nr 500767860
 */

class ConvertImage {
    companion object CompanionObject {

        fun getDrawableFromChoice(result: String): Int {
            if (result == Options.SCISSOR.toString()) {
                return R.drawable.scissors
            }
            if (result == Options.PAPER.toString()) {
                return R.drawable.paper
            }
            return R.drawable.rock
        }

    }

}