package com.projemanag.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.projemanag.activities.MainActivity
import com.projemanag.activities.SignUpActivity
import com.projemanag.model.User

class firestore {
    private val mfirestore=FirebaseFirestore.getInstance()
    fun registerUser(activity:SignUpActivity, userInfo: User)
    {
        //creating collection
               mfirestore.collection(Constants.User).document(getCurrentUserId()).set(userInfo,
                   SetOptions.merge()).addOnSuccessListener {
                       activity.userRegisterSucess()
               }

    }
    fun getCurrentUserId():String{
        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}