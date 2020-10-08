package no.trymv.fantj;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import no.trymv.fantj.data.model.User;

public class MainActivity extends AppCompatActivity {
    FantService service;
    MarketAdapter adapter = new MarketAdapter();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUserInfo();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });

        // Open message when clicked
        adapter.setOnClickListener(position -> System.out.println("Open item #" + position));
        recyclerView = findViewById(R.id.conversations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        FantService.getInstance().loadItems(adapter::setConversations,System.out::println);
    }

    private void setUserInfo() {
        service = FantService.getInstance();
        User user = service.getUser();
        if(user != null) {
            TextView lastName = findViewById(R.id.first_name);

            lastName.setText(user.getLastName());
        }
    }
}