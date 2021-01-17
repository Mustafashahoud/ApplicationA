package com.android.mustafa.applicationA.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mustafa.applicationA.core.db.BllocDao
import com.android.mustafa.applicationA.feature.model.NotificationEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * The aim of this viewModel to do the db operations on IO Thread
 */
class NotificationViewModel @Inject constructor(private val dao: BllocDao) : ViewModel() {


    fun insert(notification: NotificationEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dao.insert(notification)
            }
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dao.deleteBy(id)
            }
        }
    }


    fun deleteAll() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dao.deleteAll()
            }
        }
    }


}