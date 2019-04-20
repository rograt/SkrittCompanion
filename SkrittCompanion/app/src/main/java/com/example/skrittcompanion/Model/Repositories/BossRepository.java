package com.example.skrittcompanion.Model.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.skrittcompanion.Model.DAOs.BossDAO;
import com.example.skrittcompanion.Model.WorldBoss;

import java.util.List;

import androidx.lifecycle.LiveData;


public class BossRepository {
    private BossDAO bossDAO;
    private LiveData<List<WorldBoss>> bosses;


    public BossRepository(Application application) {
        SkrittDB db = SkrittDB.getDatabase(application);
        bossDAO= db.bossDAO();
        bosses = bossDAO.getAllBosses();
    }

    public LiveData<List<WorldBoss>> getBosses() {
        return bosses;
    }


    public void insert(WorldBoss boss) {
        new BossRepository.insertAsyncTask(bossDAO).execute(boss);
    }

    private static class insertAsyncTask extends AsyncTask<WorldBoss, Void, Void> {

        private BossDAO mAsyncTaskDao;

        insertAsyncTask(BossDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final WorldBoss... params) {
            mAsyncTaskDao.insert(params[0]);

            return null;
        }
    }

}
