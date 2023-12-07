package com.bestswlkh0310.sexyalarimi.server

import com.bestswlkh0310.sexyalarimi.server.dto.AlarmDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @POST("/api/alarm")
    suspend fun addAlarm(
        @Body alarmDto: AlarmDto
    ): String

    @GET("/api/alarm")
    suspend fun allAlarm(): List<AlarmDto>
}