package com.sqli.train;

import java.util.Arrays;

public class Train {
	
	Wagon[] wagan_tabl;
	String[] wagonRep;
	
	public Train(String Input){
		String[] listWagons = Input.split("");
		wagan_tabl = new Wagon[listWagons.length];
		wagonRep = new String[listWagons.length];
		for(int i=0;i<listWagons.length; i++){
			wagan_tabl[i] = new Wagon(listWagons[i]);
		}
	}
	
	public String print(){
		String rep = "";
		String repResult = "";
		for(int i=0; i<wagan_tabl.length; i++){
			if(wagan_tabl[i].getWagon().equals("H") && i==0){	
				rep = "<HHHH";
				wagonRep[i] ="<HHHH";
			}
			else if(wagan_tabl[i].getWagon().equals("H") && i!=0){
				rep = "HHHH>";
				wagonRep[i] ="HHHH>";
			}
			else if(wagan_tabl[i].getWagon().equals("P")){
				rep = "|OOOO|";
				wagonRep[i] ="|OOOO|";
			}
            else if(wagan_tabl[i].getWagon().equals("R")){
            	rep = "|hThT|";
            	wagonRep[i] ="|hThT|";
			}
            else if(wagan_tabl[i].getWagon().equals("C") && !fill()){
            	rep = "|____|";
            	wagonRep[i] = "|____|";
            }
            else if(wagan_tabl[i].getWagon().equals("C") && fill()){
            	rep = "|^^^^|";
            }
			if(i == wagan_tabl.length -1){
				repResult +=rep;
			}
			else{
				repResult += rep+"::";
			}
		}
		return repResult;
	}

	public void detachEnd(){
		Wagon[] detachWagon = new Wagon[wagan_tabl.length-1];
		for(int i=0;i<wagan_tabl.length-1;i++){
			detachWagon[i] = wagan_tabl[i];
		}
		wagan_tabl = detachWagon;
	}

	public void detachHead(){
		Wagon[] detachWagon = new Wagon[wagan_tabl.length-1];
		for(int i=0;i<wagan_tabl.length-1;i++){
			detachWagon[i] = wagan_tabl[i+1];
		}
		wagan_tabl = detachWagon;
	}

	public boolean fill(){
        for(int i=0;i<wagan_tabl.length;i++){
        	if(wagan_tabl[i].getWagon().equals("C") && wagonRep[i].equals("|____|")){
        		wagonRep[i] = "|^^^^|";
        		return true;
        	}
        }
        return false;
	}
}
