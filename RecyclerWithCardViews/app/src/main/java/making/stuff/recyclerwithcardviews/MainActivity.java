package making.stuff.recyclerwithcardviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<PersonalData> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        loadData(10);
        DataAdapter adapter = new DataAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }

    private void loadData(int n){
        dataList = new ArrayList<>();
        final String NAME = "name#";
        final String EMAIL = "email#";
        final String CITY = "city#";
        PersonalData data;
        for (int i=1;i<=n;i++){
            data = new PersonalData();
            data.name = NAME+i;
            data.email = EMAIL+i;
            data.city = CITY+i;
            dataList.add(data);
        }
    }
}
