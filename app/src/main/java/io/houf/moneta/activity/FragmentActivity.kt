package io.houf.moneta.activity

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import io.houf.moneta.R
import androidx.fragment.app.FragmentActivity as Activity

abstract class FragmentActivity<T> : Activity(R.layout.fragment_activity) {
    override fun onCreate(state: Bundle?) {
        super.onCreate(state)

        val data = decode(intent);

        findViewById<Toolbar>(R.id.topBar).also { topBar ->
            setActionBar(topBar)

            initializeBar(actionBar!!, data)
            actionBar?.setDisplayHomeAsUpEnabled(true)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, getFragment(data))
            .commit()
    }

    abstract fun encode(bundle: Bundle, data: T)

    abstract fun decode(intent: Intent): T

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
