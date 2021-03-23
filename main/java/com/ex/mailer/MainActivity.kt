package com.ex.mailer

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.ads.AdView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var bottomSheet: CoordinatorLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<CoordinatorLayout>
    private lateinit var dimLayout: LinearLayoutCompat
    private lateinit var activeItem: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when(resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK){

            Configuration.SCREENLAYOUT_SIZE_SMALL -> {
                configureMobile()
            }

            Configuration.SCREENLAYOUT_SIZE_NORMAL -> {
                configureMobile()
            }

            Configuration.SCREENLAYOUT_SIZE_LARGE -> {
                configureTablet()
            }

            Configuration.SCREENLAYOUT_SIZE_XLARGE -> {
                configureTablet()
            }
        }

        // Drawer layout items callback

        findViewById<AppCompatTextView>(R.id.toMyProfile).setOnClickListener {
            toProfile()
        }
        findViewById<AppCompatTextView>(R.id.toCompose).setOnClickListener {
            toCompose()
        }
        findViewById<AppCompatTextView>(R.id.toSentBox).setOnClickListener {
            toSentBox()
        }
        findViewById<AppCompatTextView>(R.id.toDraft).setOnClickListener {
            toDraft()
        }

        val gson = Gson()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.mn, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.rate -> {


            }

            R.id.share -> {

                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Free anonymous emails and email advertisement https://play.google.com/store/apps/details?id=com.ex.qmailer")
                startActivity(Intent.createChooser(shareIntent, "Share link via"))
            }

            R.id.exit -> {

                finishAffinity()
            }

            R.id.send -> {

                processEmail()
            }

        }

        return true
    }

    override fun onBackPressed() {

        if(isMobile){
            when{
                drawerLayout.isDrawerOpen(GravityCompat.START) -> {

                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                else -> {

                }
            }
        }else{
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun processEmail(){

    }

    fun hideBottomSheet(view: View) {

        if(bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

    }

    private fun closeDrawer(){

        if(isMobile) {
            Handler().postDelayed({
                drawerLayout.closeDrawer(GravityCompat.START)
            }, 300)
        }

    }

    private fun storeCallback(code: Int){

    }

    private fun loadStoreItems(): Store {

        return Store(listOf(
                EmailDeals(1, resources.getString(R.string.BUY_TEN_EMAILS), resources.getString(R.string.buy_10), 10f),
                EmailDeals(2, resources.getString(R.string.BUY_FIFTY_EMAILS), resources.getString(R.string.buy_20), 20f),
                EmailDeals(3, resources.getString(R.string.BUY_HUNDRED_EMAILS), resources.getString(R.string.buy_30), 30f),
                EmailDeals(4, resources.getString(R.string.BUY_THOUSAND_EMAILS), resources.getString(R.string.buy_40), 40f)
        ))
    }

    private fun configureMobile(){
        isMobile = true
    }

    private fun configureTablet(){
        isMobile = false
    }

    private fun toProfile(){
        removeActiveItem()
        activeItem = findViewById(R.id.toMyProfile)
        activeItem.setBackgroundResource(R.drawable.menu_item_selected)

    }

    private fun toCompose(){
        removeActiveItem()
        activeItem = findViewById(R.id.toCompose)
        activeItem.setBackgroundResource(R.drawable.menu_item_selected)
    }

    private fun toSentBox(){
        removeActiveItem()
        activeItem = findViewById(R.id.toSentBox)
        activeItem.setBackgroundResource(R.drawable.menu_item_selected)
    }

    private fun toDraft(){
        removeActiveItem()
        activeItem = findViewById(R.id.toDraft)
        activeItem.setBackgroundResource(R.drawable.menu_item_selected)
    }

    private fun removeActiveItem(){

        if(this::activeItem.isInitialized){
            activeItem.setBackgroundResource(R.drawable.ripple)
            closeDrawer()
        }

    }

    companion object {

        private var isMobile = true
    }

}