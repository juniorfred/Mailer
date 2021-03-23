package com.ex.mailer


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson

class AnonymousEmail : AppCompatActivity() {

    private lateinit var email: Email

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.anonymous_email)

        val gson = Gson()

        if(intent.extras != null){
            email = gson.fromJson(intent.getStringExtra("email"), Email::class.java)
        }

        val a = findViewById<TextInputEditText>(R.id.emailList)

        findViewById<AppCompatButton>(R.id.sendAnonymousEmail).setOnClickListener {

            val emails = a.text.toString()

            if(TextUtils.isEmpty(emails)){

                CustomDialogs.alert(this, "No reciever(s) found, enter coma seperated list of email addresses", "Got it"){}
            }else{

                if(a.text.toString().contains(',')){
                    email.receivers = a.text.toString().split(',').toMutableList()
                }else{
                    email.receivers = mutableListOf(a.text.toString())
                }

                val intent = Intent(this@AnonymousEmail, MailSender::class.java)
                intent.putExtra("email", gson.toJson(email))
                startActivity(intent)

                finish()
            }

        }
    }
}
