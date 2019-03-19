package com.vkarmaedu.vkarma.dataModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class TeacherAttendance(val teacherId : String, val attendancestatus: String)
{
    constructor(): this(teacherId="",attendancestatus="" )
}