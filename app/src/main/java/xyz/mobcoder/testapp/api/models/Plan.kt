package xyz.mobcoder.testapp.api.models

data class Plan(
    val copyright: String,
    val error: Int,
    val items: List<Item>,
    val storage: String
)