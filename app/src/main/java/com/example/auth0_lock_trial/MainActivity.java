package com.example.auth0_lock_trial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.lock.AuthenticationCallback;
import com.auth0.android.lock.Lock;
import com.auth0.android.lock.LockCallback;
import com.auth0.android.lock.utils.LockException;
import com.auth0.android.result.Credentials;

public class MainActivity extends AppCompatActivity {

    private Lock lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Auth0 account = new Auth0(getString(
                R.string.com_auth0_client_id),
                getString(R.string.com_auth0_domain));
        account.setOIDCConformant(true);

        lock = Lock.newBuilder(account, callBack)
                .build(this);

        startActivity(lock.newIntent(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Your own Activity code
        lock.onDestroy(this);
        lock = null;
    }

    private LockCallback callBack = new AuthenticationCallback() {
        @Override
        public void onAuthentication(Credentials credentials) {
            Toast.makeText(getApplicationContext(),
                    "Log in successful",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCanceled() {
            Toast.makeText(getApplicationContext(),
                    "Log in unsuccessful",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(LockException error) {
            Toast.makeText(getApplicationContext(),
                    "Log in unsuccessful with error",
                    Toast.LENGTH_LONG).show();
        }
    };
}
