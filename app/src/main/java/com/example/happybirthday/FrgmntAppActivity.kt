package com.example.happybirthday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FrgmntAppActivity : AppCompatActivity(), DogListFragment.OnDogSelected {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frgmnt_activity_main)
        val actionBar = supportActionBar
        actionBar!!.title = "Fragment Learning"
        actionBar.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().add(R.id.root_layout, DogListFragment.newInstance(), "dogList").commit()

    }

    override fun onDogSelected(dogModel: DogModel) {
        val detailsFragment =
                DogDetailsFragment.newInstance(dogModel)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.root_layout, detailsFragment, "dogDetails")
                .addToBackStack(null)
                .commit()
    }

}