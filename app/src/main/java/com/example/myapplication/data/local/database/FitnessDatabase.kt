package com.example.myapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.dao.TechniqueGuideDao
import com.example.myapplication.data.local.entity.TechniqueGuide

@Database(entities = [TechniqueGuide::class], version = 1)
abstract class FitnessDatabase : RoomDatabase() {
    abstract fun TechniqueGuideDao(): TechniqueGuideDao
}