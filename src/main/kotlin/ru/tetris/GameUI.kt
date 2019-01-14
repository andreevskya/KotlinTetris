package ru.tetris

import java.awt.Dimension
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.image.BufferedImage
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

class GameUI constructor(args:Array<String>) {
    val WINDOW_TITLE = "Tetris"

    var frame: JFrame
    var panel: JPanel
    var game:Game
    var canvas: BufferedImage

    init {
        frame = JFrame()
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.title = WINDOW_TITLE

        panel = JPanel();
        panel.preferredSize = Dimension(GameConsts.FIELD_WIDTH_PX, GameConsts.FIELD_HEIGHT_PX)

        canvas = BufferedImage(GameConsts.FIELD_WIDTH_PX, GameConsts.FIELD_HEIGHT_PX, BufferedImage.TYPE_3BYTE_BGR)
        var img = ImageIcon(canvas)
        var label = JLabel(img)
        panel.add(label)

        frame.contentPane = panel

        game = Game(canvas.createGraphics())
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
    }
}