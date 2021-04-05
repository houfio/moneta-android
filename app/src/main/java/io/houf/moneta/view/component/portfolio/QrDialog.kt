package io.houf.moneta.view.component.portfolio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.houf.moneta.R
import io.houf.moneta.model.PortfolioModel
import io.houf.moneta.util.data.encodePortfolio
import io.houf.moneta.util.qr.generateQr

@Composable
fun QrDialog(listings: List<PortfolioModel>, onDismiss: () -> Unit) {
    val qr = remember(listings) {
        val portfolio = listings.map { it.portfolio }
        val data = encodePortfolio(portfolio)

        generateQr(data)
    }

    Dialog(onDismiss) {
        Surface(
            elevation = 16.dp,
            shape = RoundedCornerShape(6.dp)
        ) {
            Column(Modifier.padding(16.dp).padding(bottom = 16.dp)) {
                if (qr != null) {
                    Image(
                        bitmap = qr,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Text(
                    text = stringResource(R.string.export_portfolio),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
