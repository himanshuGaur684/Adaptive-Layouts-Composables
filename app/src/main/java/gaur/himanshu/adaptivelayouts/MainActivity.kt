package gaur.himanshu.adaptivelayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import gaur.himanshu.adaptivelayouts.navigation.NavigationContent
import gaur.himanshu.adaptivelayouts.ui.theme.AdaptiveLayoutsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdaptiveLayoutsTheme {
                Surface(modifier = Modifier.safeContentPadding()) {
                    NavigationContent()
                }
            }
        }
    }
}
