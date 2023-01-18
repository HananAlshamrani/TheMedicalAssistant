package com.example.themedicalassistant.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Repository () {

    private val db = Firebase.firestore
    private var diseasesList = MutableLiveData<List<Diseases>>()
    private var usersList = MutableLiveData<List<Users>>()
    private var resultsList = MutableLiveData<List<Results>>()
    private var adviceList = MutableLiveData<List<Advice>>()
    private var state = MutableLiveData<String>("")


    fun addUser(newUser : Users) {
        db.collection("Users")
            .add(newUser)
            .addOnSuccessListener { documentReference -> Log.d("addUserFun", "DocumentSnapshot added with ID: ${documentReference.id}") }
            .addOnFailureListener { e ->  Log.w("addUserFunFail", "Error adding document", e) }
    }


    fun getDiseases(): MutableLiveData<List<Diseases>> {
        var arrayDiseases = arrayListOf<Diseases>()
        db.collection("Diseases")
            .get()
            .addOnSuccessListener {
                    result ->
            for (document in result){
                var name = document.id
                var symptoms1 = document.get("Symptoms1") as String
                var symptoms2 = document.get("Symptoms2") as String
                var symptoms3 = document.get("Symptoms3") as String
                var symptoms4 = document.get("Symptoms4") as String
                var symptoms5 = document.get("Symptoms5") as String
                var image = document.get("Image") as String
                var listSymptoms = listOf(symptoms1,symptoms2,symptoms3,symptoms4,symptoms5)
                arrayDiseases.add(Diseases(name,listSymptoms,image))
            }
                diseasesList.postValue(arrayDiseases)
            }
            .addOnFailureListener { e ->  Log.w("getDiseasesFunFail", "Error adding document", e) }
        return diseasesList
    }

    fun getUsers(): MutableLiveData<List<Users>> {
        var arrayUsers = arrayListOf<Users>()
        db.collection("Users")
            .get()
            .addOnSuccessListener {
                    result ->
                for (document in result){
                    var username = document.get("username") as String
                    var email = document.get("email") as String
                    arrayUsers.add(Users(username,"",email))
                }
                usersList.postValue(arrayUsers)
            }
            .addOnFailureListener { e ->  Log.w("getUsersFunFail", "Error adding document", e) }
        return usersList
    }

    fun getResults(): MutableLiveData<List<Results>>{
        var arrayResults = arrayListOf<Results>()
        db.collection("Results")
            .get()
            .addOnSuccessListener {
                    result ->
                for (document in result){
                    var id = document.id
                    var action = document.get("Action") as String
                    var answers = document.get("Answers") as Number
                    arrayResults.add(Results(id,answers,action))
                }
                resultsList.postValue(arrayResults)
            }
            .addOnFailureListener { e ->  Log.w("getResultsFunFail", "Error adding document", e) }
        return resultsList
    }

    fun getHealthAdvice(): MutableLiveData<List<Advice>> {
        var arrayAdvice = arrayListOf<Advice>()
        db.collection("HealthAdvice")
            .get()
            .addOnSuccessListener {
                    result ->
                for (document in result){
                    var id = document.id
                    var advice = document.get("Advice") as String
                    arrayAdvice.add(Advice(id,advice))
                }
                adviceList.postValue(arrayAdvice)
            }
            .addOnFailureListener { e ->  Log.w("getHealthAdviceFunFail", "Error adding document", e) }
        return adviceList
    }

    fun signInUser (username : String, password : String): LiveData<String>{
        var email = ""
        var state = MutableLiveData<String>("")
        db.collection("Users").whereEqualTo("username",username)
                .whereEqualTo("password", password)
                .get()
                .addOnSuccessListener {
                        result ->
                    for (document in result){
                        email = document.get("email") as String
                    }
                    if (email.isNotEmpty() && email !="" && email != null)
                        state.postValue("true")
                    else
                        state.postValue("false")
                }
                .addOnFailureListener { e -> Log.w("getResultsFunFail", "Error adding document", e) }

        return state
    }

}