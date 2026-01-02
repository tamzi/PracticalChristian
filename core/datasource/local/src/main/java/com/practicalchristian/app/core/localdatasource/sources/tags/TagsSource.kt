package com.practicalchristian.app.core.localdatasource.sources.tags

import com.practicalchristian.app.core.localdatasource.entity.TagCache
import com.practicalchristian.app.core.localdatasource.sources.base.BaseSource
import kotlinx.coroutines.flow.Flow

interface TagsSource : BaseSource<TagCache, Int, String> {

    val tags: Flow<List<TagCache>>
}
