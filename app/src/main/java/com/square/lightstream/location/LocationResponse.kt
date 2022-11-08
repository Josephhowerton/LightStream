package com.square.lightstream.location

data class LocationResponse(
    val info: Info,
    val results: List<Location>
)