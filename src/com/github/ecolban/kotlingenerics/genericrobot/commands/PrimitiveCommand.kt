package com.github.ecolban.kotlingenerics.genericrobot.commands

import org.jointheleague.graphical.robot.Robot

sealed class PrimitiveCommand : RobotCommand()

data class Move(private val distance: Int) : PrimitiveCommand() {

    override fun execute(robot: Robot) {
        robot.move(distance)
    }
}

data class Turn(private val degrees: Int) : PrimitiveCommand() {

    override fun execute(robot: Robot) {
        robot.turn(degrees)
    }
}

data class Pen(private val penDown: Boolean) : PrimitiveCommand() {

    override fun execute(robot: Robot) {
        if (penDown) robot.penDown() else robot.penUp()
    }
}

data class Miniaturize(private val miniaturize: Boolean) : PrimitiveCommand() {
    override fun execute(robot: Robot) {
        if (miniaturize) robot.miniaturize() else robot.expand()
    }
}

data class SetSpeed(private val speed: Int) : PrimitiveCommand() {
    override fun execute(robot: Robot) {
        robot.setSpeed(speed)
    }
}

object Show: PrimitiveCommand() {
    override fun execute(robot: Robot) {
        robot.show()
    }

    override fun toString(): String {
        return "Show()"
    }
}

object Hide: PrimitiveCommand() {
    override fun execute(robot: Robot) {
        robot.hide()
    }

    override fun toString(): String {
        return "Hide()"
    }
}

