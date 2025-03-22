package org.sabourin.swiperefresh

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.sabourin.swiperefresh.TrucAdapteur.MyViewHolder

class TrucAdapteur : RecyclerView.Adapter<MyViewHolder>() {
    var list: MutableList<Truc?> = mutableListOf()

    class MyViewHolder(v: LinearLayout) : RecyclerView.ViewHolder(v) {
        var tvNom: TextView = v.findViewById(R.id.tv)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false) as LinearLayout
        val vh = MyViewHolder(v)
        Log.i("DEBOGAGE", "appel a onCreateViewHolder")
        return vh
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val courant = list[position]
        if (courant != null) {
            holder.tvNom.text = courant.b + "  " + courant.c!!.size + " " + courant.a
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}