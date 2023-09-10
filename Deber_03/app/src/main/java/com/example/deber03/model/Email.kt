package com.example.deber03.model

class Email(
    //Atributos
    var user: String,
    var subject: String,
    var preview: String,
    var date: String,
    var stared: Boolean,
    var unread: Boolean,
    var selected: Boolean
) {
    init {
        user
        subject
        preview
        date
        stared
        unread
        selected
    }
}