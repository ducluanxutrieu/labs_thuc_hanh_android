package com.uit.unit1_layoutoverview

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var llNameContainer: LinearLayout
    private lateinit var llAddressContainer: LinearLayout
    private lateinit var llParentContainer: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNameContainer()
        createAddressContainer()
        createParentContainer()
        setContentView(R.layout.activity_main)
//        setContentView(llParentContainer)
        supportActionBar?.title = "Constraint Layout"
    }

    private fun createNameContainer() {
        llNameContainer = LinearLayout(this)
        llNameContainer.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        llNameContainer.orientation = HORIZONTAL

        val tvName = TextView(this)
        tvName.text = "Name: "
        llNameContainer.addView(tvName)

        val tvNameValue = TextView(this)
        tvNameValue.text = "John Doe"
        llNameContainer.addView(tvNameValue)
    }

    private fun createAddressContainer(){
        llAddressContainer = LinearLayout(this)
        llAddressContainer.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        llAddressContainer.orientation = HORIZONTAL

        val tvAddress = TextView(this)
        tvAddress.text = "Address:"
        llAddressContainer.addView(tvAddress)

        val tvAddressValue = TextView(this)
        tvAddressValue.text = "911 Hollywood Blvd"
        llAddressContainer.addView(tvAddressValue)
    }

    private fun createParentContainer() {
        llParentContainer = LinearLayout(this)

        llParentContainer.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        llParentContainer.orientation = LinearLayout.VERTICAL

        llParentContainer.addView(llNameContainer)
        llParentContainer.addView(llAddressContainer)
    }


}
