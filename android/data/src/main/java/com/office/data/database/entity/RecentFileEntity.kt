package com.office.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.office.domain.model.RecentFile

/**
 * Room entity for recently opened files (reja.txt 22-bo'lim).
 */
@Entity(tableName = "recent_files")
data class RecentFileEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "uri") val uri: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "mime_type") val mimeType: String,
    @ColumnInfo(name = "last_opened") val lastOpened: Long,
    @ColumnInfo(name = "last_modified") val lastModified: Long,
    @ColumnInfo(name = "thumbnail_path") val thumbnailPath: String?
)

fun RecentFileEntity.toDomain(): RecentFile = RecentFile(
    id = id,
    uri = uri,
    name = name,
    mimeType = mimeType,
    lastOpened = lastOpened,
    lastModified = lastModified,
    thumbnailPath = thumbnailPath
)

fun RecentFile.toEntity(): RecentFileEntity = RecentFileEntity(
    id = id,
    uri = uri,
    name = name,
    mimeType = mimeType,
    lastOpened = lastOpened,
    lastModified = lastModified,
    thumbnailPath = thumbnailPath
)
