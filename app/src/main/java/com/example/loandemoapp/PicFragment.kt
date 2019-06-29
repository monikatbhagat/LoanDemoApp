package com.example.loandemoapp

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Camera
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CameraMetadata
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.*
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_pic.*
import kotlinx.android.synthetic.main.fragment_pic.view.*
import java.io.FileNotFoundException
import java.io.IOException
import java.io.OutputStream

class PicFragment:Fragment() {

    private val CAMERA_REQUEST_CODE = 12345
    private val REQUEST_GALLERY_CAMERA = 54654
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_pic, container, false)
        val name = arguments!!.getString("Name")
        val middleName = arguments!!.getString("MiddleName")
        val lastName = arguments!!.getString("LastName")
        val dob = arguments!!.getString("Dob")
        val gender = arguments!!.getString("Gender")
        val location = arguments!!.getString("Location")


        Log.d("inPicFragment", "HHHHHHHHiiii  " + name + middleName)
        view.btnSubmit.setOnClickListener()
        {
            val bundlePromocode = Bundle()
            bundlePromocode.putString("Name", name)
            bundlePromocode.putString("MiddleName", middleName)
            bundlePromocode.putString("LastName", lastName)
            bundlePromocode.putString("Dob", dob)
            bundlePromocode.putString("Gender", gender)
            bundlePromocode.putString("Location", location)

            val fragment = PromoCodeFragment()
            val fragmentManager = requireFragmentManager()
            fragment.arguments = bundlePromocode
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction!!.replace(R.id.frameLayout, fragment)
            fragmentTransaction!!.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            fragmentTransaction!!.commit()

        }
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btnselfie.setOnClickListener()
        {
            if (Build.VERSION.SDK_INT >= 23) {
                if (ContextCompat.checkSelfPermission(
                        this!!.requireContext(),
                        android.Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED || context?.let { it1 ->
                        ContextCompat.checkSelfPermission(
                            it1,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                    } != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this!!.requireContext() as Activity,
                        arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_GALLERY_CAMERA
                    )
                } else {
                    openCamera()
                }
            } else {
                openCamera()
            }
        }
    }

    //packageManager
    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(context!!.packageManager) != null)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_GALLERY_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                Toast.makeText(context, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {

                    val extras = data?.getExtras()
                    val imageBitmap = extras?.get("data") as Bitmap
                    ivSelfie.setImageBitmap(imageBitmap)

                }
            }
        }

    }
}
