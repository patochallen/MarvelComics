package com.patofch.marvelcomics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.patofch.marvelcomics.domain.model.Character
import com.patofch.marvelcomics.presentation.character.CharacterViewModel
import com.patofch.marvelcomics.ui.theme.MarvelComicsTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<CharacterViewModel>()
            viewModel.getCharacters()
            val state = viewModel.state.value
            MarvelComicsTheme {
                StateHandler(state) {
                    viewModel.insertCharacters()
                }
            }
        }
    }
}

@Composable
private fun StateHandler(state: CharacterViewModel.CharacterState, add: () -> Unit = {}) {
    // A surface container using the 'background' color from the theme
    var showFab by remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            floatingActionButton = {
                if (showFab) {
                    FloatingActionButton(onClick = add) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) {
            when (state) {
                is CharacterViewModel.CharacterState.Error -> {}
                CharacterViewModel.CharacterState.Loading -> Loader()
                is CharacterViewModel.CharacterState.Success -> {
                    val characters = state.characters
                    showFab = characters.isEmpty()
                    if (characters.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "No hay resultados")
                        }
                    } else {
                        CharacterList(characters)
                    }
                }
            }
        }
    }
}

@Composable
private fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp),
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
private fun CharacterList(items: List<Character>) {
    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            val rows = items.withIndex()
                .groupBy { it.index / 3 }
                .map { it.value.map { it.value } }
            items(rows) { row->
                Spacer(modifier = Modifier.height(20.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    items(row){
                        Image(
                            painter = rememberImagePainter(
                                data = it.imageUrl,
                                builder = {
                                    transformations(CircleCropTransformation())
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }
//                Spacer(modifier = Modifier.height(5.dp))
//                Card(
//                    modifier = Modifier.padding(horizontal = 5.dp),
//                    shape = RoundedCornerShape(15.dp)
//                ) {
//                    Row(
//                        modifier = Modifier
//                            .padding(10.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Image(
//                            painter = rememberImagePainter(
//                                data = it.imageUrl,
//                                builder = {
//                                    transformations(CircleCropTransformation())
//                                }
//                            ),
//                            contentDescription = null,
//                            modifier = Modifier.size(50.dp)
//                        )
//                        Text(
//                            text = it.name,
//                            modifier = Modifier
//                                .padding(start = 15.dp)
//                                .weight(1f),
//                            fontSize = 25.sp
//                        )
//                    }
//                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterListPreview() {
    MarvelComicsTheme {
        StateHandler(state = CharacterViewModel.CharacterState.Success(listOf(Character(name = "name", imageUrl = "url"), Character(name = "name2", imageUrl = "url2"))))
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyCharacterListPreview() {
    MarvelComicsTheme {
        StateHandler(state = CharacterViewModel.CharacterState.Success())
    }
}

@Preview(showBackground = true)
@Composable
fun LoaderPreview() {
    MarvelComicsTheme {
        StateHandler(state = CharacterViewModel.CharacterState.Loading)
    }
}