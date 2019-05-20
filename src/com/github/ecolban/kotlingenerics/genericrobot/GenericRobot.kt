package com.github.ecolban.kotlingenerics.genericrobot

import com.github.ecolban.kotlingenerics.genericrobot.commands.*
import org.jointheleague.graphical.robot.Robot
import org.jointheleague.graphical.robot.RobotInterface

class GenericRobot<in T : RobotCommand>(private val robby: Robot) : RobotInterface by robby {

    private fun execute(command: T) {
        command.execute(robby)
    }

    fun execute(commands: Array<out T>) {
        for (command in commands) {
            execute(command)
        }
    }

}

//fun <T : RobotCommand> GenericRobot<T>.execute(commands: Array<T>) {
//    for (command in commands) {
//        execute(command)
//    }
//}

val primitiveDrawSquare = arrayOf(
        Miniaturize(true),
        Pen(true),
        SetSpeed(6),
        Turn(-90),
        Move(100),
        Turn(90),
        Move(100),
        Turn(90),
        Move(100),
        Turn(90),
        Move(100))

val dashedLine = ComplexCommand(
        Move(5),
        Pen(false),
        Move(10),
        Pen(true),
        Move(5)).repeat(5)

val complexDrawSquare = arrayOf(
        Pen(true),
        SetSpeed(6),
        Turn(-90),
        ComplexCommand(dashedLine, Turn(90)).repeat(3),
        dashedLine)


fun main(vararg args: String) {

    val rob = GenericRobot<RobotCommand>(Robot())
    rob.execute(complexDrawSquare)
    rob.execute(primitiveDrawSquare)
    rob.penUp()
}
