package com.example.drivershipment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.internal.ContextUtils.getActivity
import org.json.JSONObject
import  java.io.Serializable


class MainActivity : AppCompatActivity() {
    val jsonString = """
    {
      "shipments": [
        "215 Osinski Manors",
        "9856 Marvin Stravenue",
        "7127 Kathlyn Ferry",
        "987 Champlin Lake",
        "63187 Volkman Garden Suite 447",
        "75855 Dessie Lights",
        "1797 Adolf Island Apt. 744",
        "2431 Lindgren Corners",
        "8725 Aufderhar River Suite 859",
        "79035 Shanna Light Apt. 322"
      ],
      "drivers": [
        "Everardo Welch",
        "Orval Mayert",
        "Howard Emmerich",
        "Izaiah Lowe",
        "Monica Hermann",
        "Ellis Wisozk",
        "Noemie Murphy",
        "Cleve Durgan",
        "Murphy Mosciski",
        "Kaiser Sose"
      ]
    }
    """
    var simpleList: ListView? = null
    var driverList =  mutableListOf<DriverShipment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val json = JSONObject(jsonString)
        val shipments = json.getJSONArray("shipments")
        val drivers = json.getJSONArray("drivers")
        val driverShipmentScore = mutableListOf<DriverShipment>()

        for (i in 0 until shipments.length()) {
            val shipment = shipments.getString(i)
            for (j in 0 until drivers.length()) {
                val driver = drivers.getString(j)
                driverShipmentScore.add(DriverShipment(driver, shipment, calculateSuitabilityScore(shipment, driver)))
            }
        }
        driverShipmentScore.sortByDescending { it.score }
        driverShipmentScore.forEach {
            println("Driver: ${it.driver} , Shipment: ${it.shipment} , score: ${it.score}")
            var driver = it
            if (driverList.filter { it.driver ==  driver.driver ||  it.shipment ==  driver.shipment}.count() > 0){

            }else{
                driverList.add(it)
            }
        }
        setupDrivers()

    }

fun setupDrivers(){
    simpleList = findViewById<View>(R.id.simpleListView) as ListView

    val customAdapter = CustomAdapter(applicationContext, driverList.toTypedArray())
    simpleList!!.adapter = customAdapter
    simpleList!!.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
        var driver = driverList[position]
        println("Driver: ${driver.driver} , Shipment: ${driver.shipment} , score: ${driver.score}")
        val intent = Intent(application, DriverShipmentDetailActivity::class.java)
        intent.putExtra("driver", driver as Serializable)

        this.startActivity(intent)
    })

}

fun calculateSuitabilityScore(destinationStreetName: String, driverName: String): Double {
    var baseSS = 0.0
    if (destinationStreetName.length % 2 == 0) {
        baseSS = driverName.filter { it in "aeiouAEIOU" }.length * 1.5
    } else {
        baseSS = driverName.filter { it !in "aeiouAEIOU" }.length * 1.0
    }
    if (destinationStreetName.length % driverName.length != 1) {
        baseSS *= 1.5
    }
    return baseSS
}

}