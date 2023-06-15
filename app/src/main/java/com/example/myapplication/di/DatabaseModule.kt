package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.local.dao.TechniqueGuideDao
import com.example.myapplication.data.local.database.FitnessDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideFitnessDatabase(
        @ApplicationContext appContext: Context
    ): FitnessDatabase {
        return Room.databaseBuilder(
            appContext,
            FitnessDatabase::class.java,
            "fitness_database"
        ).createFromAsset("database/fitness_database.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideTechniqueGuideDao(
        fitnessDatabase: FitnessDatabase
    ): TechniqueGuideDao {
        return fitnessDatabase.TechniqueGuideDao()
    }
}