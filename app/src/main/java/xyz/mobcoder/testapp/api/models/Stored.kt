package xyz.mobcoder.testapp.api.models

data class Stored(
    val cid: String,
    val className: Any,
    val `data`: Any,
    val g: Boolean,
    val id: String,
    val size: List<String>
)