package org.drawmetry.ecolban.robot.examples

import org.jointheleague.graphical.robot.Robot

fun main(vararg args: String) {
    val rob = Robot()
    rob.setSpeed(5)
    rob.penDown()
    rob.turn(-90)
    rob.move(200)
    rob.turn(90)
    rob.move(100)
    rob.turn(90)
    rob.move(200)
    rob.turn(90)
    rob.move(100)
}