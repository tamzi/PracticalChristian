package com.practicalchristian.app.core.ui.helpers

import androidx.compose.ui.graphics.Color
import com.practicalchristian.app.core.domain.models.ScheduleStatus
import com.sacrament.ui.foundation.color.Neutral500
import com.sacrament.ui.foundation.color.SemanticColors

val ScheduleStatus.color: Color
    get() = when (this) {
        ScheduleStatus.DONE_ON_DATE -> SemanticColors.success
        ScheduleStatus.DONE_OFF_DATE -> SemanticColors.warningLight
        ScheduleStatus.PENDING_ON_DATE -> SemanticColors.successLight
        ScheduleStatus.PENDING_OFF_DATE -> SemanticColors.error
        ScheduleStatus.TODO -> Neutral500
    }
