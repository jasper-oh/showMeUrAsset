package com.example.dynamicviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActivityAsset: AppCompatActivity() {

    var recyclerAsset:RecyclerView? = null
    var assetLine = arrayListOf<Asset>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asset)
        recyclerAsset = findViewById(R.id.recycler_asset)

        var layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)

        recyclerAsset?.layoutManager = layoutManager

        assetLine = intent.extras!!.getSerializable("list") as ArrayList<Asset>

        recyclerAsset?.adapter = AssetAdapter(assetList = assetLine)
    }
}