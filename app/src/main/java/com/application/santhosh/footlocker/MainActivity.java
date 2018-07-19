package com.application.santhosh.footlocker;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.application.santhosh.footlocker.client.ChannelClient;
import com.application.santhosh.footlocker.client.ChannelInterface;
import com.application.santhosh.footlocker.model.Example;
import com.application.santhosh.footlocker.recyclerview.ChannelsAdapter;
import com.application.santhosh.footlocker.recyclerview.MyItemClickListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MyItemClickListener {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private ChannelsAdapter channelsAdapter;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager((MainActivity.this));
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(R.string.toolbar_title);

        if (!isNetworkAvailable())
            Toast.makeText(this, "No Network Availbale", Toast.LENGTH_LONG).show();
        else {
            Retrofit retrofit = ChannelClient.getClient();

            retrofit.create(ChannelInterface.class).fetchChannels()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResults, this::handleError);
        }

    }

    private void handleResults(Example rootObject) {
        if (rootObject.getChannels() != null) {
            channelsAdapter = new ChannelsAdapter(rootObject.getChannels(), MainActivity.this, this);
            recyclerView.setAdapter(channelsAdapter);
        } else {
            Log.e("Channels: ", "Can't retrieve the Data");
        }
    }

    private void handleError(Throwable t) {
        Log.e("Observer", "" + t.toString());
        Toast.makeText(this, "ERROR IN GETTING STORES",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(View v, int position) {
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager != null ? searchManager.getSearchableInfo(getComponentName()) : null);
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                channelsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                channelsAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_search || super.onOptionsItemSelected(item);

    }

}
