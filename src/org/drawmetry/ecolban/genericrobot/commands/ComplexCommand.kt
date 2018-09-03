package org.drawmetry.ecolban.genericrobot.commands

import org.jointheleague.graphical.robot.Robot

class ComplexCommand(private vararg val commands: RobotCommand) : RobotCommand() {

    init {
        if (commands.isEmpty()) {
            throw IllegalArgumentException("There must be at least one command.")
        }
    }

    override fun execute(robot: Robot) {
        for (command in commands) {
            command.execute(robot)
        }
    }

    override fun toString(): String {
        return commands.joinToString(prefix = "[", postfix = "]")
    }
}

fun main(vararg args: String) {
    val command = ComplexCommand(
            Hide,
            Pen(true),
            SetSpeed(6),
            Turn(-90),
            ComplexCommand(Move(100), Turn(90)).repeat(3),
            Move(100))
    println(command)
}
