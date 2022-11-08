package com.square.lightstream.util

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import com.square.lightstream.character.Location
import com.square.lightstream.character.Origin
import java.lang.reflect.Type


class RoomTypeConverter {
    @TypeConverter
    fun listFromString(value: String): List<String> {
        val list: Type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, list)
    }

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun originFromString(value: String): Origin {
        val origin: Type = object : TypeToken<Origin>() {}.type
        return Gson().fromJson(value, origin)
    }

    @TypeConverter
    fun fromOrigin(origin: Origin): String {
        val gson = Gson()
        return gson.toJson(origin)
    }

    @TypeConverter
    fun characterLocationFromString(value: String): Location {
        val location: Type = object : TypeToken<Location>() {}.type
        return Gson().fromJson(value, location)
    }

    @TypeConverter
    fun fromCharacterLocation(location: Location): String {
        val gson = Gson()
        return gson.toJson(location)
    }
}