package com.jtcode.sports;


import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static ArrayList<Sport> sportList= new ArrayList<>();

    private static Repository ourInstance = new Repository();

    public static Repository getInstance() {
        return ourInstance;
    }

    private Repository() {
        addTestData();
    }
    private void addTestData(){
        sportList.add(new Sport("Baloncesto",R.drawable.icon_basketball,false));
        sportList.add(new Sport("Futbol",R.drawable.icon_soccer,false));
        sportList.add(new Sport("Futbol Americano",R.drawable.icon_american_football,false));
        sportList.add(new Sport("Beisbol",R.drawable.icon_baseball,false));
        sportList.add(new Sport("Motor",R.drawable.icon_motor,false));
        sportList.add(new Sport("Ciclismo",R.drawable.icon_cycling,false));
        sportList.add(new Sport("Golf",R.drawable.icon_golf,false));
        sportList.add(new Sport("Bandy",R.drawable.icon_bandy,false));
        sportList.add(new Sport("Beach Soccer",R.drawable.icon_beach_soccer,false));
        sportList.add(new Sport("Cricket",R.drawable.icon_cricket,false));
        sportList.add(new Sport("Boxeo",R.drawable.icon_boxing,false));
        sportList.add(new Sport("Voleibol",R.drawable.icon_volleyball,false));
        sportList.add(new Sport("Ajedrez",R.drawable.icon_chess,false));
    }
    public List<Sport> getAllSports(){
        return sportList;
    }
    /**
     * Method for get the sport who start with the character selected
     * @param character the first character of the sport's name
     * */
    public List<Sport> getByChar(char character){
        List<Sport> selectSport= new ArrayList<Sport>();
        for(Sport s : sportList){
            if(s.getName().indexOf(0)==character){
                selectSport.add(s);
            }
        }
        return selectSport;
    }

}
