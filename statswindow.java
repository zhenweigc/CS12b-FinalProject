import javax.swing.*;
import java.awt.*;

public class statswindow extends JFrame{
  private GridBagLayout gridbag,bag4im,bag4all;
  private JLabel dayNum,foodNum,hpNum,pis,auto,ammoNum,medicineNum,bandageNum,valuablesNum,MoralNum,sickNum,hungryNum,FR,Inj,SID,SiFer,BG;
  private JPanel infoContent,imageContent,infoIm;
  private JLabel i1,i2,i3,i4,i5,i6;
  private Final C;
  public statswindow(Final input){
    this.C=input;
    this.setTitle("Player Stats");

    //Overall layout setting
    infoContent=new JPanel();
    imageContent=new JPanel();
    infoIm=new JPanel();
    bag4all=new GridBagLayout();
    infoIm.setLayout(bag4all);
    bag4all.setConstraints(infoContent, new GridBagConstraints(GridBagConstraints.RELATIVE,0, 2,2, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));
    bag4all.setConstraints(imageContent, new GridBagConstraints(0,0, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));

    //Image setting
    bag4im=new GridBagLayout();
    imageContent.setLayout(bag4im);
    Icon icon=new ImageIcon("Pics/man.jpg");
    i1=new JLabel(icon);
    bag4im.setConstraints(i1,new GridBagConstraints(0,0, 2,3, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));

    icon=new ImageIcon("Pics/auto.jpg");
    i3=new JLabel(icon);
    i3.setVisible(false);
    bag4im.setConstraints(i3,new GridBagConstraints(0,1, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));

    icon=new ImageIcon("Pics/pistol.jpg");
    i2=new JLabel(icon);
    i2.setVisible(false);
    bag4im.setConstraints(i2,new GridBagConstraints(1,1, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));

    icon=new ImageIcon("Pics/spy.jpg");
    i4=new JLabel(icon);
    i4.setVisible(false);
    bag4im.setConstraints(i4,new GridBagConstraints(0,2, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));

    icon=new ImageIcon("Pics/cipher.jpg");
    i5=new JLabel(icon);
    i5.setVisible(false);
    bag4im.setConstraints(i5,new GridBagConstraints(1,2, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));

    icon=new ImageIcon("Pics/badge.jpg");
    i6=new JLabel(icon);
    i6.setVisible(false);
    bag4im.setConstraints(i6,new GridBagConstraints(2,2, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));
    imageContent.add(i1);
    imageContent.add(i2);
    imageContent.add(i3);
    imageContent.add(i4);
    imageContent.add(i5);
    imageContent.add(i6);

    //Info setting
    gridbag = new GridBagLayout();
    infoContent.setLayout(gridbag);
    String temp="Day No."+C.getDay();
    dayNum=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(dayNum, new GridBagConstraints(0, 0, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20, 10, 20),0, 0));

