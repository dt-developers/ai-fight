package de.telekom.theaifight.chatgpt4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


class TicTacToeViewModel : ViewModel() {
    val board = mutableStateOf(List(3) { List(3) { CellState.NONE } })
    val currentPlayer = MutableLiveData(CellState.PLAYER_X)
    val gameState = MutableLiveData(GameState.ONGOING)

    fun makeMove(x: Int, y: Int) {
        if (board.value!![x][y] == CellState.NONE && gameState.value == GameState.ONGOING) {
            // Copy the board and make the change to the copy.
            val newBoard = board.value.mapIndexed { i, row ->
                row.mapIndexed { j, cell ->
                    if (i == x && j == y) currentPlayer.value!! else cell
                }
            }
            board.value = newBoard
            checkGameState()
            switchPlayer()

            if (currentPlayer.value == CellState.PLAYER_O) {
                aiMove()
            }
        }
    }

    private fun switchPlayer() {
        currentPlayer.value =
            if (currentPlayer.value == CellState.PLAYER_X) CellState.PLAYER_O else CellState.PLAYER_X
    }

    private fun checkGameState() {
        // Check rows, columns, diagonals
        // Set gameState accordingly
    }
}

enum class CellState {
    NONE, PLAYER_X, PLAYER_O
}

enum class GameState {
    ONGOING, X_WINS, O_WINS, DRAW
}


@Composable
fun TicTacToeGame(viewModel: TicTacToeViewModel = viewModel()) {
    val board by viewModel.board
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        board?.let {
            for (i in it.indices) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (j in it[i].indices) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .border(1.dp, Color.Black)
                                .clickable {
                                    viewModel.makeMove(i, j)
                                }
                        ) {
                            Text(
                                text = when (it[i][j]) {
                                    CellState.PLAYER_X -> "X"
                                    CellState.PLAYER_O -> "O"
                                    else -> ""
                                },
                                fontSize = 24.sp
                            )
                        }
                    }
                }
            }
        }
    }
}


fun TicTacToeViewModel.aiMove() {
    val emptyCells = mutableListOf<Pair<Int, Int>>()
    board.value!!.forEachIndexed { i, row ->
        row.forEachIndexed { j, cell ->
            if (cell == CellState.NONE) {
                emptyCells.add(i to j)
            }
        }
    }

    if (emptyCells.isNotEmpty()) {
        val (x, y) = emptyCells.random()
        makeMove(x, y)
    }
}

class TicTacToeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                TicTacToeGame()
            }
        }
    }
}
