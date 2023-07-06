package com.example.permissionhandler

import android.Manifest
import android.Manifest.permission
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
import java.security.Permission

class MainActivity : AppCompatActivity() {
    private val cameraresultLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            permissions.entries.forEach {
                if (it.value) {
                    if (it.key == Manifest.permission.ACCESS_FINE_LOCATION) {
                        Toast.makeText(this, "granted Location Permission ", Toast.LENGTH_SHORT)
                            .show()

                    } else if (it.key == Manifest.permission.ACCESS_COARSE_LOCATION) {
                        Toast.makeText(
                            this,
                            "GRANTED COARSE Location Permission ",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                    } else {
                        Toast.makeText(this, "granted camera Permission ", Toast.LENGTH_SHORT)
                            .show()

                    }
                } else {
                    if (it.key == Manifest.permission.ACCESS_FINE_LOCATION) {
                        Toast.makeText(this, "Denied FINE Location Permission ", Toast.LENGTH_SHORT)
                            .show()

                    } else if (it.key == Manifest.permission.ACCESS_COARSE_LOCATION) {
                        Toast.makeText(
                            this,
                            "Denied COARSE Location Permission ",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                    } else {
                        Toast.makeText(this, "Denied camera Permission ", Toast.LENGTH_SHORT)
                            .show()

                    }
                }
            }
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
                cameraresultLauncher.launch(
                    arrayOf(
                        Manifest.permission.CAMERA,

                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
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
    private fun customDialogFunction() {
        val customDialog = Dialog(this)
        /*Set the screen content from a layout resource.
    The resource will be inflated, adding all top-level views to the screen.*/
        customDialog.setContentView(R.layout.dialog_custom)
        customDialog.findViewById<TextView>(R.id.tv_submit).setOnClickListener {
            Toast.makeText(applicationContext, "clicked submit", Toast.LENGTH_LONG).show()
            customDialog.dismiss() // Dialog will be dismissed
        }
        customDialog.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {
            Toast.makeText(applicationContext, "clicked cancel", Toast.LENGTH_LONG).show()
            customDialog.dismiss()
        }
        //Start the dialog and display it on screen.
        customDialog.show()
    }

}
