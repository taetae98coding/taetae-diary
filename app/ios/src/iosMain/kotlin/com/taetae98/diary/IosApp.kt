package com.taetae98.diary

import androidx.compose.ui.window.ComposeUIViewController
import com.taetae98.diary.app.App
import platform.UIKit.UIViewController

public fun compose(): UIViewController {
    return ComposeUIViewController {
        App()
    }
}