package com.ex.mailer


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.formats.MediaView
import com.google.android.gms.ads.formats.UnifiedNativeAdView

class AdsRecyclerAdapter (val items: MutableList<NativeAd>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NativeAdViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.native_ad, parent, false) as UnifiedNativeAdView)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as NativeAdViewHolder).bindProfile(items[position])

    }

    override fun getItemCount(): Int = items.size

    inner class NativeAdViewHolder(val view: UnifiedNativeAdView) : RecyclerView.ViewHolder(view) {

        private val iconView = view.findViewById<AppCompatImageView>(R.id.adIcon)
        private val titleView = view.findViewById<AppCompatTextView>(R.id.adTitle)
        private val ratingView = view.findViewById<AppCompatRatingBar>(R.id.adRating)
        private val adAdvertiser = view.findViewById<AppCompatTextView>(R.id.adAdvertiser)
        private val mediaView = view.findViewById<MediaView>(R.id.adMediaView)
        private val descriptionView = view.findViewById<AppCompatTextView>(R.id.adDescription)
        private val actionBtn = view.findViewById<AppCompatButton>(R.id.actionBtn)
        private val iconCard = view.findViewById<CardView>(R.id.imageCard)

        fun bindProfile(nativeAd: NativeAd) {

            with(nativeAd) {

                // icon view

                if(this.ad.icon?.drawable != null){
                    iconView.setImageDrawable(this.ad.icon.drawable)
                }else{
                    iconCard.visibility = View.GONE
                }
                view.iconView = iconView

                // title view
                titleView.text = this.ad.headline
                view.headlineView = titleView

                if(this.ad.starRating != null){
                    ratingView.rating = this.ad.starRating.toFloat()
                }else{
                    ratingView.visibility = View.GONE
                }
                // rating view
                view.starRatingView = ratingView

                //advertiser view
                adAdvertiser.text = this.ad.advertiser
                view.advertiserView = adAdvertiser

                // media view
                mediaView.setMediaContent(this.ad.mediaContent)
                mediaView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                view.mediaView = mediaView

                // description view
                descriptionView.text = this.ad.body
                view.bodyView = descriptionView



                // call to action
                actionBtn.text = this.ad.callToAction
                view.callToActionView = actionBtn

                view.setNativeAd(nativeAd.ad)
            }
        }
    }


}
