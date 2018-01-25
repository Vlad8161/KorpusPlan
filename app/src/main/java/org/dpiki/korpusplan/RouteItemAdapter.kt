package org.dpiki.korpusplan

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by vlad on 29.11.17.
 */

class RouteItemAdapter(
        val data: List<RouteItem>,
        val func: (item: RouteItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        if (holder is RouteItemViewHolder) {
            holder.title.text = item.title
            holder.itemView.setOnClickListener { func(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = RouteItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_route_item, parent, false))

    class RouteItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById(R.id.adapter_route_item_title) as TextView
    }
}