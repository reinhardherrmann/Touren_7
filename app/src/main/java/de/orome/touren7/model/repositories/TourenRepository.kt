package de.orome.touren7.model.repositories

import de.orome.touren7.model.database.dao.TourDao
import de.orome.touren7.model.database.entity.Tour

class TourenRepository(private val tourDao: TourDao) {
    val allLiveTouren = tourDao.getAllTouren()

    suspend fun insertTour(tour: Tour): Long{
        // returns new RowID
        return tourDao.insertNewTour(tour)
    }

    suspend fun updateTour(tour: Tour): Int{
        // Returns Number of updatete Touren
        return tourDao.updateTour(tour)
    }

    suspend fun deleteTour(tour: Tour): Int {
        // returns number of deleted Touren
        return tourDao.deleteTour(tour)
    }

    suspend fun deleteAllTouren(): Int{
        // returns number of deleted Touren
        return tourDao.deleteAllTouren()
    }
}