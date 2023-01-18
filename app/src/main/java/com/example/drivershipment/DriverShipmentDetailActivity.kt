package com.example.drivershipment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DriverShipmentDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_shipment_detail)
        val driver = intent.extras?.get("driver") as DriverShipment
        val score = findViewById<TextView>(R.id.score)
        score.text = "Score "+driver.score.toString()

        val driverName = findViewById<TextView>(R.id.driverName)
        driverName.text = "Driver Name: "+driver.driver


        val shipment = findViewById<TextView>(R.id.shipment)
        shipment.text = "Shipment Address: "+driver.shipment
    }
}