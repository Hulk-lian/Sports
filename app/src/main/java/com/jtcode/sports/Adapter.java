package com.jtcode.sports;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class Adapter extends ArrayAdapter<Sport>{

   private Context context;
    private ArrayList<Sport> localCopy;
    SharedPreferences sharedPreferences;

    public Adapter(Context context) {
        super(context,R.layout.item_sport,new ArrayList<Sport>(Repository.getInstance()));
        //super(context,R.layout.item_sport);
        this.context=context;
        this.localCopy=new ArrayList<>(Repository.getInstance());
       /* this.addAll(DAOSport.getAll());
        localCopy=new ArrayList<>();
        localCopy.addAll(DAOSport.getAll());*/
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
        readPrefs();
    }
    private void readPrefs(){
        Map<String,?> favSport = sharedPreferences.getAll();

        for (int i=0;i< localCopy.size();i++) {
            if(favSport.containsKey(localCopy.get(i).getName())){
                localCopy.get(i).setChecked(true);
            }
        }
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View item = convertView;
        SportHolder sportHolder=null;
        if(item==null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //LayoutInflater inflater=LayoutInflater.from(context);

            item = inflater.inflate(R.layout.item_sport, null);
            sportHolder=new SportHolder();

            sportHolder.txvName = (TextView) item.findViewById(R.id.txvSportName);
            sportHolder.imgSport = (ImageView) item.findViewById(R.id.imgSport);
            sportHolder.chbSelected = (CheckBox) item.findViewById(R.id.chbSelectSport);

            item.setTag(sportHolder);
        }
        else{
            sportHolder=(SportHolder)item.getTag();
        }
            sportHolder.txvName.setText(localCopy.get(position).getName());
            sportHolder.imgSport.setImageResource(localCopy.get(position).getImg());

        sportHolder.chbSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               localCopy.get(position).setChecked(b);
            }
        });

        sportHolder.chbSelected.setChecked(localCopy.get(position).isChecked());

        return item;
    }
    /**
     * Get only the sport who start with the character
     * @param character the first character in the name
     * */
    public void applyCharFilter(char character){
        localCopy.clear();
        localCopy.addAll(Repository.getSportsByChar(character));
        notifyDataSetChanged();
    }

    /**
     * Get all the sports in the repository
     * First clean all the sport and after charge all the sports
     * */
    public void reloadAllSports(){
        localCopy.clear();
        localCopy.addAll(Repository.getInstance());
        notifyDataSetChanged();
    }

    class SportHolder{
        TextView txvName;
        ImageView imgSport;
        CheckBox chbSelected;
    }
}
