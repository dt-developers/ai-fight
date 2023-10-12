package de.telekom.theaifight.chatgpt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TicTacToeGame(viewModel: TicTacToeViewModel = viewModel()) {
    val board = viewModel.board

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        for (i in board.indices) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (j in board[i].indices) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp)
                            .background(Color.Gray)
                            .clickable { viewModel.play(i, j) }
                    ) {
                        Text(
                            board[i][j].value.toString(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TicTacToeGamePreview() {
    TicTacToeGame()
}

class TicTacToeViewModel : ViewModel() {
    var board = List(3) { row -> List(3) { col -> mutableStateOf(' ') } }
    var currentPlayer = mutableStateOf('X')

    fun play(row: Int, col: Int): Boolean {
        val currentCell = board[row][col].value
        if (currentCell == ' ') {
            board[row][col].value = currentPlayer.value
            if (checkWin(row, col)) {
                // Handle win
                return true
            }
            currentPlayer.value = if (currentPlayer.value == 'X') 'O' else 'X'

            if (currentPlayer.value == 'O') {
                playAI()
            }
        }
        return false
    }

    private fun checkWin(row: Int, col: Int): Boolean {
        // Check rows, columns, and diagonals
        val currentSymbol = board[row][col].value
        return (board[row].all { it.value == currentSymbol } ||
                board.map { it[col] }.all { it.value == currentSymbol } ||
                board.indices.all { board[it][it].value == currentSymbol } ||
                board.indices.all { board[it][board.size - 1 - it].value == currentSymbol })
    }

    fun reset() {
        board.forEach { row ->
            row.forEach { cell ->
                cell.value = ' '
            }
        }
        currentPlayer.value = 'X'
    }

    // Add a basic AI strategy here
    fun playAI() {
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j].value == ' ') {
                    play(i, j)
                    return
                }
            }
        }
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
                TicTacToeGame(

                )
            }
        }
    }
}
