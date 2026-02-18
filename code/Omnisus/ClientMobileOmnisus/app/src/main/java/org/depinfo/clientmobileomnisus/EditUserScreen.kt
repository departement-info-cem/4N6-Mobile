package org.depinfo.clientmobileomnisus

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.depinfo.clientmobileomnisus.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserScreen(currentPublicName: String, onSaveSuccess: () -> Unit) {
    var newPublicName by remember { mutableStateOf(currentPublicName) }
    val context = LocalContext.current
    val primaryColor = Color(0xFFFF9800)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryColor,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(modifier = Modifier.padding(start = 16.dp, top = 32.dp)) {
                Text(text = "Bienvenue, ", fontSize = 32.sp)
                Text(text = currentPublicName, fontSize = 32.sp)
            }

            Spacer(modifier = Modifier.height(128.dp))

            OutlinedTextField(
                value = newPublicName,
                onValueChange = { newPublicName = it },
                label = { Text("Nom publique") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                singleLine = true
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    RetrofitInstance.api.editUser(newPublicName).enqueue(object : Callback<String?> {
                        override fun onResponse(call: Call<String?>, response: Response<String?>) {
                            if (response.isSuccessful) {
                                Toast.makeText(context, context.getText(R.string.txt_new_name), Toast.LENGTH_SHORT).show()
                                onSaveSuccess()
                            } else {
                                Toast.makeText(context, context.getText(R.string.error_unexpected), Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<String?>, t: Throwable) {
                            Toast.makeText(context, R.string.error_com, Toast.LENGTH_SHORT).show()
                        }
                    })
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 32.dp)
            ) {
                Text("Sauvegarder les modifications")
            }
        }
    }
}
