package ru.tetris

import java.awt.Color
import java.awt.Graphics

class GameRender constructor(var graphics: Graphics) {

    fun drawField(field:Array<IntArray>) {
        graphics.color = Color.BLACK
        graphics.fillRect(0, 0, GameConsts.FIELD_WIDTH_PX, GameConsts.FIELD_HEIGHT_PX)
        for(y in 0..field.size - 1) {
            (0..field[0].size - 1)
                    .filter { field[y][it] == GameConsts.CELL_TYPE_BLOCK }
                    .forEach { drawCell(it, y, Color.GREEN) }
        }
    }

    fun drawFigure(t:Array<Array<Int>>, x:Int, y:Int) {
        for(yy in 0..t.size - 1)  {
            for(xx in 0..t[0].size - 1) {
                if(t[yy][xx] == GameConsts.CELL_TYPE_BLOCK) {
                    drawCell(xx + x , yy + y, Color.GREEN)
                }
            }
        }
    }

    private fun drawCell(x:Int, y:Int, col:Color) {
        graphics.color = col
        graphics.drawRect(
                GameConsts.CELL_SIZE_PX * x + 2,
                GameConsts.CELL_SIZE_PX * y + 2,
                GameConsts.CELL_SIZE_PX - 2,
                GameConsts.CELL_SIZE_PX - 2
        )
    }
}
