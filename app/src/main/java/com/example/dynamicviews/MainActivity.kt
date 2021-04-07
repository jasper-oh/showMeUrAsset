package com.example.dynamicviews

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.view.get


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var layoutList: LinearLayout? = null
    var buttonAdd: ImageView? = null
    var buttonCreate: Button? = null

    private var currencyList = arrayListOf<String>()
    var assetLine = arrayListOf<Asset>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutList = findViewById(R.id.layout_list)
        buttonAdd = findViewById(R.id.button_plus)
        buttonCreate = findViewById(R.id.button_create)

        buttonAdd?.setOnClickListener(this)
        buttonCreate?.setOnClickListener(this)

        currencyList.add("$")
        currencyList.add("W")

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button_plus -> addView()

            R.id.button_create -> {
                if (checkIfValidAndRead()) {
                    Log.d("asset","$assetLine")
                    val intent = Intent(this@MainActivity, ActivityAsset::class.java)
                    val bundle = Bundle()
                    bundle.putSerializable("list",assetLine)

                    /*
                     << TODO >>
                     toss the list to Another layout ( Pie Graph )
                    * list[0] = assetName1
                    * list[2] = assetCount1
                    * list[3] = assetPrice1
                    * list[4] = spinnerCurrency1
                    *
                    * P1) all the data type is text
                    *       I believe that the data should be change to Integer(Float Double Long .. not integer)
                    *       such as the assetCount1 & assetPrice1
                    *       - therefore we can put the Sum data automatically.
                    *       - showing data would be on String but on the backend; for calculation.
                    *           they are need to change the type to integer.
                    * */

                    intent.putExtras(bundle)


                    startActivity(intent)
                }
            }

        }
    }

    private fun checkIfValidAndRead(): Boolean {
        assetLine.clear()
        var result = true

        for(i in 0 until layoutList!!.childCount){
            //check num
            Log.d("fuck","${layoutList!!.childCount}")
            var assetView:View = layoutList!!.getChildAt(i)

            var assetName1 = assetView.findViewById<EditText>(R.id.asset_name)
            var assetCount1 = assetView.findViewById<EditText>(R.id.asset_count)
            var assetPrice1 = assetView.findViewById<EditText>(R.id.asset_price)
            var spinnerCurrency1 = assetView.findViewById<AppCompatSpinner>(R.id.spinner_currency)

            //check the value
            Log.d("fuck","${assetName1.text},${assetCount1.text},${assetPrice1.text},${spinnerCurrency1.selectedItemPosition}")


            val asset = Asset()


            if (assetName1.text.toString() != ""){
                asset.assetSearch = assetName1.text.toString()
                Log.d("fuck","${asset.assetSearch}")
                asset.assetCount = assetCount1.text.toString()
                Log.d("fuck","${asset.assetCount}")
                asset.assetPrice = assetPrice1.text.toString()
                Log.d("fuck","${asset.assetPrice}")
            }else{
                result = false
                break
            }

            asset.assetCurrency = currencyList.get(spinnerCurrency1.selectedItemPosition)
            Log.d("fuck","${asset.assetCurrency}")

            assetLine.add(asset)
            Log.d("fuck","${assetLine[0]}")
        }

        if(assetLine.size==0){
            result = false
            Toast.makeText(this, "Add your asset!", Toast.LENGTH_SHORT).show()
        }else if(!result){
            Toast.makeText(this, "Enter All Detail Correctly", Toast.LENGTH_SHORT).show()
        }

        return result
    }

    private fun addView() {
        var assetView = layoutInflater.inflate(R.layout.row_add_asset, null, false)

        var spinnerCurrency = assetView.findViewById<AppCompatSpinner>(R.id.spinner_currency)
        var imageClose = assetView.findViewById<ImageView>(R.id.button_delete)

        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyList)
        spinnerCurrency.adapter = arrayAdapter

        imageClose.setOnClickListener { removeView(assetView) }

        layoutList?.addView(assetView)
    }

    private fun removeView(assetView: View?) {
        layoutList?.removeView(assetView)
    }
}
