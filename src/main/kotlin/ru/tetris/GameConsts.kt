package ru.tetris

object GameConsts {
    const val CELL_SIZE_PX = 10
    const val FIELD_WIDTH_CELLS = 20
    const val FIELD_HEIGHT_CELLS = 40
    const val FIELD_WIDTH_PX = CELL_SIZE_PX * FIELD_WIDTH_CELLS
    const val FIELD_HEIGHT_PX = CELL_SIZE_PX * FIELD_HEIGHT_CELLS

    const val CELL_TYPE_VOID = 0
    const val CELL_TYPE_BLOCK = 1

    const val FIGURE_SPAWN_POINT_X = FIELD_WIDTH_CELLS / 2
    const val FIGURE_SPAWN_POINT_Y = 4

    val FIGURE_T = arrayOf(
            arrayOf(CELL_TYPE_VOID, CELL_TYPE_BLOCK, CELL_TYPE_VOID),
            arrayOf(CELL_TYPE_BLOCK, CELL_TYPE_BLOCK, CELL_TYPE_BLOCK)
    )

    val FIGURE_LINE = arrayOf(
            arrayOf(CELL_TYPE_BLOCK),
            arrayOf(CELL_TYPE_BLOCK),
            arrayOf(CELL_TYPE_BLOCK),
            arrayOf(CELL_TYPE_BLOCK)
    )

    val FIGURE_BLOCK = arrayOf(
            arrayOf(CELL_TYPE_BLOCK, CELL_TYPE_BLOCK),
            arrayOf(CELL_TYPE_BLOCK, CELL_TYPE_BLOCK)
    )

    val FIGURE_L = arrayOf(
            arrayOf(CELL_TYPE_BLOCK, CELL_TYPE_VOID),
            arrayOf(CELL_TYPE_BLOCK, CELL_TYPE_VOID),
            arrayOf(CELL_TYPE_BLOCK, CELL_TYPE_BLOCK)
    )

    val FIGURE_L_INVERTED = arrayOf(
            arrayOf(CELL_TYPE_VOID, CELL_TYPE_BLOCK),
            arrayOf(CELL_TYPE_VOID, CELL_TYPE_BLOCK),
            arrayOf(CELL_TYPE_BLOCK, CELL_TYPE_BLOCK)
    )

    val FIGURE_S = arrayOf(
            arrayOf(CELL_TYPE_BLOCK, CELL_TYPE_BLOCK, CELL_TYPE_VOID),
            arrayOf(CELL_TYPE_VOID, CELL_TYPE_BLOCK, CELL_TYPE_BLOCK)
    )

    val FIGURE_S_INVERTED = arrayOf(
            arrayOf(CELL_TYPE_VOID, CELL_TYPE_BLOCK, CELL_TYPE_BLOCK),
            arrayOf(CELL_TYPE_BLOCK, CELL_TYPE_BLOCK, CELL_TYPE_VOID)
    )

    val FIGURES = arrayOf(
            FIGURE_T,
            FIGURE_LINE,
            FIGURE_BLOCK,
            FIGURE_L,
            FIGURE_L_INVERTED,
            FIGURE_S,
            FIGURE_S_INVERTED
    )
}
