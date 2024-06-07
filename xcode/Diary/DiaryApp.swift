import SwiftUI
import iosApp

@main
struct DiaryApp: App {
    init() {
        KoinKt.doInit()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView().ignoresSafeArea()
        }
    }
}
