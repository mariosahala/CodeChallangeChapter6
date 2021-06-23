package com.example.batuguntingkertas.ui.profile.editProfile

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.batuguntingkertas.R

class EditProfileActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var presenter: EditProfilePresenter
        lateinit var etNama: EditText
        lateinit var etEmail: EditText

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        etNama = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)

    }
}