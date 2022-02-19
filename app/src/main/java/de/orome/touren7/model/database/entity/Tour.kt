package de.orome.touren7.model.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tbl_touren")
data class Tour(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tour_id")
    var id: Int,
//    @ColumnInfo(name = "tour_fb_id")
//    var fb_id: String,
    @ColumnInfo(name = "tour_nummer")
    var tourNummer: String,
    @ColumnInfo(name = "tour_datum")
    var tourDatum:String,
    @ColumnInfo(name = "tour_dauer")
    var tourDauer: String,
    @ColumnInfo(name = "tour_depotzeit_vt")
    var DepotzeitVt: String,
    @ColumnInfo(name = "tour_depotzeit_nt")
    var DapotzeitNt: String,
    @ColumnInfo(name = "tour_fahrer_nummer")
    var FahrerNummer: String,
    @ColumnInfo(name = "tour_fahrzeug_nummer")
    var Fahrzeugnummer: String
):Parcelable
