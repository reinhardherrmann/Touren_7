package de.orome.touren7.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import de.orome.touren7.model.database.entity.Tour

@Dao
interface TourDao {

    @Insert
    suspend fun insertNewTour(tour:Tour): Long

    @Update
    suspend fun updateTour(tour: Tour): Int

    @Delete
    suspend fun deleteTour(tour: Tour): Int

    @Query("DELETE FROM tbl_touren")
    suspend fun deleteAllTouren(): Int

    @Query("SELECT * FROM tbl_touren ORDER BY tour_datum DESC")
    fun getAllLiveTouren(): LiveData<List<Tour>>

    @Query("SELECT * FROM tbl_touren WHERE tour_fahrer_nummer like :tourNummer")
    suspend fun getSingleTour(tourNummer: String): Tour
}