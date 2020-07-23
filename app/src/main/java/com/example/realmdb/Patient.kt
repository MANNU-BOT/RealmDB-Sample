package com.example.realmdb

import io.realm.RealmObject

open class Patient(
    var comment: String?= null,
    var severity: Int?= null
) : RealmObject(){}