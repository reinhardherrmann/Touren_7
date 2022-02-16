package de.orome.touren7.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.orome.touren7.model.database.dao.TourDao
import de.orome.touren7.model.database.entity.Tour

@Database(entities = [Tour::class], version = 1, exportSchema = false)
abstract class TourenDb: RoomDatabase() {
    abstract val tourDao:  TourDao
    companion object{
        @Volatile
        private var INSTANCE : TourenDb? = null
        fun getInstance(context: Context):TourenDb{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TourenDb::class.java,
                        "touren.db"
                    ).build()
                }
                return instance
            }
        }

    }
}