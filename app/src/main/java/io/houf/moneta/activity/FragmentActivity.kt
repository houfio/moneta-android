package io.houf.moneta.activity

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import io.houf.moneta.R
import androidx.fragment.app.FragmentActivity as Activity

abstract class FragmentActivity(private val fragment: (Intent) -> Fragment) : Activity(R.layout.fragment_activity) {
    override fun onCreate(state: Bundle?) {
        super.onCreate(state)

        findViewById<Toolbar>(R.id.topBar).also { topBar ->
            setActionBar(topBar)

            initializeBar(actionBar!!)
            actionBar?.setDisplayHomeAsUpEnabled(true)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, fragment(intent))
            .commit()
    }

    abstract fun initializeBar(bar: ActionBar);

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()

            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
