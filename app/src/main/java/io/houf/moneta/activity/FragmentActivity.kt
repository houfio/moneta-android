package io.houf.moneta.activity

import android.app.ActionBar
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity as Activity
import io.houf.moneta.R

abstract class FragmentActivity(private val fragment: Fragment) : Activity(R.layout.fragment_activity) {
    override fun onCreate(state: Bundle?) {
        super.onCreate(state)

        findViewById<Toolbar>(R.id.topBar).also { topBar ->
            setActionBar(topBar)

            initializeBar(actionBar!!)
            actionBar?.setDisplayHomeAsUpEnabled(true)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, fragment)
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
