package com.ex.mailer

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity

class EmailAdvertisement : AppCompatActivity() {

    private lateinit var region: AutoCompleteTextView

    override fun onCreate(savedINstanceState: Bundle?){
        super.onCreate(savedINstanceState)
        setContentView(R.layout.email_advertisement)

        region = findViewById(R.id.region)
        region.setAdapter(ArrayAdapter<String>(this@EmailAdvertisement, android.R.layout.simple_list_item_1, arrayOf("USA", "UK", "France", "Portugal")))
    }
}