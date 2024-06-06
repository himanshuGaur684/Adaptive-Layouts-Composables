package gaur.himanshu.adaptivelayouts.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.window.core.layout.WindowWidthSizeClass
import gaur.himanshu.adaptivelayouts.list_details.ListDetailsScaffold
import gaur.himanshu.adaptivelayouts.supporting_pane.MainSupportingScaffold

@Composable
fun NavigationContent(modifier: Modifier = Modifier) {
    val selected = rememberSaveable { mutableStateOf(AppDest.HOME.name) }

    val windowAdaptiveInfo = currentWindowAdaptiveInfo()
    val layoutType = when (windowAdaptiveInfo.windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.EXPANDED -> {
            NavigationSuiteType.NavigationDrawer
        }

        WindowWidthSizeClass.MEDIUM -> {
            NavigationSuiteType.NavigationRail
        }

        WindowWidthSizeClass.COMPACT -> {
            NavigationSuiteType.NavigationBar
        }

        else -> {
            NavigationSuiteType.NavigationBar
        }
    }

    NavigationSuiteScaffold(
        layoutType = layoutType,
        navigationSuiteItems = {
            AppDest.entries.forEach {
                item(selected = selected.value == it.name,
                    icon = { Icon(imageVector = it.imageVector, contentDescription = null) },
                    label = { Text(text = stringResource(id = it.resId)) }, onClick = {
                        selected.value = it.name
                    })
            }
        })
    {
        when (selected.value) {
            AppDest.HOME.name -> {
                ListDetailsScaffold()
            }

            AppDest.FAVORITE.name -> {
                MainSupportingScaffold()

            }
        }
    }


}