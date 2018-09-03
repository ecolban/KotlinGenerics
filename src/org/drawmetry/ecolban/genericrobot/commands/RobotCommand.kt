package org.drawmetry.ecolban.genericrobot.commands

import org.jointheleague.graphical.robot.Robot

abstract class RobotCommand {

    abstract fun execute(robot: Robot)


    fun repeat(times: Int) = object : RobotCommand() {
        override fun execute(robot: Robot) {
            for (i in 1..times) {
                this@RobotCommand.execute(robot)
            }
        }

        override fun toString(): String {
            return "${times}x${this@RobotCommand}"
        }
    }
}

fun main(vararg args: String) {
    val command = ComplexCommand(
            Turn(-90),
            ComplexCommand(Move(100), Turn(90)).repeat(4),
            Turn(90),
            Hide)
    println(command)
}





