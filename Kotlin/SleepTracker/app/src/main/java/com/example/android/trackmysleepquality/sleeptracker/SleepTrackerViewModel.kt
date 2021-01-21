/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import androidx.lifecycle.*
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.formatNights
import kotlinx.coroutines.*

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var tonight = MutableLiveData<SleepNight?>()
    private val nights = database.getAllNights()

    val btn_start = Transformations.map(tonight) {
        it == null
    }

    val btn_stop = Transformations.map(tonight) {
        it != null
    }

    val btn_clear = Transformations.map(nights){
        it?.isNotEmpty()
    }
    //si nights masih objek makanya diubah ke bentuk yang lebih enak lihat
    val nightsString = Transformations.map(nights) { nights ->
        formatNights(nights, application.resources)
    }

    private val _dataToNavigate = MutableLiveData<SleepNight>()
    val dataToNavigate: LiveData<SleepNight>
        get() = _dataToNavigate

    private val _showSnackBar = MutableLiveData<Boolean>()

    val showSnackbar: LiveData<Boolean>
        get() = _showSnackBar

    init {
        initializeTonight()
    }

    fun doneShowingSnackBar(){
        _showSnackBar.value = false
    }

    private fun initializeTonight() {
        uiScope.launch {
            tonight.value = getTonightFromDb()
        }
    }

    private suspend fun getTonightFromDb(): SleepNight? {
        //withContext act like async (series) and can return value
        return withContext(Dispatchers.IO) {
            //dapatkan malam terakhir yg disimpan
            var night = database.getTonight()
            //kalau tidak ada data default yg tersimpan, artinya ini masih ngambil data lama
            if (night?.endTimeMili != night?.startTimeMili) {
                night = null
            }
            night
        }

    }

    private suspend fun insert(night: SleepNight) {
        withContext(Dispatchers.IO) {
            database.insert(night)
        }
    }

    private suspend fun update(night: SleepNight) {
        withContext(Dispatchers.IO) {
            database.update(night)
        }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    fun onStartTracking() {
        uiScope.launch {
            //kasih objek kosong dgn nilai defaultnya
            val newNight = SleepNight()
            //masukkan sementara si objek ke db
            insert(newNight)
            //karna sudah dimasukkan jadi bisa tentukan
            tonight.value = getTonightFromDb()
        }
    }

    fun onStopTracking() {
        uiScope.launch {
            //keputusan getTonightFromDb(), kalau null, return dari fungsi launch jadi gak perlu akses berikutnya
            val oldNight = tonight.value ?: return@launch
            //kalau gak null, bisa diupdet endtime nya
            oldNight.endTimeMili = System.currentTimeMillis()
            _dataToNavigate.value = oldNight
            update(oldNight)
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            tonight.value = null
            _showSnackBar.value = true
        }
    }

    fun doneNavigating() {
        _dataToNavigate.value = null
    }

}

