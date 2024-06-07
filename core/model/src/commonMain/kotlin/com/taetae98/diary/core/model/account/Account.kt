package com.taetae98.diary.core.model.account

public sealed class Account {
    public abstract val uid: String?

    public data object Guest : Account() {
        override val uid: String? = null
    }
}
