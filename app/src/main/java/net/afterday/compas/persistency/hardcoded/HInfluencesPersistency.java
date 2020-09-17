package net.afterday.compas.persistency.hardcoded;

import net.afterday.compas.core.influences.Emission;
import net.afterday.compas.core.influences.Influence;
import net.afterday.compas.persistency.influences.InfluencesPersistency;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Justas Spakauskas on 2/3/2018.
 */

public class HInfluencesPersistency implements InfluencesPersistency
{
    @Override
    public List<Influence> getPossibleInfluences()
    {
        return null;
    }

    @Override
    public List<String> getRegisteredWifiModules()
    {
        List<String> wifiInfls = new ArrayList<>();

        wifiInfls.add("3e:71:bf:29:ac:f4");
        wifiInfls.add("3e:71:bf:29:aa:d5");
        wifiInfls.add("3e:71:bf:29:a7:1e");
        wifiInfls.add("3e:71:bf:28:f9:43");
        wifiInfls.add("3e:71:bf:29:a9:07");
        wifiInfls.add("3e:71:bf:28:fa:09");
        wifiInfls.add("3e:71:bf:29:a2:f6");
        wifiInfls.add("3e:71:bf:29:ac:38");
        wifiInfls.add("3e:71:bf:29:a6:81");
        wifiInfls.add("3e:71:bf:29:ac:37");
        wifiInfls.add("3e:71:bf:28:f9:d2");
        wifiInfls.add("3e:71:bf:29:a5:59");
        wifiInfls.add("3e:71:bf:29:a8:a9");
        wifiInfls.add("3e:71:bf:28:fa:da");
        wifiInfls.add("3e:71:bf:29:ab:dc");
        wifiInfls.add("3e:71:bf:29:a9:8c");
        wifiInfls.add("3e:71:bf:29:ac:7c");
        wifiInfls.add("3e:71:bf:29:ad:42");
        wifiInfls.add("3e:71:bf:28:fc:8d");
        wifiInfls.add("3e:71:bf:29:a1:44");
        wifiInfls.add("ee:fa:bc:28:7a:cd");
        wifiInfls.add("ee:fa:bc:28:6f:9c");
        wifiInfls.add("ee:fa:bc:28:1f:a1");
        wifiInfls.add("ee:fa:bc:28:77:ff");
        wifiInfls.add("ee:fa:bc:28:75:4a");
        wifiInfls.add("ee:fa:bc:28:7a:5f");
        wifiInfls.add("ee:fa:bc:28:75:c1");
        wifiInfls.add("ee:fa:bc:28:20:21");
        wifiInfls.add("ee:fa:bc:28:70:b7");
        wifiInfls.add("ee:fa:bc:28:79:d9");
        wifiInfls.add("2e:f4:32:13:17:f1");
        wifiInfls.add("2e:f4:32:13:17:f1");
        wifiInfls.add("2e:f4:32:13:24:07");
        wifiInfls.add("2e:f4:32:13:17:0a");
        wifiInfls.add("2e:f4:32:13:20:28");
        wifiInfls.add("2e:f4:32:13:1c:0f");
        wifiInfls.add("2e:f4:32:13:22:c8");
        wifiInfls.add("a6:cf:12:bf:51:63");
        wifiInfls.add("a6:cf:12:bf:56:cd");
        wifiInfls.add("a6:cf:12:bf:59:cb");
        wifiInfls.add("a6:cf:12:bf:5b:44");
        wifiInfls.add("a6:cf:12:bf:5e:a7");
        wifiInfls.add("a6:cf:12:bf:4f:2a");
        wifiInfls.add("ee:fa:bc:4b:c4:1e");
        wifiInfls.add("ee:fa:bc:4b:ca:d6");
        wifiInfls.add("ee:fa:bc:4b:c2:c8");
        wifiInfls.add("ee:fa:bc:4b:bf:40");
        wifiInfls.add("ee:fa:bc:4b:be:3f");
        wifiInfls.add("ee:fa:bc:4b:c2:d9");
        wifiInfls.add("ee:fa:bc:4b:41:56");
        wifiInfls.add("ee:fa:bc:4b:c5:23");
        // Shaman
        wifiInfls.add("86:f3:eb:dd:fe:a7");
        wifiInfls.add("de:4f:22:75:87:f8");
        wifiInfls.add("de:4f:22:75:86:2d");
        wifiInfls.add("de:4f:22:75:89:fb");
        wifiInfls.add("de:4f:22:75:7f:6d");
        wifiInfls.add("52:02:91:c2:f9:62");
        wifiInfls.add("de:4f:22:75:8b:20");
        wifiInfls.add("2e:f4:32:49:cf:39");
        wifiInfls.add("de:4f:22:75:82:6c");
        wifiInfls.add("ee:fa:bc:5e:94:95");
        wifiInfls.add("18:f0:e4:d6:41:67"); //Tel shaman
        // Lowrence
        wifiInfls.add("4a:3f:da:55:4d:15");
        wifiInfls.add("4a:3f:da:55:34:87");
        wifiInfls.add("4a:3f:da:56:7d:11");

        return wifiInfls;
    }

    @Override
    public List<Emission> getEmissions()
    {
        List<Emission> emissions = new ArrayList<Emission>();

        //Test Emissions
        emissions.add(emission(at(9, 16, 15, 0), 15, 20));
        emissions.add(emission(at(9, 25, 15, 0), 15, 20));
        // Game Emissions
        emissions.add(emission(at(10, 3, 15, 30), 15, 40));

        return emissions;
    }

    private Emission emission(Calendar startAt, int notifyBefore, int duration, boolean isFake)
    {
        return new Emission()
        {
            @Override
            public Calendar getStartTime()
            {
                return startAt;
            }

            @Override
            public int notifyBefore()
            {
                return notifyBefore;
            }

            @Override
            public int duration()
            {
                return duration;
            }

            @Override
            public boolean isFake()
            {
                return isFake;
            }
        };
    }

    private Emission emission(Calendar startAt, int notifyBefore, int duration)
    {
        return emission(startAt, notifyBefore, duration, false);
    }

//    private Emission emission(int afterMins, int notifyBefore, int duration)
//    {
//        return new Emission()
//        {
//            @Override
//            public Calendar getStartTime()
//            {
//                return afterMins(afterMins);
//            }
//
//            @Override
//            public int notifyBefore()
//            {
//                return notifyBefore;
//            }
//
//            @Override
//            public int duration()
//            {
//                return duration;
//            }
//
//            @Override
//            public boolean isFake()
//            {
//
//            }
//        };
//    }

    private Calendar afterMins(int mins)
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + mins);
        return c;
    }

    private Calendar at(int month, int day, int hour, int min)
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, min);
        c.set(Calendar.SECOND, 0);
        return c;
    }
}
