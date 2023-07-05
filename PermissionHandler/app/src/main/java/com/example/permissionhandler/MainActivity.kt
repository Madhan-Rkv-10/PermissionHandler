package com.example.permissionhandler

import android.Manifest
//import android.hardware.SensorPrivacyManager.Sensors.CAMERA
//import android.Manifest.permission.CAMERA
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private val cameraresultLauncher: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) Toast.makeText(this, "granted camera Permission ", Toast.LENGTH_SHORT).show()
        else Toast.makeText(this, "Denied camera Permission ", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var cameraPermissionbutton = findViewById<Button>(R.id.camera_permission)
        cameraPermissionbutton.setOnClickListener {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(
                    Manifest.permission.CAMERA
                )
            ) {
                showRationalDialog(
                    "Premission for Camera Access",
                    "Camera cananot be used because Camera Access is Denied"
                )
            } else {
                cameraresultLauncher.launch((Manifest.permission.CAMERA))
            }
        }

    }

    private fun showRationalDialog(title: String, message: String) {


        var builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()

    }

}