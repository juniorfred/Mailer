package com.ex.mailer

data class Email(val subject: String?, val text: String?, val attachment: String?, var receivers: MutableList<String>?)