package cs.apps.obg.activity;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cs.apps.obg.R;
import cs.apps.obg.adapter.ListAdapter;
import cs.apps.obg.database.DBHelper;
import cs.apps.obg.domain.FlagVO;

public class AllListActivity extends AppCompatActivity {
    private String[] navItems = {"Europe","Asia","Oceania", "America","Africa","Middle East"};
    private DBHelper db;
    private ListAdapter mAdapter;
    private RecyclerView recyclerView;
    private ArrayList<FlagVO> mItems;
    private LinearLayout detailLayout;
    private TextView detailContinent, detailCountry, detailCapital;
    private ImageView detailImage;
    private ImageButton detailCloseBtn;
    private ListView navList;
    private FrameLayout flContainer;
    private Toolbar toolbar;
    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle dtToggle;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);
        db = new DBHelper(this);
        db.open();
        //mAdapter = new ListAdapter();
        navList = (ListView) findViewById(R.id.lv_activity_main_nav_list);
        flContainer = (FrameLayout) findViewById(R.id.container);
        setRecyclerView();
        navList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navItems));
        navList.setOnItemClickListener(new DrawerItemClickListener());
        navList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navItems));
        navList.setOnItemClickListener(new DrawerItemClickListener());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.setDrawerListener(dtToggle);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    private void setRecyclerView() {

        mItems = db.selectAllData(1);
        mAdapter = new ListAdapter(mItems, this);
        recyclerView = (RecyclerView) findViewById(R.id.all_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailLayout = (LinearLayout) findViewById(R.id.list_detail_layout);
        detailCapital = (TextView) findViewById(R.id.detail_capital);
        detailContinent = (TextView) findViewById(R.id.detail_continent);
        detailCountry = (TextView) findViewById(R.id.detail_country);
        detailImage = (ImageView) findViewById(R.id.detail_image);
        detailCloseBtn = (ImageButton) findViewById(R.id.detail_close_btn);
        mAdapter.setItemClick(new ListAdapter.ItemClick() {
            @Override
            public void onClick(View view, int position) {
                Log.d("AA", mItems.get(position).getCapital());
                detailLayout.setVisibility(View.VISIBLE);
                detailCapital.setText(mItems.get(position).getCapital());
                detailContinent.setText(getContinentString(mItems.get(position).getContinentNum()));
                detailCountry.setText(mItems.get(position).getCountry());
                Glide.with(getApplicationContext()).load(mItems.get(position).getResId()).into(detailImage);
            }
        });
        detailCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        dtToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (dtToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id){
            switch(position){
                case 0:
                    Toast.makeText(AllListActivity.this,"Europe",Toast.LENGTH_SHORT).show();
                    setAllList(position+1);
                    break;
                case 1:
                    Toast.makeText(AllListActivity.this,"Asia",Toast.LENGTH_SHORT).show();
                    setAllList(position+1);
                    break;
                case 2:
                    Toast.makeText(AllListActivity.this,"Oceania",Toast.LENGTH_SHORT).show();
                    setAllList(position+1);
                    break;
                case 3:
                    Toast.makeText(AllListActivity.this,"America",Toast.LENGTH_SHORT).show();
                    setAllList(position+1);
                    break;
                case 4:
                    Toast.makeText(AllListActivity.this,"Africa",Toast.LENGTH_SHORT).show();
                    setAllList(position+1);
                    break;
                case 5:
                    Toast.makeText(AllListActivity.this,"Middle East",Toast.LENGTH_SHORT).show();
                    setAllList(position+1);
                    break;
            }
            dlDrawer.closeDrawer(navList);
        }
    }

    private void setAllList(int position) {
        mItems.clear();
        mItems = db.selectAllData(position);
        mAdapter.setItems(mItems);
        mAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(0);
    }

    private String getContinentString(int position) {
        String str = "";
        if (position == 1) {
            str = "Europe";
        } else if (position == 2) {
            str = "Asia";
        } else if (position == 3) {
            str = "Oceania";
        } else if (position == 4) {
            str = "America";
        } else if (position == 5) {
            str = "Africa";
        } else if (position == 6) {
            str = "Middle East";
        }
        return str;
    }

    @Override
    public void onBackPressed() {
        if (detailLayout.getVisibility() == View.VISIBLE) {
            detailLayout.setVisibility(View.GONE);
        } else {
            finish();
        }
    }

    public void println(String str) {
        Log.d("AA",str);
    }

}
