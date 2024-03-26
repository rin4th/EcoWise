package com.example.ecowise.ui.merchant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecowise.ui.merchant.ui.main.PaymetFragment

class PaymetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paymet)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PaymetFragment.newInstance())
                .commitNow()
        }
    }
}