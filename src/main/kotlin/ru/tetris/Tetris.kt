package ru.tetris

import javax.swing.SwingUtilities

fun main(args: Array<String>) {
    SwingUtilities.invokeLater { GameUI(args) }
}
