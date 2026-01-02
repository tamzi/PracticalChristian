package com.practicalchristian.app.core.localdatasource.sources.notes

import com.practicalchristian.app.core.localdatasource.entity.NoteCache
import com.practicalchristian.app.core.localdatasource.sources.base.BaseSource
import kotlinx.coroutines.flow.Flow

interface NotesSource : BaseSource<NoteCache, String, String> {

    val notes: Flow<List<NoteCache>>
}
