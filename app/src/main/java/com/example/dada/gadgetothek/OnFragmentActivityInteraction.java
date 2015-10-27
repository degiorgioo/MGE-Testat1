package com.example.dada.gadgetothek;


import android.app.Fragment;
import android.view.View;

import com.example.dada.gadgetothek.domain.Gadget;
import com.example.dada.gadgetothek.domain.Reservation;
import com.example.dada.gadgetothek.service.LibraryService;

import java.util.List;

public interface OnFragmentActivityInteraction {
    void getAllGadgets(View v);
    void reserveGadget(Gadget g);
    void getAllReservation(View v);
    void deleteReservation(Reservation r);
    void loginUser(String email, String password, String servername);
    void registerUser(String mail, String password, String name, String studentenNumber);
    void setServerName(String name);
    void setSharedPreferences(String servername);
    void getAllLoans(View v);
    void switchFragment(Fragment f);
    LibraryService getLibServiceSession();
    List<Gadget> getAllGadgetAsList();
    List<Reservation> getAllReservationAsList();
    String getSharedPreferencesServername();
}
