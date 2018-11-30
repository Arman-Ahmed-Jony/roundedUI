package com.example.arman.roundediu;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    private EditText emailET,passET;
    private Button loginbtn, registerbtn;
    private TextView statusTV;
    private FirebaseAuth auth;//this class is for authentication in firebase
    private FirebaseUser user;
    private UserAuthCompleteListenner authCompleteListenner;
    
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        authCompleteListenner= (UserAuthCompleteListenner) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        if(user!=null){
            authCompleteListenner.onAuthenticationComplete();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        emailET=view.findViewById(R.id.userName);
        passET=view.findViewById(R.id.password);
        loginbtn=view.findViewById(R.id.loginbtn);
        registerbtn=view.findViewById(R.id.registerbtn);
        statusTV=view.findViewById(R.id.statusTV);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=emailET.getText().toString();
                String pass=passET.getText().toString();

                loginUser(email,pass);

            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email=emailET.getText().toString();
                String pass=passET.getText().toString();

                registerNewUser(email,pass);
            }
        });
        return view;
    }

    private void loginUser(String email, String pass) {
        auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            user=auth.getCurrentUser();
                            statusTV.setText("Logged in as "+user.getEmail());
                            authCompleteListenner.onAuthenticationComplete();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                statusTV.setText(e.getMessage());
            }
        });

    }

    private void registerNewUser(String email, String pass) {
        auth.createUserWithEmailAndPassword(email,pass)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    authCompleteListenner.onAuthenticationComplete();
                    user=auth.getCurrentUser();
                    statusTV.setText("Logged in as "+user.getEmail());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                statusTV.setText(e.getMessage());
            }
        });

    }
    interface UserAuthCompleteListenner{
        void onAuthenticationComplete();
    }

}
