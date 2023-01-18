package com.example.themedicalassistant.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.themedicalassistant.Model.Repository
import com.example.themedicalassistant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private var repository = Repository()
    lateinit var username : String
    lateinit var password : String
    private var state =false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {


            btnSignIn.setOnClickListener{
                username = etUsername.text.toString()
                password = etPassword.text.toString()
                if (username.isNotEmpty()&&password.isNotEmpty()) {
                    userExists(username, password)
                }
                else
                    Toast.makeText(
                        this@MainActivity,
                        "Username and password should not empty",
                        Toast.LENGTH_LONG
                    ).show()
            }

            tvCreateAccount.setOnClickListener{
                val intent = Intent(this@MainActivity , Signup::class.java)
                startActivity(intent)
            }
        }

    }
    private fun userExists(username : String, password : String){


            repository.signInUser(username, password).observe(this){
                state ->
                if (state.equals("true")) {
                    val intent = Intent(this@MainActivity, Home::class.java)
                    startActivity(intent)
                    finish()

                }else if (state.equals("false")){
                    Toast.makeText(
                        this@MainActivity,
                        "Username or password not correct",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

    }
}