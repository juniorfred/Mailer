package com.ex.mailer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.VideoOptions
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.gson.Gson

class MailSender : AppCompatActivity() {

   private lateinit var nativeAdLoader: AdLoader

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mail_sender)

        val recyclerview = findViewById<RecyclerView>(R.id.adsRecycler)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val ads: MutableList<NativeAd> = mutableListOf()

        val videoOptions = VideoOptions.Builder()
            .setStartMuted(false)
            .setCustomControlsRequested(true)
            .setClickToExpandRequested(true)
            .build()

        val nativeAdOptions = NativeAdOptions.Builder()
            .setVideoOptions(videoOptions)
            .build()

            nativeAdLoader = AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110")
            .forUnifiedNativeAd {

                ads.add(NativeAd(it))

                if(!nativeAdLoader.isLoading) {

                    recyclerview.adapter = AdsRecyclerAdapter(ads)
                }

                if(isDestroyed){
                    it.destroy()
                    return@forUnifiedNativeAd
                }
            }
            .withAdListener(object: AdListener(){
                override fun onAdFailedToLoad(p0: Int) {}

                override fun onAdImpression() {}
            })
            .withNativeAdOptions(nativeAdOptions)
            .build()

        nativeAdLoader.loadAds(AdRequest.Builder().build(), 2)

        val gson = Gson()

        if(intent.extras != null){

            val email = gson.fromJson(intent.getStringExtra("email"), Email::class.java)

            EXMailer(this, email, AppExecutors()).sendEmail()

        }

    }
}