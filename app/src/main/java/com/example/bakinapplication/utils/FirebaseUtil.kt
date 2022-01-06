package com.example.bakinapplication.utils

import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast

import com.google.android.gms.tasks.OnFailureListener

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope


object FirebaseUtil {


    fun firebaseDownlode(context: Context, name: String, image: ImageView) {
        val storage = FirebaseStorage.getInstance("gs://mobile-contents-812ea.appspot.com")
        val storageReference = storage.reference
        if (!name.isEmpty()) {
            storageReference.child(name).downloadUrl.addOnSuccessListener { uri ->
                Glide.with(context).load(uri)
                    .into(image)
            }.addOnFailureListener { }
        }
    }
    fun firebaseDownlode(context: Context, name: String, image: ImageButton) {
        val storage = FirebaseStorage.getInstance("gs://mobile-contents-812ea.appspot.com")
        val storageReference = storage.reference
        if (!name.isEmpty()) {
            storageReference.child(name).downloadUrl.addOnSuccessListener { uri ->
                Glide.with(context).load(uri)
                    .into(image)
            }.addOnFailureListener { }
        }
    }

    fun firebaseUpload(context: Context, uri: Uri): String? {
        val storage: FirebaseStorage =
            FirebaseStorage.getInstance("gs://mobile-contents-812ea.appspot.com")
        var imgname = ""
        if (uri != null) {
            val uuid: String = UUID.randomUUID().toString()
            imgname = "image/$uuid"
            val storageReference: StorageReference =
                storage.getReferenceFromUrl("gs://mobile-contents-812ea.appspot.com").child(imgname)
            storageReference.putFile(uri)
                .addOnSuccessListener(OnSuccessListener<Any?> { Log.d(TAG, "onSuccess: 성공") })
                .addOnFailureListener(
                    OnFailureListener {
                        Toast.makeText(context, "업로드에 실패하였습니다", Toast.LENGTH_SHORT).show()
                    })
        }
        return imgname
    }
}