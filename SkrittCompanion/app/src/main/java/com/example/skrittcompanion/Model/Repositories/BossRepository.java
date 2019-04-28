package com.example.skrittcompanion.Model.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.skrittcompanion.Model.DAOs.BossDAO;
import com.example.skrittcompanion.Model.WorldBoss;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;


public class BossRepository {
    private static BossRepository repository;
    private BossDAO bossDAO;

    public static BossRepository getInstance(Application application){
        if(repository!=null){
            return repository;
        }
        else {
            return repository=new BossRepository(application);
        }
    }

    private BossRepository(Application application) {
        SkrittDB db = SkrittDB.getDatabase(application);
        bossDAO= db.bossDAO();
        // Do sth about this in the final version as it'll get executed every time thus defeating the purpose
        // of having a god damn db in the first place.
        if(bossDAO.getAllBosses().getValue()==null){
            bossInitializer();
        }
    }

    public void insert(WorldBoss boss) {
        new BossRepository.insertAsyncTask(bossDAO).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,boss);
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

    public LiveData<List<WorldBoss>> getBosses(){
        return bossDAO.getAllBosses();
    }
    private void bossInitializer(){
        // Fix this so that it'd work on every time zone and not just UTC+2
        WorldBoss jungleWurm=new WorldBoss("[&BEEFAAA=]","Great Jungle Wurm",3,15,"https://wiki.guildwars2.com/wiki/Defeat_the_great_jungle_wurm","Wychmire Swamp",2);
        WorldBoss taidha = new WorldBoss("[&BKgBAAA=]","Admiral Taidha Covington",2,0,"https://wiki.guildwars2.com/wiki/Kill_Admiral_Taidha_Covington","Bloodtide Coast",3);
        WorldBoss svanir = new WorldBoss("[&BKgBAAA=]","Svanir Shaman Chief",2,15,"https://wiki.guildwars2.com/wiki/Kill_the_Svanir_shaman_chief_to_break_his_control_over_the_ice_elemental","Hunter's Lake",2);
        WorldBoss mdestroyer = new WorldBoss("[&BM0CAAA=]","Megadestroyer",2,30,"https://wiki.guildwars2.com/wiki/Kill_the_megadestroyer_before_it_blows_everyone_up","Maelstrom's Bile",3);
        WorldBoss fireElemental = new WorldBoss("[&BE4DAAA=]","Fire Elemental",2,45,"https://wiki.guildwars2.com/wiki/Destroy_the_fire_elemental_created_from_chaotic_energy_fusing_with_the_C.L.E.A.N._5000%27s_energy_core","Thaumanova Reactor",2);
        WorldBoss shatterer = new WorldBoss("[&BKgBAAA=]","The Shatterer",3,0,"https://wiki.guildwars2.com/wiki/Slay_the_Shatterer","Lowland Burns",3);
        WorldBoss ulgoth=new WorldBoss("[&BLAAAAA=]","Modniir Ulgoth",3,30,"https://wiki.guildwars2.com/wiki/Defeat_Ulgoth_the_Modniir_and_his_minions","Modniir Gorge",3);
        WorldBoss behemonth=new WorldBoss("[&BPcAAAA=]","Shadow Behemoth",3,45,"https://wiki.guildwars2.com/wiki/Defeat_the_shadow_behemoth","Godslost Swamp",2);
        WorldBoss markIIGolem=new WorldBoss("[&BNQCAAA=]","Golem Mark II",4,0,"https://wiki.guildwars2.com/wiki/Defeat_the_Inquest%27s_golem_Mark_II","Whitland Flats",3);
        WorldBoss jormag=new WorldBoss("[&BHoCAAA=]","Claw Of Jarmag",4,30,"https://wiki.guildwars2.com/wiki/Defeat_the_Claw_of_Jormag","Frostwalk Tundra",3);
        insert(jungleWurm);
        insert(taidha);
        insert(svanir);
        insert(mdestroyer);
        insert(fireElemental);
        insert(shatterer);
        insert(ulgoth);
        insert(behemonth);
        insert(markIIGolem);
        insert(jormag);
    }

    public List<WorldBoss> getTimedBosses(List<WorldBoss> worldBosses){
        int currentHour= Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute=Calendar.getInstance().get(Calendar.MINUTE);
        List<WorldBoss> timedBosses=new ArrayList<>();
        if(worldBosses!=null){
            for (WorldBoss boss : worldBosses) {
                int currentHourCopy=currentHour;
                for (int i = 0; i < 4; i++) {
                    if(currentHourCopy%boss.getInterval()==boss.getExpected()){
                        {
                            if((currentHourCopy==currentHour&&currentMinute<boss.getMinute())||currentHourCopy>currentHour){
                                WorldBoss bossCop = new WorldBoss(boss.getClosestWaypoint(),boss.getBossName(),currentHourCopy,boss.getMinute(),boss.getWikiPage(),boss.getArea(),boss.getInterval());
                                timedBosses.add(bossCop);
                            }
                        }
                    }
                    currentHourCopy++;
                }
            }
            // Replace the sort with a custom sort so that the app would run on more than Oreo / Pie
            timedBosses.sort(new Comparator<WorldBoss>() {
                @Override
                public int compare(WorldBoss o1, WorldBoss o2) {
                    return Integer.compare(o1.getHour()*60 + o1.getMinute(), o2.getMinute() + o2.getHour()*60);
                }
            });
        }

        return timedBosses;
    }

}

