package com.tradiebot.domasna1

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.tradiebot.domasna1.adapters.GradeListAdapter
import com.tradiebot.domasna1.model.Grade
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    lateinit var grades: ArrayList<Grade>
    lateinit var gradeList: RecyclerView
    val dmFormat = DecimalFormat("0.00")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        gradeList = findViewById(R.id.grade_holder)

        gradeList.layoutManager = LinearLayoutManager(this)

        grades = arrayListOf(
            Grade("test 1", 5),
            Grade("test 2", 6),
            Grade("test 3", 7),
            Grade("test 4", 8),
            Grade("test 5", 9),
            Grade("test 6", 10),
        )
        findViewById<TextView>(R.id.grade_avg).text = "Average: ${dmFormat.format(grades.sumOf{it.score} * 1.0f / grades.size)}"

        gradeList.adapter = GradeListAdapter(grades)
        findViewById<com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton>(R.id.add_grade_fab).setOnClickListener {
            showAddMoodDialog()
        }

    }

    fun showAddMoodDialog(){
        Handler(Looper.getMainLooper()).post {
            val dialog = Dialog(this,android.R.style.Theme_NoTitleBar_Fullscreen)
            dialog.setContentView(R.layout.add_grade_dialog)
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

            dialog.findViewById<Button>(R.id.cancel_button).setOnClickListener { dialog.dismiss() }
            val gradeName = dialog.findViewById<TextInputEditText>(R.id.grade_name)
            val gradeScore = dialog.findViewById<TextInputEditText>(R.id.grade_score)

            dialog.findViewById<Button>(R.id.add_grade_button).setOnClickListener {
                if(gradeName.text.toString() != ""){
                    if(gradeScore.text.toString() != "" && gradeScore.text.toString().toInt() in 5..10){
                        (gradeList.adapter as GradeListAdapter).addGrade(Grade(gradeName.text.toString(), gradeScore.text.toString().toInt()))
                        findViewById<TextView>(R.id.grade_avg).text = "Average: ${dmFormat.format(grades.sumOf{it.score} * 1.0f / grades.size)}"
                        dialog.dismiss()
                    }else{
                        Toast.makeText(this, "Please Enter a Valid Grade Between 5 and 10", Toast.LENGTH_LONG).show()
                    }

                }else{
                    Toast.makeText(this, "Please Enter a Valid Name", Toast.LENGTH_LONG).show()
                }
            }

            dialog.show()
        }
    }

}