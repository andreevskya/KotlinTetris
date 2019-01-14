package ru.tetris

class Figure constructor(figure:Array<Array<Int>>, x:Int, y:Int){
    var x:Int
    var y:Int
    var width:Int
    var height:Int
    var figure:Array<Array<Int>>

    init {
        this.figure = figure
        this.x = x
        this.y = y
        width = getFigureWidth(this.figure)
        height = getFigureHeight(this.figure)
    }

    fun rotate() {
        val rotated = Array(figure[0].size, {Array(figure.size, {GameConsts.CELL_TYPE_VOID})})
        for(yy in 0..rotated.size - 1) {
            for(xx in 0..rotated[0].size - 1) {
                rotated[yy][xx] = figure[xx][yy]
            }
        }
        mirrorY(rotated)
        figure = rotated
        width = getFigureWidth(figure)
        height = getFigureHeight(figure)
    }

    private fun mirrorY(m:Array<Array<Int>>):Array<Array<Int>> {
        for(yy in 0..m.size - 1) {
            for(xx in 0..(m[0].size - 1) / 2) {
                var oldR = m[yy][xx]
                m[yy][xx] = m[yy][m[0].size - 1 - xx]
                m[yy][m[0].size - 1 - xx] = oldR
            }
        }
        return m
    }

    private fun getFigureWidth(figure:Array<Array<Int>>):Int {
        var left = figure[0].size - 1
        var right = 0
        for(xx in 0..figure[0].size - 1) {
            (0..figure.size - 1)
                    .asSequence()
                    .filter { figure[it][xx] == GameConsts.CELL_TYPE_BLOCK }
                    .forEach {
                        if(xx < left) {
                            left = xx
                        }
                    }
        }
        for(xx in figure[0].size - 1 downTo 0 ) {
            (figure.size - 1 downTo 0)
                    .asSequence()
                    .filter { figure[it][xx] == GameConsts.CELL_TYPE_BLOCK }
                    .forEach {
                        if(xx > right) {
                            right = xx
                        }
                    }
        }
        return if(right - left > 0) right - left + 1 else 1
    }

    private fun getFigureHeight(figure:Array<Array<Int>>):Int {
        var top = figure.size
        var bottom = 0
        for(yy in 0..figure.size - 1) {
            (0..figure[0].size - 1)
                    .asSequence()
                    .filter { figure[yy][it] == GameConsts.CELL_TYPE_BLOCK }
                    .forEach {
                        if(top > yy) {
                            top = yy
                        }
                    }
        }
        for(yy in figure.size - 1 downTo 0) {
            (0..figure[0].size - 1)
                    .asSequence()
                    .filter { figure[yy][it] == GameConsts.CELL_TYPE_BLOCK }
                    .forEach {
                        if(bottom < yy) {
                            bottom = yy
                        }
                    }
        }
        return if(bottom - top > 0) bottom - top + 1 else 1
    }
}