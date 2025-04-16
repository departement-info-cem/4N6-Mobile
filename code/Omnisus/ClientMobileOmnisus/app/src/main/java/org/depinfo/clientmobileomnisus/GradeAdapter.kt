package org.depinfo.clientmobileomnisus

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import org.depinfo.clientmobileomnisus.GradeAdapter.MyViewHolder
import org.depinfo.clientmobileomnisus.http.dto.GradeResponse

class GradeAdapter : RecyclerView.Adapter<MyViewHolder>() {
    var list: MutableList<GradeResponse> = mutableListOf()

    class MyViewHolder(v: ConstraintLayout) : RecyclerView.ViewHolder(v) {
        var tvName: TextView = v.findViewById(R.id.tvName)
        var tvGrade: TextView = v.findViewById(R.id.tvGrade)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.grade_item, parent, false) as ConstraintLayout
        return MyViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val grade: GradeResponse = list[position]
        holder.tvName.text = ("- " + grade.name) + " : "
        holder.tvGrade.text = grade.grade.toString() + " %"
    }

    override fun getItemCount(): Int {
        return list.size
    }
}