package org.depinfo.clientmobileomnisus

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.depinfo.clientmobileomnisus.databinding.ActivitySigninBinding
import org.depinfo.clientmobileomnisus.http.RetrofitUtil
import org.depinfo.clientmobileomnisus.http.Service
import org.depinfo.clientmobileomnisus.http.dto.SigninRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private lateinit var service: Service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        service = RetrofitUtil.get()
        setContentView(binding.root)

//        val actionBar = supportActionBar
//        actionBar!!.hide()

        onSignInClick()
    }

    private fun onSignInClick() {
        binding.btnConnect.setOnClickListener {
            val request: SigninRequest = SigninRequest()
            request.username = binding.etUsername.text.toString()
            request.password = binding.etPassword.text.toString()
            requestSignIn(request)
        }
    }

    private fun requestSignIn(request: SigninRequest) {
        service.signIn(request).enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if (response.isSuccessful) {
                    val gradeIntent = Intent(this@SignInActivity, GradeActivity::class.java)
                    startActivity(gradeIntent)
                } else {
                    Toast.makeText(this@SignInActivity, baseContext.getText(R.string.error_wrong_username_pass),
                        Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Toast.makeText(this@SignInActivity, R.string.error_com, Toast.LENGTH_SHORT).show()
            }
        })
    }
}