package com.example.makerenew

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class Signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        this.supportActionBar?.hide()

        auth = Firebase.auth
        auth.setLanguageCode("kr")

        val et_phone = findViewById<EditText>(R.id.signupPhoneNumber)
        val et_passwd = findViewById<EditText>(R.id.singupPassword)
        val et_otp = findViewById<EditText>(R.id.singupOTP)
        val btn_signup = findViewById<Button>(R.id.btn_signup)
        val btn_smsSent = findViewById<Button>(R.id.btn_SmsSent)
        val btn_verify = findViewById<Button>(R.id.btn_verify)
        var phone = ""
        var passwd = ""
        var otp = ""

        var storedVerificationId = ""
        var resendToken : PhoneAuthProvider.ForceResendingToken

        fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
            auth.signInWithCredential(credential)
                .addOnCompleteListener(this@Signup) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")

                        val user = task.result?.user
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                        }
                        // Update UI
                    }
                }
        }
        var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {



            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }

                // Show a message and update the UI
            }


            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token





            }

//                    val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, et_otp.text.toString())






        }

        btn_smsSent.setOnClickListener(){
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("+82"+et_phone.text.toString()) //국가 번호 +82 를 해줬음
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)

                            // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)

        }

        fun verifyPhoneNumberWithCode(){
            var credential = PhoneAuthProvider.getCredential(storedVerificationId, et_otp.text.toString())
            signInWithPhoneAuthCredential(credential)
        }

        btn_verify.setOnClickListener(){
            verifyPhoneNumberWithCode()
        }




    }
}