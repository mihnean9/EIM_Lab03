package lab03.eim.systems.cs.pub.phonedialer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import lab03.eim.systems.cs.pub.phonedialer.databinding.ActivityPhoneDialerBinding


class PhoneDialerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhoneDialerBinding
    private lateinit var phoneNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneDialerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        phoneNumber = binding.editTextPhone.text.toString()

    }

    fun addNumber(view: View) {
        phoneNumber += (view as Button).text
        binding.editTextPhone.setText(phoneNumber)
    }

    fun backspace(view: View) {
        if (phoneNumber.isNotEmpty()) {
            phoneNumber = phoneNumber.dropLast(1)
            binding.editTextPhone.setText(phoneNumber)
        }
    }

    fun startCall(view: View) {
        if (ContextCompat.checkSelfPermission(
                this@PhoneDialerActivity,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@PhoneDialerActivity, arrayOf(Manifest.permission.CALL_PHONE),
                1
            )
        } else {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:" + phoneNumber)
            startActivity(intent)
        }
    }
}