package ru.tetris

import java.awt.Dimension
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.image.BufferedImage
import javax.swing.*


class GameUI constructor(args:Array<String>):GameEventListener {
    val WINDOW_TITLE = "Tetris"

    var frame = JFrame()
    var panel = JPanel()
    var game:Game
    var canvas: BufferedImage
    var timer:Timer
    var score = 0

    init {
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.title = WINDOW_TITLE

        panel.preferredSize = Dimension(GameConsts.FIELD_WIDTH_PX, GameConsts.FIELD_HEIGHT_PX)

        canvas = BufferedImage(GameConsts.FIELD_WIDTH_PX, GameConsts.FIELD_HEIGHT_PX, BufferedImage.TYPE_3BYTE_BGR)
        val img = ImageIcon(canvas)
        val label = JLabel(img)
        panel.add(label)

        frame.contentPane = panel

        game = Game(canvas.createGraphics(), this)
        game.update()

        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
        frame.addKeyListener(object : KeyListener {
            override fun keyTyped(e: KeyEvent?) {

            }

            override fun keyPressed(e: KeyEvent?) {
                when(e!!.keyCode) {
                    KeyEvent.VK_UP -> game.move(Direction.UP)
                    KeyEvent.VK_DOWN -> game.move(Direction.DOWN)
                    KeyEvent.VK_LEFT -> game.move(Direction.LEFT)
                    KeyEvent.VK_RIGHT -> game.move(Direction.RIGHT)
                }
                game.update()
                frame.repaint()
            }

            override fun keyReleased(e: KeyEvent?) {

            }
        })
        timer = Timer(400) { e ->
            game.move(Direction.DOWN)
            game.update()
            frame.repaint()
        }
        timer.start()
    }

    override fun onLineCompleted(numLinesComplete: Int) {
        if(score == 0) {
            score = 1
        }
        score *= numLinesComplete
        frame.title = "$WINDOW_TITLE [$score m tall]"
    }

    override fun onGameOver() {
        score = 0
        game.reset()
    }
}