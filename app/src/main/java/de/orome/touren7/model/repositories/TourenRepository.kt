package de.orome.touren7.model.repositories

import de.orome.touren7.model.database.dao.TourDao
import de.orome.touren7.model.database.entity.Tour

class TourenRepository(private val tourDao: TourDao) {
    val allLiveTouren = tourDao.getAllLiveTouren()
    //val singleLiveTour = tourDao.getSingleLiveTour()

    suspend fun insertTour(tour: Tour): Long{
        // returns new RowID
        return tourDao.insertNewTour(tour)
    }

    suspend fun updateTour(tour: Tour): Int{
        // Returns Number of updated Touren
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

    suspend fun getSingleTour(tourNummer: String): Tour {
        return tourDao.getSingleTour(tourNummer)
    }
}