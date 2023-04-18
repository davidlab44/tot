package com.david.dfv.ui










import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.david.dfv.R
import com.david.dfv.ui.theme.TotTheme
//import com.david.navigationdrawer.ui.theme.NavigationDrawerTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
A single activity with a navigation drawer component
 */

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TotTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val scaffoldState = rememberScaffoldState()
                    val coroutineScope = rememberCoroutineScope()
                    val contextForToast = LocalContext.current.applicationContext

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        scaffoldState = scaffoldState,
                        topBar = {
                            MyTopAppBar {
                                coroutineScope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        },
                        drawerContent = {
                            DrawerContent { itemLabel ->
                                Toast
                                    .makeText(contextForToast, itemLabel, Toast.LENGTH_SHORT)
                                    .show()
                                coroutineScope.launch {
                                    // delay for the ripple effect
                                    delay(timeMillis = 250)
                                    scaffoldState.drawerState.close()
                                }
                            }
                        }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "UI \n UI \n UI \n UI \n")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MyTopAppBar(onNavIconClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Some text here") },
        navigationIcon = {
            IconButton(
                onClick = {
                    onNavIconClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Open Navigation Drawer"
                )
            }
        }
    )
}

@Composable
private fun DrawerContent(
    gradientColors: List<Color> = listOf(Color(0xFFF70A74), Color(0xFFF59118)),
    itemClick: (String) -> Unit
) {

    val itemsList = prepareNavigationDrawerItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = gradientColors)),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 36.dp)
    ) {

        item {

            // user's image
            Image(
                modifier = Modifier
                    .size(size = 120.dp)
                    .clip(shape = CircleShape),
                //painter = painterResource(id = R.drawable.people1),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Profile Image"
            )

            // user's name
            Text(
                modifier = Modifier
                    .padding(top = 12.dp),
                text = "DavidLab44",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            // user's email
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 30.dp),
                text = "davidlab44@example.co",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.White
            )
        }

        items(itemsList) { item ->
            NavigationListItem(item = item) {
                itemClick(item.label)
            }
        }
    }
}

@Composable
private fun NavigationListItem(
    item: NavigationDrawerItem,
    unreadBubbleColor: Color = Color(0xFF0FFF93),
    itemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                itemClick()
            }
            .padding(horizontal = 24.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // icon and unread bubble
        Box {

            Icon(
                modifier = Modifier
                    .padding(all = if (item.showUnreadBubble && item.label == "Messages") 5.dp else 2.dp)
                    .size(size = if (item.showUnreadBubble && item.label == "Messages") 24.dp else 28.dp),
                painter = item.image,
                contentDescription = null,
                tint = Color.White
            )

            // unread bubble
            if (item.showUnreadBubble) {
                Box(
                    modifier = Modifier
                        .size(size = 8.dp)
                        .align(alignment = Alignment.TopEnd)
                        .background(color = unreadBubbleColor, shape = CircleShape)
                )
            }
        }

        // label
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = item.label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}

@Composable
private fun prepareNavigationDrawerItems(): List<NavigationDrawerItem> {
    val itemsList = arrayListOf<NavigationDrawerItem>()

    itemsList.add(
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.ic_launcher_background),
            label = "Home"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.ic_launcher_background),
            label = "Messages",
            showUnreadBubble = true
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.ic_launcher_background),
            label = "Notifications",
            showUnreadBubble = true
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.ic_launcher_background),
            label = "Profile"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.ic_launcher_background),
            label = "Payments"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.ic_launcher_background),
            label = "Settings"
        )
    )
    itemsList.add(
        NavigationDrawerItem(
            image = painterResource(id = R.drawable.ic_launcher_background),
            label = "Logout"
        )
    )

    return itemsList
}

data class NavigationDrawerItem(
    val image: Painter,
    val label: String,
    val showUnreadBubble: Boolean = false
)









/*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.david.dfv.ui.theme.TotTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TotTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //val recipeViewModel = viewModel<RecipeViewModel>()
                    //NavigationHost(recipeViewModel)
                    customScaffold({ onBackPressed() })
                    //NavigationDr()
                }
            }
        }
    }
}

*/