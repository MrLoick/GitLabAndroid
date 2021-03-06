package com.commit451.gitlab.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.commit451.gitlab.GitLabApp;
import com.commit451.gitlab.R;
import com.commit451.gitlab.api.GitLabClient;
import com.commit451.gitlab.model.Diff;
import com.commit451.gitlab.model.DiffLine;
import com.commit451.gitlab.views.DiffView;
import com.commit451.gitlab.views.MessageView;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;
import timber.log.Timber;

public class DiffActivity extends BaseActivity {

	private static final String EXTRA_COMMIT = "extra_commit";

	public static Intent newInstance(Context context, DiffLine commit) {
		Intent intent = new Intent(context, DiffActivity.class);
		intent.putExtra(EXTRA_COMMIT, Parcels.wrap(commit));
		return intent;
	}

	@Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.message_container) LinearLayout messageContainer;
	@Bind(R.id.diff_container) LinearLayout diffContainer;

    DiffLine commit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diff);
		ButterKnife.bind(this);
        commit = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_COMMIT));
		init();
	}
	
	private void init() {
		toolbar.setNavigationIcon(R.drawable.ic_back);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		toolbar.setTitle(commit.getShortId());
		toolbar.inflateMenu(R.menu.diff);
		toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch(item.getItemId()) {
					case android.R.id.home:
						finish();
						return true;
					case R.id.text_wrap_checkbox:
						item.setChecked(!item.isChecked());
						setTextWrap(item.isChecked());
						return true;
				}
				return false;
			}
		});

		//TODO make this use RecyclerViews, cause this is insane
		GitLabClient.instance().getCommit(GitLabApp.instance().getSelectedProject().getId(), commit.getId()).enqueue(commitCallback);
		GitLabClient.instance().getCommitDiff(GitLabApp.instance().getSelectedProject().getId(), commit.getId()).enqueue(diffCallback);
	}

	private Callback<DiffLine> commitCallback = new Callback<DiffLine>() {

		@Override
		public void onResponse(Response<DiffLine> response) {
			if (response.isSuccess()) {
				messageContainer.addView(new MessageView(DiffActivity.this, response.body()));
			}
		}

		@Override
		public void onFailure(Throwable t) {
			Timber.e(t.toString());
			Snackbar.make(getWindow().getDecorView(), getString(R.string.connection_error), Snackbar.LENGTH_SHORT)
					.show();
		}
	};
	
	private Callback<List<Diff>> diffCallback = new Callback<List<Diff>>() {

		@Override
		public void onResponse(Response<List<Diff>> response) {
			if (response.isSuccess()) {
				for(Diff diff : response.body()) {
					diffContainer.addView(new DiffView(DiffActivity.this, diff));
				}
			}
		}

		@Override
		public void onFailure(Throwable t) {
			Timber.e(t.toString());
			Snackbar.make(getWindow().getDecorView(), getString(R.string.connection_error), Snackbar.LENGTH_SHORT)
					.show();
		}
	};
	
	private void setTextWrap(boolean checked) {
		((MessageView) messageContainer.getChildAt(0)).setWrapped(checked);

		for(int i = 0; i < diffContainer.getChildCount(); ++i) {
			((DiffView) diffContainer.getChildAt(i)).setWrapped(checked);
		}
	}
}