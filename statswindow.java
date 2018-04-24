import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class statswindow extends JFrame{
  public GridBagLayout gridbag;
  public JLabel dayNum,foodNum,hpNum,pis,auto,ammoNum,medicineNum,bandageNum,valuablesNum,MoralNum,sickNum,hungryNum,FR,Inj,SID,SiFer,BG;
  public JPanel statsContent;
  public Final C;
  public statswindow(Final input){
    this.C=input;
    this.setTitle("Player Stats");
    statsContent=new JPanel();
    gridbag = new GridBagLayout();
    this.setLayout(gridbag);
    String temp="Day No."+C.getDay();
    dayNum=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(dayNum, new GridBagConstraints(0, 0, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(20, 20, 20, 20),0, 0));

    temp="Number of units of food you own:"+C.getFood();
    foodNum=new JLabel(temp,JLabel.CENTER);

    temp="Your HP:"+C.getHp();
    hpNum=new JLabel(temp,JLabel.CENTER);

    if(C.getPistol()){
      temp="You have a pistol.";
    }else{
      temp="You don't own a pistol.";
    }
    pis=new JLabel(temp,JLabel.CENTER);

    if(C.getAutomaticRifle()){
      temp="You have an automatic rifle.";
    }else{
      temp="You don't own an automatic rifle.";
    }
    auto=new JLabel(temp,JLabel.CENTER);

    temp="You have "+C.getAmmo()+" bullets.";
    ammoNum=new JLabel(temp,JLabel.CENTER);

    temp="Medicine: "+C.getMedicine();
    medicineNum=new JLabel(temp,JLabel.CENTER);

    temp="Bandage: "+C.getBandage();
    bandageNum=new JLabel(temp,JLabel.CENTER);

    temp="Valuables: "+C.getValuables();
    valuablesNum=new JLabel(temp,JLabel.CENTER);

    temp="Moral value:"+C.getMoral();
    MoralNum=new JLabel(temp,JLabel.CENTER);

    if(C.getSick()){
      temp="You are sick.";
    }else{
      temp="You are not sick.";
    }
    sickNum=new JLabel(temp,JLabel.CENTER);

    temp="Fullness meter:"+C.getHungry();
    hungryNum=new JLabel(temp,JLabel.CENTER);

    if(C.getFastRecover()){
      temp="You have bandaged yourself.";
    }else{
      temp="";
    }
    FR=new JLabel(temp,JLabel.CENTER);

    if(C.getInjured()){
      temp="You are injured";
    }else{
      temp="You are not injured";
    }
    Inj=new JLabel(temp,JLabel.CENTER);

    if(C.getSpyID()){
      temp="You have a spy ID.";
    }else{
      temp="You don't have a spy ID.";
    }
    SID=new JLabel(temp,JLabel.CENTER);

    if(C.getCipher()){
      temp="You know the rebel cipher.";
    }else{
      temp="You don't know the rebel cipher.";
    }
    SiFer=new JLabel(temp,JLabel.CENTER);

    if(C.getB()){
      temp="You have the rebel badge.";
    }else{
      temp="You don't have the rebel badge.";
    }
    BG=new JLabel(temp,JLabel.CENTER);

    statsContent.add(dayNum);
    statsContent.add(foodNum);
    statsContent.add(hpNum);
    statsContent.add(pis);
    statsContent.add(auto);
    statsContent.add(ammoNum);
    statsContent.add(medicineNum);
    statsContent.add(bandageNum);
    statsContent.add(valuablesNum);
    statsContent.add(MoralNum);
    statsContent.add(sickNum);
    statsContent.add(hungryNum);
    statsContent.add(FR);
    statsContent.add(Inj);
    statsContent.add(SID);
    statsContent.add(SiFer);
    statsContent.add(BG);
    this.setContentPane(statsContent);
    this.setLocation(0,0);
    this.setSize(400,400);
    this.setVisible(true);
  }

  public void update(){
    String temp="Day No."+C.getDay();
    this.dayNum.setText(temp);
    temp="Number of units of food you own:"+C.getFood();
    this.foodNum.setText(temp);

    temp="Your HP:"+C.getHp();
    this.hpNum.setText(temp);

    if(C.getPistol()){
      temp="You have a pistol.";
    }else{
      temp="You don't own a pistol.";
    }
    this.pis.setText(temp);

    if(C.getAutomaticRifle()){
      temp="You have an automatic rifle.";
    }else{
      temp="You don't own an automatic rifle.";
    }
    this.auto.setText(temp);

    temp="You have "+C.getAmmo()+" bullets.";
    this.ammoNum.setText(temp);

    temp="Medicine: "+C.getMedicine();
    this.medicineNum.setText(temp);

    temp="Bandage: "+C.getBandage();
    this.bandageNum.setText(temp);

    temp="Valuables: "+C.getValuables();
    this.valuablesNum.setText(temp);

    temp="Moral value:"+C.getMoral();
    this.MoralNum.setText(temp);

    if(C.getSick()){
      temp="You are sick.";
    }else{
      temp="You are not sick.";
    }
    this.sickNum.setText(temp);

    temp="Fullness meter:"+C.getHungry();
    this.hungryNum.setText(temp);

    if(C.getFastRecover()){
      temp="You have bandaged yourself.";
    }else{
      temp="";
    }
    this.FR.setText(temp);

    if(C.getInjured()){
      temp="You are injured";
    }else{
      temp="You are not injured";
    }
    this.Inj.setText(temp);

    if(C.getSpyID()){
      temp="You have a spy ID.";
    }else{
      temp="You don't have a spy ID.";
    }
    this.SID.setText(temp);

    if(C.getCipher()){
      temp="You know the rebel cipher.";
    }else{
      temp="You don't know the rebel cipher.";
    }
    this.SiFer.setText(temp);

    if(C.getB()){
      temp="You have the rebel badge.";
    }else{
      temp="You don't have the rebel badge.";
    }
    this.BG.setText(temp);
  }
}
