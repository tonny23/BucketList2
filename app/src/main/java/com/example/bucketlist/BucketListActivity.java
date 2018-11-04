package com.example.bucketlist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

public class BucketListActivity extends AppCompatActivity implements ItemArrayAdapter.ItemClickListener{

    private List<BucketItem> mItems;
    private RecyclerView.Adapter mAdapter;

    @BindView(R.id.new_title_edit_text)
    EditText mTitleEditText;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_list);
        ButterKnife.bind(this);
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mItems = new ArrayList<>();

        //Add some items to the recyclerview.
        mItems.add(new BucketItem("Skydiving"));
        mItems.add(new BucketItem("Fly a plane"));
        mItems.add(new BucketItem("Drive a Formula 1 car"));
        mItems.add(new BucketItem("Go to Japan"));

        updateUI();
    }

    /**
     * A new item will be added if the edittext is not empty
     */
    @OnClick(R.id.fab)
    public void clickAdd() {
        //Check if the title and descriptions have text
        if (!TextUtils.isEmpty(mTitleEditText.getText())) {
            String title = mTitleEditText.getText().toString();
            //Add the new item to the mAdapter;
            mItems.add(new BucketItem(title, false));
            //Have the mAdapter update
            updateUI();
            //clear edittext after adding
            mTitleEditText.getText().clear();
        } else {
            //Show a message to the user
            Toast.makeText(BucketListActivity.this, "Please enter some text in the title field", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * If enter key on keyboard is pressed in the edittext, a new item will be added to the list
     * @param v
     * @param actionId
     * @param event
     * @return
     */
    @OnEditorAction(R.id.new_title_edit_text)
    public boolean enterText(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            clickAdd();
        }
        return false;
    }

    /**
     * Sets the adapter if it hasn't been set or update the adapter if the dataset has been modified
     */
    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new ItemArrayAdapter(mItems, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void itemOnLongClick(int position) {
        mItems.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    /**
     * If a checkbox is clicked the checked boolean of the bucketitem will be updated
     * @param position position of the item
     * @param checked checked value of the checkbox
     */
    @Override
    public void itemOnClick(int position, boolean checked) {
        mItems.get(position).setChecked(checked);
        mAdapter.notifyDataSetChanged();
    }
}
