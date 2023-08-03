
import com.google.gson.annotations.SerializedName

data class OompaLoompaList(
    @SerializedName("current")
    var current: Int,
    @SerializedName("results")
    var results: List<ResultX>,
    @SerializedName("total")
    var total: Int
)