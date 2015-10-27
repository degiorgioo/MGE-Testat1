package com.example.dada.gadgetothek.service;

import android.util.Log;

import com.example.dada.gadgetothek.domain.Gadget;
import com.example.dada.gadgetothek.domain.Loan;
import com.example.dada.gadgetothek.domain.Reservation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryService {
    
    public static final String TAG = LibraryService.class.getSimpleName();
    public static LoginToken token;
    public static String serverUrl;
    public static String currentUser = null;

    public static void setServerAddress(String address) {
        Log.d(TAG, "Setting server to " + address);
        serverUrl = address;
    }

    public static boolean isLoggedIn() {
        return token != null;
    }

    public static void login(final String mail, String password, final Callback<Boolean> callback) {
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("email", mail);
        parameter.put("password", password);
        Request<LoginToken> request = new Request<>(HttpVerb.POST, serverUrl + "/login", LoginToken.class, parameter, new Callback<LoginToken>() {
            @Override
            public void onCompletion(LoginToken input) {
                token = input;
                callback.onCompletion(input != null && !input.getSecurityToken().isEmpty());
                currentUser = mail;
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
        request.execute();
    }

    public static void logout(final Callback<Boolean> callback) {
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("token", getTokenAsString());

        Request<Boolean> request = new Request<>(HttpVerb.POST, serverUrl + "/logout", Boolean.class, parameter, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                if (input) {
                    token = null;
                }
                callback.onCompletion(input);
            }

            @Override
            public void onError(String message) {
                token = null;
                callback.onError(message);
            }
        });
        request.execute();
    }

    public static void register(String mail, String password, String name, String studentenNumber, final Callback<Boolean> callback) {
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("email", mail);
        parameter.put("password", password);
        parameter.put("name", name);
        parameter.put("studentnumber", studentenNumber);

        Request<Boolean> request = new Request<>(HttpVerb.POST, serverUrl + "/register", Boolean.class, parameter, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                callback.onCompletion(input);
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
        request.execute();
    }


    public static void getLoansForCustomer(final Callback<List<Loan>> callback) {
        if (token == null) {
            throw new IllegalStateException("Not logged in");
        }
        HashMap<String, String> parameter = new HashMap<>();

        parameter.put("token", getTokenAsString());
        Request<List<Loan>> request = new Request<>(HttpVerb.GET, serverUrl + "/loans", new TypeToken<List<Loan>>() {
        }.getType(), parameter, new Callback<List<Loan>>() {
            @Override
            public void onCompletion(List<Loan> input) {
                callback.onCompletion(input == null ? new ArrayList<Loan>() : input);
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
        request.execute();
    }

    public static void getReservationsForCustomer(final Callback<List<Reservation>> callback) {
        if (token == null) {
            throw new IllegalStateException("Not logged in");
        }
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("token", getTokenAsString());

        Request<List<Reservation>> request = new Request<>(HttpVerb.GET, serverUrl + "/reservations", new TypeToken<List<Reservation>>() {
        }.getType(), parameter, new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> input) {
                callback.onCompletion(input == null ? new ArrayList<Reservation>() : input);
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
        request.execute();
    }


    public static void reserveGadget(Gadget toReserve, final Callback<Boolean> callback) {
        if (token == null) {
            throw new IllegalStateException("Not logged in");
        }
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("token", getTokenAsString());
        parameter.put("gadgetId", toReserve.getInventoryNumber());

        Request<Boolean> request = new Request<>(HttpVerb.POST, serverUrl + "/reservations", new TypeToken<Boolean>() {
        }.getType(), parameter, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean success) {
                callback.onCompletion(success);
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
        request.execute();
    }


    public static void deleteReservation(Reservation toDelete, final Callback<Boolean> callback) {
        if (token == null) {
            throw new IllegalStateException("Not logged in");
        }
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("token", getTokenAsString());
        parameter.put("id", toDelete.getReservationId());
        Request<Boolean> request = new Request<>(HttpVerb.DELETE, serverUrl + "/reservations", Boolean.class, parameter, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                callback.onCompletion(input);
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
        request.execute();
    }

    public static void getGadgets(final Callback<List<Gadget>> callback) {
        if (token == null) {
            throw new IllegalStateException("Not logged in");
        }
        HashMap<String, String> parameter = new HashMap<>();

        parameter.put("token", getTokenAsString());
        Request<List<Gadget>> request = new Request<>(HttpVerb.GET, serverUrl + "/gadgets", new TypeToken<List<Gadget>>() {
        }.getType(), parameter, new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> input) {
                callback.onCompletion(input);
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
        request.execute();
    }

    public static String getTokenAsString() {
        Gson gson = createGsonObject();
        return gson.toJson(token);
    }

    static Gson createGsonObject() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
    }

    public static void getLoansForCustomer() {
    }

    public String getCurrentUser(){
        return this.currentUser;
    }

    public String getCurrenServername(){
        return this.serverUrl;
    }
}


