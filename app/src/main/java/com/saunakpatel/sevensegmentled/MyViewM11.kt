package com.saunakpatel.sevensegmentled

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MyViewM11 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr){

    var secondDigit: Boolean = false
    var activeColor = Color.GREEN
    var inactiveColor = Color.BLACK
    var background = Color.BLACK
    var limit = 0f


    private val activePaint = Paint().apply {
        color = activeColor
        style = Paint.Style.FILL

    }

    private val inactivePaint = Paint().apply {
        color = inactiveColor
        style = Paint.Style.FILL
    }

    private val backgroundPaint = Paint().apply {
        color = background
        style = Paint.Style.FILL
    }

    private var tensDigit: Int = 0
    private var unitsDigit: Int = 0

    fun setNumber(number: Int) {
        tensDigit = number / 10
        unitsDigit = number % 10
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val padding = 20f
        if(secondDigit) {
            canvas.drawRect(
                50f - padding + limit,
                50f - padding + limit,
                350f + padding - limit,
                600f + padding - limit,
                backgroundPaint
            )
            drawSegments(canvas, tensDigit, 0f)
        }
            canvas.drawRect(
                400f - padding + limit,
                50f - padding + limit,
                700f + padding - limit,
                600f + padding - limit,
                backgroundPaint
            )
            drawSegments(canvas, unitsDigit, 350f)

    }

    private fun drawSegments(canvas: Canvas, digit: Int, offsetX: Float) {

        val segments = getSegmentsForDigit(digit)

        val segmentCords = arrayOf(
            floatArrayOf(100f + offsetX, 50f, 300f + offsetX, 100f),   // Segment A
            floatArrayOf(300f + offsetX, 100f, 350f + offsetX, 300f),  // Segment B
            floatArrayOf(300f + offsetX, 350f, 350f + offsetX, 550f),  // Segment C
            floatArrayOf(100f + offsetX, 550f, 300f + offsetX, 600f),  // Segment D
            floatArrayOf(50f + offsetX, 350f, 100f + offsetX, 550f),   // Segment E
            floatArrayOf(50f + offsetX, 100f, 100f + offsetX, 300f),   // Segment F
            floatArrayOf(100f + offsetX, 300f, 300f + offsetX, 350f)   // Segment G
        )


        for (i in segments.indices) {
            val paint = if (segments[i]) activePaint else inactivePaint
            canvas.drawRect(
                segmentCords[i][0],
                segmentCords[i][1],
                segmentCords[i][2],
                segmentCords[i][3],
                paint
            )
        }
    }

    private fun getSegmentsForDigit(digit: Int): BooleanArray {

        return when (digit) {
            0 -> booleanArrayOf(true, true, true, true, true, true, false)
            1 -> booleanArrayOf(false, true, true, false, false, false, false)
            2 -> booleanArrayOf(true, true, false, true, true, false, true)
            3 -> booleanArrayOf(true, true, true, true, false, false, true)
            4 -> booleanArrayOf(false, true, true, false, false, true, true)
            5 -> booleanArrayOf(true, false, true, true, false, true, true)
            6 -> booleanArrayOf(true, false, true, true, true, true, true)
            7 -> booleanArrayOf(true, true, true, false, false, false, false)
            8 -> booleanArrayOf(true, true, true, true, true, true, true)
            9 -> booleanArrayOf(true, true, true, true, false, true, true)
            else -> booleanArrayOf(false, false, false, false, false, false, false)
        }
    }
}