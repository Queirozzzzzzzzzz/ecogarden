import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.EnvironmentPlant

class EnvironmentPlantsAdapter(private val environmentPlants: List<EnvironmentPlant>) :
    RecyclerView.Adapter<EnvironmentPlantsAdapter.EnvironmentPlantViewHolder>() {

    class EnvironmentPlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val plantNameTextView: TextView = itemView.findViewById(R.id.plant_name)
        val lightIntensityTextView: TextView = itemView.findViewById(R.id.plant_light_intensity)
        val soilHumidityTextView: TextView = itemView.findViewById(R.id.plant_soil_humidity)
        val phTextView: TextView = itemView.findViewById(R.id.plant_ph)
        val airTemperatureTextView: TextView = itemView.findViewById(R.id.plant_air_temperature)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnvironmentPlantViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.environment_plant_item, parent, false)
        return EnvironmentPlantViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EnvironmentPlantViewHolder, position: Int) {
        val environmentPlant = environmentPlants[position]
        holder.plantNameTextView.text = environmentPlant.name
        holder.lightIntensityTextView.text = environmentPlant.light_intensity
        holder.soilHumidityTextView.text = environmentPlant.soil_humidity
        holder.phTextView.text = environmentPlant.ph
        holder.airTemperatureTextView.text = environmentPlant.air_temperature
    }

    override fun getItemCount(): Int = environmentPlants.size
}