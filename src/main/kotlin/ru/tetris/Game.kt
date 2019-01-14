package ru.tetris

import java.awt.Graphics

class Game constructor(graphics: Graphics) {
    var render:GameRender = GameRender(graphics)
    var spawner = FigureSpawner()

    var gameField = Array(GameConsts.FIELD_HEIGHT_CELLS, {IntArray(GameConsts.FIELD_WIDTH_CELLS)})

    var figure:Figure? = null

    init {
        spawnFigure()
    }

    fun move(direction:Direction) {
        when(direction) {
            Direction.UP -> {
                if(figure?.y == 0)  {
                    return
                }
                figure?.rotate()
            }
            Direction.DOWN -> {
                if(!canBeMoved(figure!!, direction)) {
                    placeFigure(figure!!.figure, figure!!.x, figure!!.y)
                    grow()
                    spawnFigure()
                    if(!canBeMoved(figure!!, Direction.DOWN)) {
                        clear()
                        spawnFigure()
                    }
                    return
                }
                figure?.y = figure?.y!!.plus(1)
            }
            Direction.LEFT -> {
                if(figure?.x == 0) {
                    return
                }
                figure?.x = figure?.x!!.minus(1)
            }
            Direction.RIGHT -> {
                if(figure?.x == GameConsts.FIELD_WIDTH_CELLS - figure!!.width) {
                    return
                }
                figure?.x = figure?.x!!.plus(1)
            }
        }
    }

    fun update() {
        render.drawField(gameField)
        render.drawFigure(figure!!.figure, figure!!.x, figure!!.y)
    }

    private fun placeFigure(figure:Array<Array<Int>>, x:Int, y: Int) {
        for(yy in 0..figure.size - 1)  {
            (0..figure[0].size - 1).filter {
                figure[yy][it] == GameConsts.CELL_TYPE_BLOCK
            }.forEach {
                gameField[yy + y][it + x] = figure[yy][it]
            }
        }
    }

    private fun canBeMoved(figure:Figure, direction:Direction):Boolean {
        when(direction) {
            Direction.DOWN -> {
                if(figure.y == GameConsts.FIELD_HEIGHT_CELLS - 1 - figure.height) {
                    return false
                }
                for(yy in figure.height - 1 downTo 0) {
                    for (xx in 0..figure.width - 1) {
                        if (figure.figure[yy][xx] == GameConsts.CELL_TYPE_BLOCK &&
                                gameField[figure.y + yy + 1][figure.x + xx] == GameConsts.CELL_TYPE_BLOCK) {
                            return false
                        }
                    }
                }
                return true
            }
        }
        return false
    }

    private fun spawnFigure() {
        figure = Figure(spawner.spawn(), 0, GameConsts.FIGURE_SPAWN_POINT_Y)
        figure!!.x = GameConsts.FIGURE_SPAWN_POINT_X - (figure!!.width / 2)
    }

    private fun grow() {
        for(y in 0..gameField.size - 1) {
            var fill = 0
            for(x in 0..gameField[0].size - 1) {
                if(gameField[y][x] == GameConsts.CELL_TYPE_BLOCK) {
                    ++fill
                }
            }
            if(fill < gameField[0].size) {
                continue
            }
            for(x in 0..gameField[0].size - 1) {
                gameField[y][x] = GameConsts.CELL_TYPE_VOID
            }
            for(yy in y downTo 1) {
                for(x in 0..gameField[0].size - 1) {
                    gameField[yy][x] = gameField[yy - 1][x]
                }
            }
        }
    }

    private fun clear() {
        for(y in 0..gameField.size - 1) {
            for(x in 0..gameField[0].size - 1) {
                gameField[y][x] = GameConsts.CELL_TYPE_VOID
            }
        }
    }
}