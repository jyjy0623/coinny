package com.coinny.storedcard.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import com.coinny.storedcard.BuildConfig
import com.coinny.storedcard.R
import com.coinny.storedcard.databinding.ActivityMainBinding
import com.coinny.storedcard.domain.model.VersionInfo
import com.coinny.storedcard.ui.addedit.AddEditCardActivity
import com.coinny.storedcard.ui.carddetail.CardDetailActivity
import com.coinny.storedcard.ui.common.UpdateDialogFragment
import com.coinny.storedcard.util.VersionCheckUtil
import com.coinny.storedcard.worker.ExpiryCheckWorker
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory(this) }
    private lateinit var cardAdapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.main_title)

        setupRecyclerView()
        setupFab()
        observeCards()
        checkForUpdate()
    }

    private fun setupRecyclerView() {
        cardAdapter = CardAdapter { card ->
            val intent = Intent(this, CardDetailActivity::class.java)
            intent.putExtra("CARD_ID", card.id)
            startActivity(intent)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = cardAdapter
        }
    }

    private fun setupFab() {
        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddEditCardActivity::class.java))
        }
    }

    private fun observeCards() {
        lifecycleScope.launch {
            viewModel.cards.collect { cards ->
                if (cards.isEmpty()) {
                    binding.emptyView.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    binding.emptyView.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    cardAdapter.submitList(cards)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_statistics -> {
                // TODO: Open statistics activity
                true
            }
            R.id.action_backup -> {
                viewModel.exportBackup()
                true
            }
            R.id.action_restore -> {
                // TODO: Open file picker for restore
                true
            }
            R.id.action_check_update -> {
                checkForUpdate()
                true
            }
            R.id.action_about -> {
                showAboutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAboutDialog() {
        val aboutMessage = getString(R.string.about_version, BuildConfig.VERSION_NAME) + "\n" +
                getString(R.string.about_build_time, BuildConfig.BUILD_TIME) + "\n\n" +
                getString(R.string.about_power_by, BuildConfig.ORGANIZATION)

        AlertDialog.Builder(this)
            .setTitle(R.string.about_title)
            .setMessage(aboutMessage)
            .setPositiveButton(R.string.ok, null)
            .show()
    }

    private fun checkForUpdate() {
        lifecycleScope.launch {
            try {
                val versionInfo = VersionCheckUtil.checkForUpdate(this@MainActivity)
                if (versionInfo != null) {
                    showUpdateDialog(versionInfo)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // 静默失败，不影响用户使用
            }
        }
    }

    private fun showUpdateDialog(versionInfo: VersionInfo) {
        val dialog = UpdateDialogFragment.newInstance(versionInfo)
        dialog.show(supportFragmentManager, "UpdateDialog")
    }
}
