package xyz.mobcoder.testapp.sqlite.models


class ListItem {

    var id: Int = 0
    var title: String? = null

    constructor(id: Int, title: String?) {
        this.id = id
        this.title = title
    }

    constructor(title: String?) {
        this.title = title
    }


}