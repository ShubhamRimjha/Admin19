package com.covid19.admin19

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_verification.*


class VerificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        if (intent != null) {
            txtName.text = intent.getStringExtra("name")
            txtCity.text = "${intent.getStringExtra("city")}${intent.getStringExtra("state")}"
            txtProvider.text = intent.getStringExtra("provider")
            txtAddress.text = intent.getStringExtra("address")
            txtContact.text = intent.getStringExtra("contact")

        }

        btnCall.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:" + txtContact.text)
            startActivity(callIntent)
        }

        btnVerify.setOnClickListener {
            AlertDialog.Builder(applicationContext).setMessage("You sure this reource is verified?")
                .setNegativeButton(
                    "Cancel"
                ) { _, _ -> }
                .setPositiveButton("Verify") { _, _ ->
                    verifythisResource(intent.getStringExtra("id"))
                }
                .setTitle("Verification Prompt")
                .create().show()

        }
    }

    private fun verifythisResource(id: String?) {

        val db = FirebaseFirestore.getInstance()
        val resoRef = db.collection("ADDED-RESOURCES").document(id.toString())
        resoRef.update("verifiedBY", getSharedPreferences("Name", MODE_PRIVATE))


    }
}