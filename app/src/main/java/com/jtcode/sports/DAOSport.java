package com.jtcode.sports;


import java.util.ArrayList;
import java.util.List;

public class DAOSport {
    
    private static List<Sport> listaDep;
    private static DAOSport ourInstance = new DAOSport();

    public static DAOSport getInstance() {
        return ourInstance;
    }

    private DAOSport() {
        listaDep=new ArrayList<Sport>();
        listaDep.add(new Sport("Baloncesto",R.drawable.icon_basketball,false));
        listaDep.add(new Sport("Futbol",R.drawable.icon_soccer,false));
        listaDep.add(new Sport("Futbol Americano",R.drawable.icon_american_football,false));
        listaDep.add(new Sport("Beisbol",R.drawable.icon_baseball,false));
        listaDep.add(new Sport("Motor",R.drawable.icon_motor,false));
        listaDep.add(new Sport("Ciclismo",R.drawable.icon_cycling,false));
        listaDep.add(new Sport("Golf",R.drawable.icon_golf,false));
        listaDep.add(new Sport("Bandy",R.drawable.icon_bandy,false));
        listaDep.add(new Sport("Beach Soccer",R.drawable.icon_beach_soccer,false));
        listaDep.add(new Sport("Cricket",R.drawable.icon_cricket,false));
        listaDep.add(new Sport("Boxeo",R.drawable.icon_boxing,false));
        listaDep.add(new Sport("Voleibol",R.drawable.icon_volleyball,false));
        listaDep.add(new Sport("Ajedrez",R.drawable.icon_chess,false));
    }
    public static List<Sport> getAll(){
        return listaDep;
    }

    public static ArrayList<Sport> getSportsByChar(char c){
        ArrayList<Sport> artemp=new ArrayList();
        for ( Sport s: listaDep ) {
            if(s.getName().toLowerCase().startsWith(String.valueOf(c).toLowerCase())){
                artemp.add(s);
            }
        }
        return artemp;
    }
}
