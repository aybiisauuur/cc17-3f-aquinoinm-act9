package database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy


@Dao
interface FlightDao {
    // Autocomplete search for airports
    @Query("SELECT * FROM airport WHERE name LIKE :query OR iata_code LIKE :query ORDER BY passengers DESC")
    fun searchAirports(query: String): LiveData<List<Airport>>

    // Get all favorite routes
    @Query("SELECT * FROM favorite")
    fun getFavorites(): LiveData<List<Favorite>>

    // Insert favorite route
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)
}
