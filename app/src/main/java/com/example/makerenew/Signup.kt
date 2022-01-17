package com.example.makerenew

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
        val btn_signup = findViewById<Button>(R.id.siginupbutton)
        val btn_smsSent = findViewById<Button>(R.id.btn_SmsSent)
        val btn_verify = findViewById<Button>(R.id.btn_verify)
        var phone = ""
        var passwd = ""
        var otp = ""

        var storedVerificationId = ""
        var resendToken: PhoneAuthProvider.ForceResendingToken






        et_passwd.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(et_passwd.length() > 5){
                        btn_signup.setEnabled(true)
                    }
            }
        }) //pwssword가 6자 이상이어야 회원가입 버튼이 활성화 됨





        fun verifyWithPhoneAuthCredential(credential: PhoneAuthCredential) {
            auth.signInWithCredential(credential)
                .addOnCompleteListener(this@Signup) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                            et_passwd.setEnabled(true)
                        val user = Firebase.auth.currentUser!!

                        user.delete()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d(TAG, "User account deleted.")
                                }
                            }
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                        }
                        // Update UI
                    }
                }
        } //번호 인증을 하고 인증이 되면 firebase에 사용자 등록을 함과 동시에 삭제함

        fun signInWithPhoneAuthCredential() {
            auth.createUserWithEmailAndPassword(
                "+82" + et_phone.text.toString() + "@user.com",
                et_passwd.text.toString()
            )
                .addOnCompleteListener(this@Signup) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser

//                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
//                        updateUI(null)
                    }
                }
        } // 전화번호를 비밀번호와 연동하기 위해서 이메일 형식으로 포멧 변환함


        var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:$credential")
                verifyWithPhoneAuthCredential(credential)
//                btn_signup.setEnabled(true)
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

        btn_smsSent.setOnClickListener() {
            et_phone.setEnabled(false)
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("+82" + et_phone.text.toString()) //국가 번호 +82 를 해줬음
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)

                // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
            btn_verify.setEnabled(true)
        }

        fun verifyPhoneNumberWithCode() {
            var credential =
                PhoneAuthProvider.getCredential(storedVerificationId, et_otp.text.toString())

            verifyWithPhoneAuthCredential(credential)

        }


        btn_verify.setOnClickListener() {
            verifyPhoneNumberWithCode()
        }


        btn_signup.setOnClickListener() {
//            passwd = et_passwd.text.toString()
            signInWithPhoneAuthCredential()

    }

    }
}