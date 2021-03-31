package io.houf.moneta.activity

import android.app.ActionBar
import android.os.Bundle
import android.os.Parcelable
import android.view.MenuItem
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import io.houf.moneta.R
import androidx.fragment.app.FragmentActivity as Activity

abstract class FragmentActivity<T : Parcelable> : Activity(R.layout.fragment_activity) {
    override fun onCreate(state: Bundle?) {
        super.onCreate(state)

        val data = intent.getParcelableExtra<T>("data") ?: return

        findViewById<Toolbar>(R.id.topBar)?.also { topBar ->
            setActionBar(topBar)

            actionBar?.also { bar ->
                initializeBar(bar, data)
                bar.setDisplayHomeAsUpEnabled(true)
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, getFragment(data))
            .commit()
    }

    abstract fun initializeBar(bar: ActionBar, data: T)

    abstract fun getFragment(data: T): Fragment

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()

            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
