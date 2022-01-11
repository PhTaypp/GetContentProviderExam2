package com.example.getcontentproviderexam2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.getcontentproviderexam2.R
import com.example.getcontentproviderexam2.fragment.ShowProviderContentFragment

class MainActivity : AppCompatActivity() {
    lateinit var fragment : Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment = ShowProviderContentFragment()
        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        fragment.let { ft.replace(R.id.content_list, it) }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commit()
//        <provider
//        android:authorities="com.example.user"
//        android:name="com.example.getcontentproviderexam2.provider.ContentProviderP"
//        android:enabled="true"
//        android:exported="true" />
    }

}