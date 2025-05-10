package com.psydrite.vrid_blog_app.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(dateString: String): String {
    val zonedDateTime = ZonedDateTime.parse(dateString + "Z")  // Z for GMT
    val formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy")

    val gmtDate = zonedDateTime.withZoneSameInstant(ZoneId.of("GMT"))

    return gmtDate.format(formatter) + " GMT"   // inform the timezone
}
