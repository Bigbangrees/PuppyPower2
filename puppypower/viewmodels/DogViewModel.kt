package com.example.puppypower.viewmodels

import androidx.lifecycle.*
import com.example.puppypower.database.PuppyDao
import com.example.puppypower.database.PuppyPowerEntity
import com.example.puppypower.network.DogPhoto
import com.example.puppypower.network.PuppyPowerApi
import kotlinx.coroutines.launch

class DogViewModel(private val fileNameDao: PuppyDao) : ViewModel() {
    private val _dogPhoto = MutableLiveData<DogPhoto>()
    val dogPhoto: LiveData<DogPhoto> = _dogPhoto


    init {
        getNewPhoto()
    }

    fun getNewPhoto() {
        viewModelScope.launch {
            _dogPhoto.value = PuppyPowerApi.retrofitService.getRandomPhoto()
        }
    }

    fun addDog(fileNameEntity: PuppyPowerEntity) {
        viewModelScope.launch {
            fileNameDao.addDogImage(fileNameEntity)
        }
    }

    fun getAllDogs(): LiveData<List<PuppyPowerEntity>> {
        return fileNameDao.getAllDogImages().asLiveData()
    }


    class FileNameViewModelFactory(private val fileNameDao: PuppyDao) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DogViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DogViewModel(fileNameDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
















