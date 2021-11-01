package xyz.mobcoder.testapp.api.models

data class Data(
    val autoinc: Int,
    val className: String,
    val cover: Int,
    val currentFloor: Int,
    val ground: Ground,
    val height: Int,
    val items: List<ItemX>,
    val s: Int,
    val ss: List<S>,
    val sscounter: Int,
    val v: Int,
    val version: Int,
    val viewOptions: ViewOptions,
    val width: Int,
    val zoom: Zoom
)