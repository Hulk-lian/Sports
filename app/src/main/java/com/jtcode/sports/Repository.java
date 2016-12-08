package com.jtcode.sports;

import java.util.ArrayList;
import java.util.List;

public class Repository extends ArrayList<Sport>{

    private static Repository sportList;

    public static Repository getInstance() {
        if(sportList==null){
            sportList=new Repository();
        }
        return sportList;
    }

    private Repository() {
        addTestData();
    }
    private void addTestData(){
        add(new Sport("Baloncesto",R.drawable.icon_basketball,false));
        add(new Sport("Futbol",R.drawable.icon_soccer,false));
        add(new Sport("Futbol Americano",R.drawable.icon_american_football,false));
        add(new Sport("Beisbol",R.drawable.icon_baseball,false));
        add(new Sport("Motor",R.drawable.icon_motor,false));
        add(new Sport("Ciclismo",R.drawable.icon_cycling,false));
        add(new Sport("Golf",R.drawable.icon_golf,false));
        add(new Sport("Bandy",R.drawable.icon_bandy,false));
        add(new Sport("Beach Soccer",R.drawable.icon_beach_soccer,false));
        add(new Sport("Cricket",R.drawable.icon_cricket,false));
        add(new Sport("Boxeo",R.drawable.icon_boxing,false));
        add(new Sport("Voleibol",R.drawable.icon_volleyball,false));
        add(new Sport("Ajedrez",R.drawable.icon_chess,false));
    }

    public static ArrayList<Sport> getSportsByChar(char c){
        ArrayList<Sport> artemp=new ArrayList();
        for ( Sport s: sportList ) {
            if(s.getName().toLowerCase().startsWith(String.valueOf(c).toLowerCase())){
                artemp.add(s);
            }
        }
        return artemp;
    }
}
