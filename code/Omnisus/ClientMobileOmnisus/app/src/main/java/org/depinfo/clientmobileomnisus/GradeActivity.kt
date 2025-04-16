package org.depinfo.clientmobileomnisus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.depinfo.clientmobileomnisus.databinding.ActivityGradeBinding
import org.depinfo.clientmobileomnisus.http.RetrofitUtil
import org.depinfo.clientmobileomnisus.http.Service
import org.depinfo.clientmobileomnisus.http.dto.UserDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GradeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGradeBinding
    private lateinit var service: Service
    private lateinit var gradeAdapter: GradeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGradeBinding.inflate(layoutInflater)
        service = RetrofitUtil.get()
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        initRecycler()
        onEditIntentClick()
        requestGrades()
    }

    private fun onEditIntentClick() {
        binding.btnEditUser.setOnClickListener {
            val editIntent = Intent(this@GradeActivity, EditUserActivity::class.java)
            editIntent.putExtra("PUBLIC_NAME", binding.tvUsername.text.toString())
            startActivity(editIntent)
        }
    }

    private fun requestGrades() {
        service.getGrade().enqueue(object : Callback<UserDetailsResponse?> {
            override fun onResponse(call: Call<UserDetailsResponse?>, response: Response<UserDetailsResponse?>) {
                if (response.isSuccessful) {
                    response.body()?.let { prepareUI(it) }
                } else {
                    Toast.makeText(this@GradeActivity, R.string.error_unexpected, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserDetailsResponse?>, t: Throwable) {
                Toast.makeText(this@GradeActivity, R.string.error_com, Toast.LENGTH_SHORT).show()
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun prepareUI(response: UserDetailsResponse) {
        binding.tvUsername.text = response.publicName
        gradeAdapter.list.addAll(response.grades)
        gradeAdapter.notifyDataSetChanged()
        //        gradeAdapter.notifyItemRangeInserted(0, response.grades.size());
    }

    private fun initRecycler() {
        binding.rvGrades.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.rvGrades.setLayoutManager(layoutManager)
        gradeAdapter = GradeAdapter()
        binding.rvGrades.setAdapter(gradeAdapter)
    }
}