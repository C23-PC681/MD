package com.example.myapplication.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "technique_guide")
data class TechniqueGuide(
    @PrimaryKey(autoGenerate = true)
    val techniqueGuideId: Int,
    val title: String,
    val description: String,
    val urlToImage: String,
) : Parcelable
