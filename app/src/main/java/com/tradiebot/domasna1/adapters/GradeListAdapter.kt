package com.tradiebot.domasna1.adapters

import android.annotation.SuppressLint
import android.graphics.Color

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.tradiebot.domasna1.R
import com.tradiebot.domasna1.model.Grade


class GradeListAdapter(private var dataSet: ArrayList<Grade>) :
    RecyclerView.Adapter<GradeListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gradeScore : TextView = view.findViewById(R.id.grade_score)
        val gradeName: TextView = view.findViewById(R.id.grade_name)
        val gradeCard : MaterialCardView = view.findViewById(R.id.grade_card)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.grade_card, viewGroup, false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val colors = arrayOf(
            Color.parseColor("#FF3924"),
            Color.parseColor("#FF9600"),
            Color.parseColor("#FFE500"),
            Color.parseColor("#CDF03A"),
            Color.parseColor("#2CE574"),
            Color.parseColor("#7986CB")
        )

        viewHolder.gradeName.text = dataSet[position].name
        viewHolder.gradeScore.text = dataSet[position].score.toString()
        viewHolder.gradeCard.setCardBackgroundColor(colors[dataSet[position].score - 5])

    }
    fun addGrade(grade:Grade){
        dataSet.add(grade)
        Handler(Looper.getMainLooper()).post {
            notifyItemInserted(itemCount)
        }
    }
    override fun getItemCount() = dataSet.size
}