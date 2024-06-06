package gaur.himanshu.adaptivelayouts.supporting_pane

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainSupportingScaffold(modifier: Modifier = Modifier) {
    val navigator = rememberSupportingPaneScaffoldNavigator<TravelImage>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    SupportingPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        mainPane = {
            MainPane {
                navigator.navigateTo(ThreePaneScaffoldRole.Secondary, it)
            }
        },
        supportingPane = {
            navigator.currentDestination?.content?.let {
                SupportinPane(travelImage = it) {
                    navigator.navigateTo(ThreePaneScaffoldRole.Tertiary, it)
                }
            }

        },
        extraPane = {
            navigator.currentDestination?.content?.let {
                ExtraPane(travelImage = it)
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPane(modifier: Modifier = Modifier, onClick: (TravelImage) -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text(text = "My Trips Images") }) }) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            modifier = Modifier.padding(it)
        ) {
            items(travelImages) {
                Image(
                    painter = painterResource(id = it.res), contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 1.dp)
                        .clickable { onClick.invoke(it) }
                )
            }
        }
    }
}

@Composable
fun SupportinPane(
    modifier: Modifier = Modifier,
    travelImage: TravelImage,
    onClick: (TravelImage) -> Unit
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onClick.invoke(travelImage) }) {
            Icon(imageVector = Icons.Default.Info, contentDescription = null)
        }
    }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = travelImage.res), contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExtraPane(modifier: Modifier = Modifier, travelImage: TravelImage) {
    Scaffold(topBar = { TopAppBar(title = { Text(text = "Sri Lanka trip") }) }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Image(
                painter = painterResource(id = travelImage.res), contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = travelImage.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = travelImage.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

        }
    }

}

