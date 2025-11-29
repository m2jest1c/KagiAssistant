package space.httpjames.kagiassistantmaterial.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import space.httpjames.kagiassistantmaterial.AssistantClient

@Composable
fun SettingsScreen(
    assistantClient: AssistantClient,
) {
    val state = rememberSettingsScreenState(assistantClient)

    LaunchedEffect(Unit) {
        state.runInit()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primaryFixed
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(128.dp))
            if (!state.emailAddressLoading) {
                InitialsAvatar(char = state.emailAddress.firstOrNull() ?: 'K')
                Text(
                    state.emailAddress,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium
                )
            } else {
                CircularProgressIndicator()
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                SettingsItem(
                    icon = Icons.Default.Keyboard,
                    title = "Open keyboard automatically",
                    subtitle = "Focus the message bar on app open",
                    pos = SettingsItemPosition.SINGLE,
                    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    iconTint = MaterialTheme.colorScheme.onPrimaryContainer,
                    rightSide = {
                        Switch(checked = state.openKeyboardAutomatically, onCheckedChange = {
                            state.toggleOpenKeyboardAutomatically()
                        })
                    }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                SettingsItem(
                    icon = Icons.Default.Logout,
                    title = "Sign out",
                    subtitle = "Sign out of your Kagi account on this device only",
                    pos = SettingsItemPosition.SINGLE,
                    iconBackgroundColor = MaterialTheme.colorScheme.errorContainer,
                    iconTint = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }
    }
}