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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import org.depinfo.clientmobileomnisus.api.GradeResponse
import org.depinfo.clientmobileomnisus.api.RetrofitInstance
import org.depinfo.clientmobileomnisus.api.UserDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeScreen(onEditUser: (String) -> Unit) {
    var publicName by remember { mutableStateOf("") }
    var grades by remember { mutableStateOf<List<GradeResponse>>(emptyList()) }
    val context = LocalContext.current
    val primaryColor = Color(0xFFFF9800)

    LaunchedEffect(Unit) {
        RetrofitInstance.api.getGrade().enqueue(object : Callback<UserDetailsResponse?> {
            override fun onResponse(call: Call<UserDetailsResponse?>, response: Response<UserDetailsResponse?>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        publicName = it.publicName ?: ""
                        grades = it.grades
                    }
                } else {
                    Toast.makeText(context, R.string.error_unexpected, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserDetailsResponse?>, t: Throwable) {
                Toast.makeText(context, R.string.error_com, Toast.LENGTH_SHORT).show()
            }
        })
    }

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
                Text(text = publicName, fontSize = 32.sp)
            }

            Spacer(modifier = Modifier.height(64.dp))

            Text(
                text = "Notes : ",
                fontSize = 64.sp,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 32.dp)
            ) {
                items(grades) { grade ->
                    GradeItem(grade)
                }
            }

            Button(
                onClick = { onEditUser(publicName) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 32.dp)
            ) {
                Text("Modifier l'utilisateur")
            }
        }
    }
}

@Composable
fun GradeItem(grade: GradeResponse) {
    Row {
        Text(text = "- ${grade.name} : ", fontSize = 32.sp)
        Text(text = "${grade.grade} %", fontSize = 32.sp)
    }
}
