package com.example.gleonardo.newssportspaginglibrary.domain.model.exception

class RequestValuesNotImplementedException : RuntimeException() {

    companion object {
        const val ERROR_MESSAGE = "Needs to be implement class"
    }

    override val message: String?
        get() = ERROR_MESSAGE + this.javaClass.name
}