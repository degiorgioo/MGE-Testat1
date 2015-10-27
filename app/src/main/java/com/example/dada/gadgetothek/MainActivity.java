package com.example.dada.gadgetothek;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dada.gadgetothek.domain.Gadget;
import com.example.dada.gadgetothek.domain.Loan;
import com.example.dada.gadgetothek.domain.Reservation;
import com.example.dada.gadgetothek.service.Callback;
import com.example.dada.gadgetothek.service.LibraryService;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnFragmentActivityInteraction{

    private DrawerLayout drawer;
    private View content;
    private LibraryService libservice;

    private RecyclerView loansListView;
    private RecyclerView reservationListView;
    private RecyclerView gadgetListView;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManager2;
    private RecyclerView.LayoutManager layoutManager3;

    private LoansAdapter loansAdapterObj;
    private ReservationAdapter reservationsAdapterObj;
    private GagdgetAdapter gadgetAdapterObj;

    List<Reservation> listAllReservation;
    List<Gadget> listAllGadget;
    Toolbar toolbar;

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        content = findViewById(R.id.content);
        libservice = new LibraryService();

        loansListView = (RecyclerView) findViewById(R.id.recyclerView);
        reservationListView = (RecyclerView) findViewById(R.id.recyclerViewReservations);
        gadgetListView = (RecyclerView) findViewById(R.id.listView_gadgets);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        sharedpreferences = getPreferences(Context.MODE_PRIVATE);
        setServerName(sharedpreferences.getString("SERVERNAME", "http://mge1.dev.ifs.hsr.ch/public"));

        switchFragment(new HomeFragment());

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch(menuItem.toString()){
            case "Home":
                switchFragment(new HomeFragment());
                break;
            case "Login":
                switchFragment(new LoginFragment());
                break;
            case "Register":
                switchFragment(new RegisterFragment());
                break;
            case "Loans":
                switchFragment(new LoansFragment());
                break;
            case "My reservations":
                switchFragment(new ReservationFragment());
                break;
            case "Settings":
                switchFragment(new SettingsFragment());
                break;
            case "Logout":
                libservice.logout(new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        Toast.makeText(getApplicationContext(), "Logout successfull", LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(getApplicationContext(), message , LENGTH_SHORT).show();
                    }
                });
        }
        return false;
    }

    @Override
    public void loginUser(String email, String password, String servername) {
        libservice.setServerAddress(servername);
        libservice.login(email, password, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                Toast.makeText(getApplicationContext(), "Login successfull", LENGTH_SHORT).show();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(), message, LENGTH_SHORT).show();
            }
        });
    }

    public void switchFragment(Fragment f){
        getFragmentManager().beginTransaction().replace(R.id.content, f).addToBackStack(null).commit();
        drawer.closeDrawers();
    }

    @Override
    public void registerUser(String mail, String password, String name, String studentenNumber) {
        libservice.register(mail, password, name, studentenNumber, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                Toast.makeText(getApplicationContext(), "User successfull registred", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void getAllLoans(final View v) {
        libservice.getLoansForCustomer(new Callback<List<Loan>>() {
            @Override
            public void onCompletion(List<Loan> input) {
                loansAdapterObj = new LoansAdapter(input);
                loansListView = (RecyclerView) v.findViewById(R.id.recyclerView);
                layoutManager = new LinearLayoutManager(getApplicationContext());
                loansListView.setLayoutManager(layoutManager);
                loansListView.setAdapter(loansAdapterObj);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(), message , LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getAllReservation(final View v) {
        listAllReservation = new ArrayList<>();
        libservice.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> input) {
                reservationsAdapterObj = new ReservationAdapter(input);
                reservationListView = (RecyclerView) v.findViewById(R.id.recyclerViewReservations);
                layoutManager2 = new LinearLayoutManager(getApplicationContext());
                reservationListView.setLayoutManager(layoutManager2);
                reservationListView.setAdapter(reservationsAdapterObj);
                listAllReservation.addAll(input);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(), message, LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public  List<Reservation> getAllReservationAsList() {
        return listAllReservation;
    }

    @Override
    public void getAllGadgets(final View v) {
        listAllGadget = new ArrayList<>();
        libservice.getGadgets(new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> input) {
                gadgetAdapterObj = new GagdgetAdapter(input, getApplicationContext());
                gadgetListView = (RecyclerView) v.findViewById(R.id.listView_gadgets);
                layoutManager3 = new LinearLayoutManager(getApplicationContext());
                gadgetListView.setLayoutManager(layoutManager3);
                gadgetListView.setAdapter(gadgetAdapterObj);
                listAllGadget.addAll(input);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(), message , LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public LibraryService getLibServiceSession(){
        return this.libservice;
    }

    @Override
    public List<Gadget> getAllGadgetAsList() {
        return listAllGadget;
    }

    @Override
    public void setServerName(String name) {
        libservice.setServerAddress(name);
    }

    @Override
    public void setSharedPreferences(String servername) {
        editor = sharedpreferences.edit();
        editor.putString("SERVERNAME", servername);
        editor.clear();
        editor.commit();
    }

    @Override
    public String getSharedPreferencesServername() {
        return sharedpreferences.getString("SERVERNAME", "test");
    }


    @Override
    public void reserveGadget(Gadget g) {
        libservice.reserveGadget(g, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                if (input) {
                    Toast.makeText(getApplicationContext(), "Reservation successful", LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Reservation  not successful", LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(), message , LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void deleteReservation(Reservation r) {
        libservice.deleteReservation(r, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                if (input) {
                    Toast.makeText(getApplicationContext(), "Reservation successful deleted", LENGTH_SHORT).show();
                    getAllReservation(content);
                } else {
                    Toast.makeText(getApplicationContext(), "Reservation not successful deleted", LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(), message , LENGTH_SHORT).show();
            }
        });
    }
}
