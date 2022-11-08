package com.square.lightstream.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.square.lightstream.character.RMCharacter
import com.square.lightstream.location.Location
import com.square.lightstream.util.RoomTypeConverter

@Database(entities = [RMCharacter::class, Location::class], exportSchema = false, version = 1)
@TypeConverters(RoomTypeConverter::class)
abstract class LocalCache: RoomDatabase() {
    abstract fun RMDao(): RMDao
}