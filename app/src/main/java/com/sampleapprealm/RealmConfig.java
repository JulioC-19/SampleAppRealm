package com.sampleapprealm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmConfig extends Application{
        @Override
        public void onCreate(){
            super.onCreate();
            Realm.init(this);

            // Configuring realm version 0
            RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                                                    .deleteRealmIfMigrationNeeded()
                                                    .schemaVersion(0)
                                                    .build();
            Realm.setDefaultConfiguration(realmConfig);
        }
}


