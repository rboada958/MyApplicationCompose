package com.example.myapplication.ui.login

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.navigation.AppScreens
import com.example.myapplication.ui.home.HomeActivity
import com.example.myapplication.ui.login.mvvm.LoginViewModel
import com.example.myapplication.ui.login.mvvm.LoginViewModel.*

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {
    Login(navController, viewModel)
}

@Composable
fun Login(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val email by viewModel.emailLiveData.observeAsState(initial = "")
    val password by viewModel.passwordLiveData.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnableLiveData.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.loadingEnableLiveData.observeAsState(initial = false)
    val success: Boolean by viewModel.successEnableLiveData.observeAsState(initial = false)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            item {
                HeaderImage()
                Spacer(modifier = Modifier.padding(top = 16.dp))
                EmailField(
                    email = email,
                    onTextFieldChange = {
                        viewModel.onLoginChange(it, password)
                    }
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                PasswordField(
                    password = password,
                    onTextFieldChange = {
                        viewModel.onLoginChange(email, it)
                    }
                )
                Spacer(modifier = Modifier.padding(top = 8.dp))
                ForgotPassword(navController)
                Spacer(modifier = Modifier.padding(top = 32.dp))
                LoginButton(value = loginEnable) {
                    viewModel.onLoginSelected(email, password)
                }
            }
        }
        if (isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }

    if (success) {
        val activity = LocalContext.current
        val intent = Intent(activity, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(intent)
    }
}

@Composable
fun LoginButton(value: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF44336),
            disabledContainerColor = Color(0xFF797675),
        ),
        enabled = value,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            color = Color.Black,
            fontSize = 16.sp,
            text = "Login"
        )
    }
}

@Composable
fun ForgotPassword(navController: NavController) {
    Text(
        color = Color(0xFFF44336),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(AppScreens.ForgotPasswordScreen.route)
            },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        text = "Forgot password",
        textAlign = TextAlign.End
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(password: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = { onTextFieldChange(it) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFF44336),
            containerColor = Color(0xFFF0E6E6),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        maxLines = 1,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Password") },
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(email: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextFieldChange(it) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFF44336),
            containerColor = Color(0xFFF0E6E6),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        maxLines = 1,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        singleLine = true,
    )
}

@Composable
fun HeaderImage() {
    Image(
        painter = painterResource(id = R.drawable.img),
        contentDescription = null
    )
}

