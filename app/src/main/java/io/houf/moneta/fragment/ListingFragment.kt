package io.houf.moneta.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import io.houf.moneta.theme.MonetaTheme
import io.houf.moneta.view.ListingView

class ListingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MonetaTheme {
                    Scaffold {
                        ListingView()
                    }
                }
            }
        }
    }
}
