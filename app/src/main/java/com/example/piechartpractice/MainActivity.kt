package com.example.piechartpractice

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chart = findViewById<PieChart>(R.id.chart)

        chart.setUsePercentValues(true)

        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(350f, "35%"))
        entries.add(PieEntry(300f, "30%"))
        entries.add(PieEntry(200f, "20%"))
        entries.add(PieEntry(80f, "8%"))
        entries.add(PieEntry(70f, "7%"))

        val colorsItems = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colorsItems.add(c)
        for (c in COLORFUL_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colorsItems.add(c)
        colorsItems.add(ColorTemplate.getHoloBlue())

        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.apply {
            colors = colorsItems
            valueTextColor = Color.BLACK
            valueTextSize = 16f
        }

        val pieData = PieData(pieDataSet)
        chart.apply {
            data = pieData
            description.isEnabled = false
            isRotationEnabled = false
            centerText = "Total"
            setEntryLabelColor(Color.BLACK)
            animateY(1400, Easing.EaseInOutQuad)
            animate()
        }

        val button = findViewById<Button>(R.id.plusbutton)
        button.setOnClickListener {
            val intent = Intent(this, PlusScreen::class.java)
            startActivity(intent)

        }


    }


}