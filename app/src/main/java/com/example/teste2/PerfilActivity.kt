package com.example.teste2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_perfil.*

class PerfilActivity : AppCompatActivity() {

    lateinit var etNome2: TextView

    lateinit var etEmail2: TextView

    lateinit var imgFoto2: ImageView

    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        etNome2 = findViewById(R.id.textoNome2)

        imgFoto2 = findViewById(R.id.imagemredonda)

        etEmail2 = findViewById(R.id.textoEmail2)



        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .requestProfile()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        val acct = GoogleSignIn.getLastSignedInAccount(this)

        if (acct != null) {

            var nomePessoa: String? = acct.displayName
            var imgPesosa: Uri? = acct.photoUrl
            var emailPessoa: String? = acct.email


            etNome2.setText(nomePessoa)
            etEmail2.setText(emailPessoa)
            Glide.with(this).load(imgPesosa).into(imgFoto2)

            ButSair.setOnClickListener {googleSignInClient.signOut()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)}



            }
        }


    }





