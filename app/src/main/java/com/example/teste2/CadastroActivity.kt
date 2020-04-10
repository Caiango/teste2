package com.example.teste2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.google.firebase.database.*


import kotlinx.android.synthetic.main.activity_cadastro.*

lateinit var etTelefone: EditText

lateinit var btSalvar: Button

lateinit var ratingbar: RatingBar

lateinit var ref: DatabaseReference

lateinit var usersList: ArrayList<users>


class CadastroActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        etTelefone = findViewById(R.id.textFone)
        btSalvar = findViewById(R.id.buttonSalvar)
        ratingbar = findViewById(R.id.ratingBar)

        usersList = arrayListOf()

        ref = FirebaseDatabase.getInstance().getReference("Usuários")


        btSalvar.setOnClickListener {
            var telefone: String = textFone.text.toString().trim()
            //O RATING É UM DADO EM FLOATING
            var rate = ratingBar.rating.toInt()

            val UID = ref.push().key.toString()
            // OBJETO COM OS DADOS DA CLASSE DATA
            val USU = users(UID, telefone, rate)
            //INSERT FIREBASE
            ref.child(UID).setValue(USU).addOnCompleteListener {
                Toast.makeText(this, "DEU CERTO", Toast.LENGTH_LONG).show()
            }




        }
    }

    fun readData(){
        ref.addValueEventListener(object : ValueEventListener {
            //MÉTODO PARA LER DADOS DO FIREBASE
            override fun onDataChange(p0: DataSnapshot) {
                usersList.clear()

            }


            override fun onCancelled(p0: DatabaseError) {

            }

        })
    }
}





