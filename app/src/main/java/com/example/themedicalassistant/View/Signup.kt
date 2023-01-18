package com.example.themedicalassistant.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.themedicalassistant.Model.Repository
import com.example.themedicalassistant.Model.Users
import com.example.themedicalassistant.databinding.ActivitySignupBinding

class Signup : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    private lateinit var username : String
    private lateinit var password : String
    private lateinit var email : String
    private lateinit var confirmPassword : String
    private val repository = Repository()
    private var usersList = listOf<Users>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnSignup.setOnClickListener(){

                username = etUsername.text.toString()
                email = etEmail.text.toString()
                password = etPassword.text.toString()
                confirmPassword = etConfirmPassword.text.toString()

                if (username.isNotEmpty()&&email.isNotEmpty()&&confirmPassword.isNotEmpty()&&password.isNotEmpty()){
                        if(password == confirmPassword) {
                            if (password.length<8)
                                Toast.makeText(this@Signup, "The password must more than 7", Toast.LENGTH_LONG).show()
                            else if (email.contains("@"))

                                checkUsernameAndEmail(username,email)

                            else { Toast.makeText(this@Signup, "Please enter correct email", Toast.LENGTH_LONG).show() }
                        }
                        else
                        { Toast.makeText(this@Signup, "Please enter the same password", Toast.LENGTH_LONG).show() }
                }
                else { Toast.makeText(this@Signup, "Please fill all filed", Toast.LENGTH_LONG).show() }

            }
        }

    }


    private fun signUp() {
        repository.addUser(Users(username,password,email))
        Toast.makeText(this@Signup, "You create account correctly, you can sign in now", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@Signup, MainActivity::class.java)
        startActivity(intent)

    }
    private fun checkUsernameAndEmail(username :String, email: String){
//        usersList.clear()
        repository.getUsers().observe(this){
            usersDB -> usersList = usersDB
        }


        var usernames : ArrayList<String> = arrayListOf()
        var emails : ArrayList<String> = arrayListOf()

        for (user in usersList) {
            usernames.add(user.username)
            emails.add(user.email)
        }
        if (usernames.contains(username)||emails.contains(email)) {
            Toast.makeText(this@Signup, "The username or email is used before, try again", Toast.LENGTH_SHORT).show()
        } else{
            signUp()
        }
    }

}
