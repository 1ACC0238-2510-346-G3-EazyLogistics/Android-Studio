package pe.edu.upc.logisticmaster.data.model

data class Reserve (
    val id: Int,
    val userId: Int,
    val RoomId: Int,
    val status: String,
    val startTime: String,
    val endTime: String

)