    temp="Number of units of food you own:"+C.getFood();
    foodNum=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(foodNum, new GridBagConstraints(1,0, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20,10, 20),0, 0));

    temp="Your HP:"+C.getHp();
    hpNum=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(hpNum, new GridBagConstraints(0,1, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20,10, 20),0, 0));

    temp="Medicine: "+C.getMedicine();
    medicineNum=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(medicineNum, new GridBagConstraints(1,1, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20,10, 20),0, 0));

    temp="Bandage: "+C.getBandage();
    bandageNum=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(bandageNum, new GridBagConstraints(0,2, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20,10, 20),0, 0));

    temp="Valuables: "+C.getValuables();
    valuablesNum=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(valuablesNum, new GridBagConstraints(1,2, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20,10, 20),0, 0));

    temp="Moral value:"+C.getMoral();
    MoralNum=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(MoralNum, new GridBagConstraints(0,3, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20,10, 20),0, 0));

    if(C.getSick()){
      temp="You are sick.";
    }else{
      temp="You are not sick.";
    }
    sickNum=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(sickNum, new GridBagConstraints(1,3, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20,10, 20),0, 0));

    temp="Fullness meter:"+C.getHungry();
    hungryNum=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(hungryNum, new GridBagConstraints(0,4, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20,10, 20),0, 0));

    if(C.getFastRecover()){
      temp="You have bandaged yourself.";
    }else{
      temp="-------------------------";
    }
    FR=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(FR, new GridBagConstraints(1,4, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20,10, 20),0, 0));

    if(C.getInjured()){
      temp="You are injured";
    }else{
      temp="You are not injured";
    }
    Inj=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(Inj, new GridBagConstraints(0,5, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20,10, 20),0, 0));


    if(C.getPistol()){
      temp="You have a pistol.";
    }else{
      temp="You don't own a pistol.";
    }
    pis=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(pis, new GridBagConstraints(1,5, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20,10, 20),0, 0));


    if(C.getAutomaticRifle()){
      temp="You have an automatic rifle.";
    }else{
      temp="You don't own an automatic rifle.";
    }
    auto=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(auto, new GridBagConstraints(0,6, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20,10, 20),0, 0));

    temp="You have "+C.getAmmo()+" bullets.";
    ammoNum=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(ammoNum, new GridBagConstraints(1,6, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20, 10, 20),0, 0));

    if(C.getSpyID()){
      temp="You have a spy ID.";
    }else{
      temp="You don't have a spy ID.";
    }
    SID=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(SID, new GridBagConstraints(0,7, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20, 10, 20),0, 0));

    if(C.getCipher()){
      temp="You know the rebel cipher.";
    }else{
      temp="You don't know the rebel cipher.";
    }
    SiFer=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(SiFer, new GridBagConstraints(1,7, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20, 10, 20),0, 0));

    if(C.getB()){
      temp="You have the rebel badge.";
    }else{
      temp="You don't have the rebel badge.";
    }
    BG=new JLabel(temp,JLabel.CENTER);
    gridbag.setConstraints(BG, new GridBagConstraints(0,8, 1,1, 1.0, 1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(10, 20,10, 20),0, 0));

    infoContent.add(dayNum);
    infoContent.add(foodNum);
    infoContent.add(hpNum);
    infoContent.add(pis);
    infoContent.add(auto);
    infoContent.add(ammoNum);
    infoContent.add(medicineNum);
    infoContent.add(bandageNum);
    infoContent.add(valuablesNum);
    infoContent.add(MoralNum);
    infoContent.add(sickNum);
    infoContent.add(hungryNum);
    infoContent.add(FR);
    infoContent.add(Inj);
    infoContent.add(SID);
    infoContent.add(SiFer);
    infoContent.add(BG);

    infoIm.add(imageContent);
    infoIm.add(infoContent);
    this.setContentPane(infoIm);
    this.setLocation(0,0);
    this.setSize(900,600);
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
      i2.setVisible(true);
    }else{
      temp="You don't own a pistol.";
      i2.setVisible(false);
    }
    this.pis.setText(temp);

    if(C.getAutomaticRifle()){
      temp="You have an automatic rifle.";
      i3.setVisible(true);
    }else{
      temp="You don't own an automatic rifle.";
      i3.setVisible(false);
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
      temp="----------------------";
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
      i4.setVisible(true);
    }else{
      temp="You don't have a spy ID.";
      i4.setVisible(false);
    }
    this.SID.setText(temp);

    if(C.getCipher()){
      temp="You know the rebel cipher.";
      i5.setVisible(true);
    }else{
      temp="You don't know the rebel cipher.";
      i5.setVisible(false);
    }
    this.SiFer.setText(temp);

    if(C.getB()){
      temp="You have the rebel badge.";
      i6.setVisible(true);
    }else{
      temp="You don't have the rebel badge.";
      i6.setVisible(false);
    }
    this.BG.setText(temp);
  }
}
