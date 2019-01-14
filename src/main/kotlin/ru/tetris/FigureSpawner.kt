package ru.tetris

import java.util.*

class FigureSpawner {
    var random:Random = Random()

    fun spawn():Array<Array<Int>>  {
        return GameConsts.FIGURES[random.nextInt(GameConsts.FIGURES.size)]
    }
}