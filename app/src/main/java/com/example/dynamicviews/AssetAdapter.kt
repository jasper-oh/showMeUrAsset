package com.example.dynamicviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AssetAdapter : RecyclerView.Adapter<AssetAdapter.AssetView> {

    var assetList:ArrayList<Asset> = ArrayList()

    constructor(assetList: ArrayList<Asset>) : super() {
        this.assetList = assetList
    }

    class AssetView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textAssetSearch:TextView? = itemView.findViewById(R.id.text_asset_search)
        var textAssetCount:TextView? = itemView.findViewById(R.id.text_asset_count)
        var textAssetPrice:TextView? = itemView.findViewById(R.id.text_asset_price)
        var textAssetCurrency:TextView? = itemView.findViewById(R.id.text_asset_currency)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetView {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.row_asset, parent, false)

        return AssetView(view)
    }

    override fun onBindViewHolder(holder: AssetView, position: Int) {
        var asset:Asset = assetList.get(position)
        holder.textAssetSearch?.text = asset.assetSearch
        holder.textAssetCount?.text = asset.assetCount
        holder.textAssetPrice?.text = asset.assetPrice
        holder.textAssetCurrency?.text = asset.assetCurrency
    }

    override fun getItemCount(): Int {
        return assetList.size
    }
}

