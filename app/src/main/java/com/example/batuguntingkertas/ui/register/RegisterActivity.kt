package com.example.batuguntingkertas.ui.register

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.batuguntingkertas.R
import com.example.batuguntingkertas.ui.login.LoginActivity
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class RegisterActivity : AppCompatActivity(), RegisterNavigator {
    private lateinit var ivKembali: ImageView
    private lateinit var etUserNameRegis: EditText
    private lateinit var etEmailRegis: EditText
    private lateinit var etPassRegis: EditText
    private lateinit var etRePassRegis: EditText
    private lateinit var ivFotoProfil: ImageView
    private lateinit var btnUploadFoto: Button
    private lateinit var btnRegister: Button
    private lateinit var ivJudul: ImageView
    var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        ivKembali = findViewById(R.id.ivKembali)
        ivFotoProfil = findViewById(R.id.ivFotoProfil)
        etUserNameRegis = findViewById(R.id.etUserNameRegis)
        etEmailRegis = findViewById(R.id.etEmailRegis)
        etPassRegis = findViewById(R.id.etPassRegis)
        etRePassRegis = findViewById(R.id.etRePassRegis)
        btnUploadFoto = findViewById(R.id.btnUploadFoto)
        btnRegister = findViewById(R.id.btnRegister)
        ivJudul = findViewById(R.id.ivJudulRegis)
        val presenter = RegisterPresenter(this, this)

        Glide.with(this).load("https://i.ibb.co/HC5ZPgD/splash-screen1.png").into(ivJudul)

        ivKembali.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        etEmailRegis.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(etEmailRegis.text.toString())
                        .matches()
                ) btnRegister.isEnabled = true
                else {
                    btnRegister.isEnabled = false
                    etEmailRegis.error = "Isi Email dengan benar (co:admin@gmail.com)"
                }
            }
        })

        btnRegister.setOnClickListener {
            when {
                etUserNameRegis.text.toString().isEmpty() -> {
                    etUserNameRegis.error = "Username Belum Diisi"
                }
                etEmailRegis.text.toString().isEmpty() -> {
                    etEmailRegis.error = "Email Belum Diisi"
                }
                etPassRegis.text.toString().isEmpty() -> {
                    etPassRegis.error = "Password Belum Diisi"
                }
                etPassRegis.text.toString() != etRePassRegis.text.toString() ->{
                    etRePassRegis.error = "Password tidak sama"
                }
                else -> {
                    val username = etUserNameRegis.text.toString()
                    val email = etEmailRegis.text.toString()
                    val password = etPassRegis.text.toString()
                    val image = imageUri?.path.toString()
                    presenter.register(username,email,password,image)
                }
            }
        }

        btnUploadFoto.setOnClickListener {
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode === RESULT_OK) {
                val resultUri = result.uri
                val path = result.uri.path
                Log.d("PATH", path.toString())
                imageUri = resultUri
                ivFotoProfil.setImageURI(resultUri)
            } else if (resultCode === CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                result.error
            }
        }
    }

    override fun errorRegistrasi() {
        Toast.makeText(this, "Gagal Coba lagi", Toast.LENGTH_SHORT).show()
    }

    override fun succesRegistrasi() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}