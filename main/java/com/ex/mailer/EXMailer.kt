package com.ex.mailer

import android.content.Context
import android.widget.Toast
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EXMailer(private val context: Context, private val mail: Email, private val executor: AppExecutors) {

    init {

        props = System.getProperties()
        props.setProperty("mail.transport.protocol", "smtp")
        props["mail.smtp.host"] = "smtp.gmail.com"
        props["mail.smtp.socketFactory.port"] = "465"
        props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.port"] = "465"

        session = Session.getInstance(props, object : javax.mail.Authenticator() {
            //Authenticating the password
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(Credentials.EMAIL, Credentials.PASSWORD)
            }
        })

        mimeMessage = MimeMessage(session).apply{

            // sender email
            setFrom(InternetAddress(Credentials.EMAIL))

            // email subject
            subject = mail.subject

            // email body
            setText(mail.text)
        }

    }

    fun sendEmail(){

        executor.diskIO().execute {

            mail.receivers?.forEach {

                try{
                    val m = mimeMessage
                            .apply {
                                addRecipient(
                                        Message.RecipientType.TO,
                                        InternetAddress(it))
                            }

                    Transport.send(m)
                    executor.mainThread().execute {
                        //Something that should be executed on main thread.
                        Toast.makeText(context, "sent to $it", Toast.LENGTH_SHORT).show()
                    }

                } catch (e: MessagingException) {
                    e.printStackTrace()
                    executor.mainThread().execute {
                        //Something that should be executed on main thread.
                        Toast.makeText(context, "${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }

    companion object {

        private lateinit var session: Session
        private lateinit var props: Properties
        private lateinit var mimeMessage:  MimeMessage

    }
}