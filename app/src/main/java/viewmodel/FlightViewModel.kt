import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import database.Airport
import database.Favorite
import database.FlightDatabase
import kotlinx.coroutines.launch

class FlightViewModel(application: Application) : AndroidViewModel(application) {
    private val flightDao = FlightDatabase.getDatabase(application).flightDao()
    val favorites: LiveData<List<Favorite>> = flightDao.getFavorites()

    fun searchAirports(query: String): LiveData<List<Airport>> {
        return flightDao.searchAirports("%$query%")
    }

    fun addFavorite(favorite: Favorite) {
        viewModelScope.launch {
            flightDao.insertFavorite(favorite)
        }
    }
}

