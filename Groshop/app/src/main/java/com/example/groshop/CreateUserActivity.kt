package com.example.groshop

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.groshop.databinding.ActivityCreateUserBinding
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class CreateUserActivity : AppCompatActivity(), ApiResponse {

    private lateinit var binding: ActivityCreateUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnCreate.setOnClickListener {
            Thread {
                try {
                    createUserRequest()
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }.start()
        }
    }

    private fun createUserRequest() {
        val credential = JSONObject()
        credential.put("name", binding.etName.text)
        credential.put("email", binding.etEmail.text)
        credential.put("password", binding.etPassword.text)

        val url = URL("https://6283714992a6a5e46223ee72.mockapi.io/api/v1/createUser")
        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json")

            Log.d("Resp", responseCode.toString())
            if (responseCode == 201) {
                Log.d("RespC", responseCode.toString())
                outputStream.bufferedWriter().use {
                    it.write(credential.toString())
                }
                val response = inputStream.bufferedReader().use {
                    it.readText()
                }
                Log.d("RespR", "Response $response")
                val jsonObject = JSONObject(response)
                val name = jsonObject.getString("name")
                val email = jsonObject.getString("email")
                val password = jsonObject.getString("password")
                val id = jsonObject.getString("id")

                val message = name + " " + email + " " + password + " " + id
                Toast.makeText(this@CreateUserActivity, message, Toast.LENGTH_SHORT).show()

            } else {
                println(errorStream)
            }
        }
    }

    override fun onSuccessfulResponse(data: UserModel) {

    }

    override fun onError(message: String) {
       
    }
}