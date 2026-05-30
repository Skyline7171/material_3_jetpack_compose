package com.example.another_course_jetpack_compose

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.core.graphics.drawable.toDrawable
import coil3.annotation.ExperimentalCoilApi
import coil3.asImage
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.example.another_course_jetpack_compose.components.BottomNavigationBar
import com.example.another_course_jetpack_compose.components.FavoriteScreen
import com.example.another_course_jetpack_compose.components.SettingsScreen
import com.example.another_course_jetpack_compose.ui.theme.Another_course_jetpack_composeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Another_course_jetpack_composeTheme {
                MainScreen()
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainScreenPreview() {
    Another_course_jetpack_composeTheme {
        MainScreen(true)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(preview: Boolean = false) {

    var showDialog by remember { mutableStateOf(false) }
    var selectedItemIndex by remember { mutableIntStateOf(0) }

    if (showDialog) {
        Material3AlertDialog(
            onDismiss = {
                showDialog = false
            }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Filled.Call, contentDescription = null)
            }
        },
        topBar = {
            Material3TopAppBar()
        },
        bottomBar = {
            BottomNavigationBar(
                onItemSelected = { index ->
                    selectedItemIndex = index
                }
            )
        }

    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedItemIndex) {
                0 -> {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        MaterialDogCard(preview)
                        MaterialDogCard(preview)
                    }
                }
                1 -> FavoriteScreen()
                2 -> Text("Notificaciones")
                3 -> SettingsScreen()
            }
        }
    }
}

@Composable
fun Material3AlertDialog(onDismiss: () -> Unit) {
    AlertDialog(

        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),

        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        textContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        iconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,

        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Sí, llama a mi perro")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("No, aún no")
            }
        },
        title = {
            Text("Título: llamar a mi perro")
        },
        text = {
            Text("¿Estás seguro de que quieres llamar a tu perro?")
        },
        icon = {
            Icon(imageVector = Icons.Filled.Call, contentDescription = null)
        }
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MaterialDogCard(preview: Boolean) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        onClick = {
            expanded = !expanded
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
        ) {
            Column {
                if (preview) {
                    val context = androidx.compose.ui.platform.LocalContext.current
                    val previewHandler = AsyncImagePreviewHandler {
                        context.getDrawable(R.drawable.dog1)?.asImage()
                            ?: Color.GRAY.toDrawable().asImage()
                    }

                    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
                        CoilFunctions(expanded)
                    }
                }
                else {
                    CoilFunctions(expanded)
                }
            }
        }
    }
}

@Composable
fun CoilFunctions(expanded: Boolean) {
    val dogURL = "https://plus.unsplash.com/premium_photo-1694819488591-a43907d1c5cc?q=80&w=714&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"

    AsyncImage(
        model = dogURL,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),

        onState = { state ->
            Log.d("COIL_DEBUG", "Estado actual: $state")

            if (state is coil3.compose.AsyncImagePainter.State.Error) {
                Log.e("COIL_DEBUG", "Error detectado", state.result.throwable)
            }
        }
    )

    Text(
        text = "This is my Dog!",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
    )

    val description = "El verdadero rey del sofá y un especialista profesional en siestas por la tarde. Le encanta correr por el parque, sentir el viento en la cara y asegurarse de que nadie se sienta solo. Un compañero leal que convierte cualquier día gris en uno brillante."

    Text(
        text = description,
        fontSize = 15.sp,
        maxLines = if (expanded) 20 else 4,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp)
    )
}

@Composable
fun Material3BottomBar() {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
            }

            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Create, contentDescription = null)
            }

            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.AddCircle, contentDescription = null)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Material3TopAppBar() {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
            }
        },

        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Create, contentDescription = "Crear")
            }

            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Añadir")
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {
            Text(text = "Material 3 App")
        }
    )
}


