package com.github.ecolban.kotlingenerics.robot.examples

import org.jointheleague.graphical.robot.Robot
import java.awt.Color
import java.util.concurrent.Executors
import java.lang.System.currentTimeMillis as now


class RegularPolygon(private val numVertices: Int, private val sideLength: Int, x: Int = 400, y: Int = 300)
    : Robot(x, y), Runnable {

    override fun run() {
        println("${Thread.currentThread().name} running...")
        val start = now()
        miniaturize()
        setSpeed(6)
        penDown()
        turn(-90)
        drawPolygon()
        penUp()
        println("${Thread.currentThread().name} done in ${now() - start}ms!")
    }

    private fun drawPolygon() {
        val degrees = 360 / numVertices
        for (i in 1..numVertices) {
            move(sideLength)
            turn(degrees)
        }
    }
}


fun main(vararg args: String) {
    Robot.setWindowColor(Color.WHITE)
    val triangle = RegularPolygon(numVertices = 3, sideLength = 100, x = 200)
    val hexagon = RegularPolygon(numVertices = 6, sideLength = 50, x = 450)
    val pentagon = RegularPolygon(numVertices = 5, sideLength = 60, x = 700)
    val executor = Executors.newFixedThreadPool(3)
    for (robot in arrayOf(triangle, hexagon, pentagon)) {
        executor.execute(robot)
    }
}