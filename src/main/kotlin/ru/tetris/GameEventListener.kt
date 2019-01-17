package ru.tetris

interface GameEventListener {

    fun onLineCompleted(numLinesComplete:Int)
    fun onGameOver()
}
