package com.example.batuguntingkertas.ui.profile.editProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.batuguntingkertas.R
import com.example.batuguntingkertas.ui.menu.MenuActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EditProfile : AppCompatActivity(), EditProfileNavigator {
    lateinit var presenter: EditProfilePresenter
    lateinit var etNama: EditText
    lateinit var etEmail: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        etNama = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        presenter = EditProfilePresenter(this, this)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val fabClose = findViewById<FloatingActionButton>(R.id.fabClose)
        val nama = presenter.ambilNama().toString()
        val email = presenter.ambilEmail().toString()
        etNama.setText(nama)
        etEmail.setText(email)

        btnSave.setOnClickListener {
            if (nama.isEmpty() && email.isEmpty()) {
                editGagal("Gagal disimpan")
            } else {
                presenter.editProfile(nama, email, "", "", "")
                presenter.editSharedpref(nama)
                editBerhasil("Berhasil Disimpan")
            }
        }

        fabClose.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }

    override fun editBerhasil(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT)
    }

    override fun editGagal(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT)
    }
}

