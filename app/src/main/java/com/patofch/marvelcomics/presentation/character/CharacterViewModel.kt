package com.patofch.marvelcomics.presentation.character

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patofch.marvelcomics.domain.model.Character
import com.patofch.marvelcomics.domain.use_case.character.CharacterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCases: CharacterUseCases
) : ViewModel() {

    private val _state = mutableStateOf<CharacterState>(CharacterState.Loading)
    val state: State<CharacterState> get() = _state

    private var charactersJob: Job? = null

    fun getCharacters() {
        charactersJob?.cancel()
        charactersJob = characterUseCases.getCharacters().onEach { characters->
            _state.value = CharacterState.Success(characters)
        }.launchIn(viewModelScope)
    }

    fun insertCharacters() {
        viewModelScope.launch {
            characterUseCases.insertCharacter(Character(name = "3-D Man", imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"))
            characterUseCases.insertCharacter(Character(name = "A-Bomb (HAS)", imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg"))
            characterUseCases.insertCharacter(Character(name = "A.I.M.", imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec.jpg"))
        }
    }

    sealed class CharacterState {
        object Loading: CharacterState()
        data class Success(val characters: List<Character> = emptyList()): CharacterState()
        data class Error(val message: String): CharacterState()
    }
}