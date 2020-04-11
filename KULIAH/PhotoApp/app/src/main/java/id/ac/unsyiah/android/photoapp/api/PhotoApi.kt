package id.ac.unsyiah.android.photoapp.api

import id.ac.unsyiah.android.photoapp.models.PhotoList
import retrofit2.Call
import retrofit2.http.GET

interface PhotoApi {
    @GET("?key=xxxxxxxxxxxxxx&q=keyword&image_type=photo")
    fun getPhotos() : Call<PhotoList>
}