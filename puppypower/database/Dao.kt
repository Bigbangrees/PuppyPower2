package com.example.puppypower.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Flow as UtilConcurrentFlow


@Dao
interface PuppyDao {

    @Query("SELECT * FROM PuppyPics")
    fun getAllDogImages(): Flow<List<PuppyPowerEntity>>

    @Query("SELECT * FROM PuppyPics ORDER BY Id DESC LIMIT 1")
    fun getMostRecentlyAddDog() : PuppyPowerEntity

    @Query("DELETE from PuppyPics where id=(select max(id)-1 from PuppyPics)")
    suspend fun deleteDog()

    @Insert
    suspend fun addDogImage(fileNameEntity: PuppyPowerEntity)

}






