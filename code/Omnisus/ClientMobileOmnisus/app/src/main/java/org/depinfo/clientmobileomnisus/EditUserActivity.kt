package org.depinfo.clientmobileomnisus

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.depinfo.clientmobileomnisus.databinding.ActivityEditUserBinding
import org.depinfo.clientmobileomnisus.http.RetrofitUtil
import org.depinfo.clientmobileomnisus.http.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditUserBinding
    private lateinit var service: Service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUserBinding.inflate(layoutInflater)
        service = RetrofitUtil.get()
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        prepareUI()
        onEditUserClick()
    }

    private fun prepareUI() {
        val publicUsername = intent.getStringExtra("PUBLIC_NAME")
        binding.tvUsername.text = publicUsername
        binding.editTextTextPersonName.setText(publicUsername)
    }

    private fun onEditUserClick() {
        binding.btnEditUser.setOnClickListener {
            val newPublicUsername: String = binding.editTextTextPersonName.text.toString()
            service.editUser(newPublicUsername).enqueue(object : Callback<String?> {
                    override fun onResponse(call: Call<String?>, response: Response<String?>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@EditUserActivity, baseContext.getText(R.string.txt_new_name),
                                Toast.LENGTH_SHORT).show()
                            val gradeActivityIntent = Intent(baseContext, GradeActivity::class.java)
                            startActivity(gradeActivityIntent)
                        } else {
                            Toast.makeText(this@EditUserActivity, baseContext.getText(R.string.error_unexpected),
                                Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<String?>, t: Throwable) {
                        Toast.makeText(this@EditUserActivity, R.string.error_com, Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}