package com.retrocam.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.retrocam.app.ui.theme.RetroAmber
import com.retrocam.app.ui.theme.RetroDarkBackground
import com.retrocam.app.ui.theme.RetroTextPrimary
import com.retrocam.app.ui.theme.RetroTextSecondary

@Composable
fun SettingsScreen(
    onOpenCalibrationDebug: () -> Unit,
    onBack: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        color = RetroDarkBackground,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TextButton(onClick = onBack) {
                    Text(text = "← Kembali", color = RetroAmber)
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Pengaturan",
                    style = MaterialTheme.typography.titleMedium,
                    color = RetroTextPrimary,
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Pengaturan (M5–M6)",
                    style = MaterialTheme.typography.headlineSmall,
                    color = RetroTextPrimary,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Opsi kualitas dan format penyimpanan akan ditambahkan di M5–M6. Layar debug kalibrasi sudah aktif.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = RetroTextSecondary,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onOpenCalibrationDebug,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RetroAmber,
                        contentColor = RetroDarkBackground,
                    ),
                ) {
                    Text(text = "Buka Layar Debug & Kalibrasi")
                }
            }
        }
    }
}
