var status = 0;
//��ѡ���װ���б�
var selectedList = Array();
//ɸѡ��ı���װ���б�
var newItemList = Array();
var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var itemBorder = "#fUI/UIWindow.img/Item/activeIcon#";
var itemMaster = "#fUI/UIWindow.img/Item/bossPetIcon#"
var itemIcon = "#fUI/Basic.img/Cursor/0/0#";
var numArr = Array("#fUI/Basic.img/LevelNo/0#","#fUI/Basic.img/LevelNo/1#","#fUI/Basic.img/LevelNo/2#","#fUI/Basic.img/LevelNo/3#","#fUI/Basic.img/LevelNo/4#","#fUI/Basic.img/LevelNo/5#","#fUI/Basic.img/LevelNo/6#","#fUI/Basic.img/LevelNo/7#","#fUI/Basic.img/LevelNo/8#","#fUI/Basic.img/LevelNo/9#");
var btnOk_disabled="#fUI/Basic.img/BtYes2/disabled/0#";
var btnOk="#fUI/Basic.img/BtYes2/mouseOver/0#";
var startIcon = "#fUI/Basic.img/icon/arrow#";
var ii =Packages.server.MapleItemInformationProvider.getInstance();
var itemstr,itemdex,itemluk,itemint,itemhp,itemmp,itemwatk,itemmatk,itemwdef,itemmdef,itemavoid,itemacc,itemjump,itemspeed,itemcishu,owner;
//װ����˳��
var selectedPosition = 0;
//���λ
var step = 0;
var successRate = 100;
//����
var mose =5000000,mose2=5000000;//��� 
var cost = 1000;//���
var Itemcost=4310148;//�۳�����
var ��֮�ҳɹ��ʼӳ�=25;//�������֮��ͷ�����ӳɹ���
var ItemPosition=0;
var haveLuck = false;
var useLuck = false;
var sflag = false;
var listitem=Array(
1102184, //��ʹ֮����
1102349,//���⾫����
1102604,//���֮��
1102074,//��������ĺ�����
1102096,//������ĳ��
1103029,//������

1912406,//Ԫ������
1902406,//Ԫ������
1912404,//Ԫ������
1902404,//Ԫ������
1912403,//Ԫ������
1902403,//Ԫ������
1912412,//Ԫ������
1902412,//Ԫ������
1912413,//Ԫ������
1902413,//Ԫ������
1912416,//Ԫ������
1902416,//Ԫ������
1912417,//Ԫ������
1902417,//Ԫ������
1912420,//Ԫ������
1902420,//Ԫ������
1902409,//Ԫ������
1912409,//Ԫ������

1103029//ռλ������
);
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
		if (haveLuck && mode == 0) {
			useLuck = false;
			status=0;
			mode = 1;
		} else if (haveLuck && mode == 1) {
			useLuck = true;
		}
        if (mode == 0 && status == 0) {
			cm.dispose();
            return;
        }
		if (mode == 0 && status == -1) {
			cm.dispose();
            return;
        }
		//���ӵ�лƽ��㣬���ҵ��˷�
		
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
			if (step==1) {
				//�����װ��
				if (selectedPosition == 0){
					selectedList.splice(0,4);
				ItemPosition=0;}
				//���뵽��ѡװ���б�
				if (selection!=-1)
					selectedList[selectedPosition] = Array(selection, newItemList[selection]);
				//���ñ��
				step=0;

			}
			var text = "#e��\t\t      �� ʱװ����ת�� ��  \t\t\t��#n\r\n\r\n";
			for (var i=0; i<3; i++) {
				if (selectedList[i]!=null)
					text+="       #L"+i+"##v"+selectedList[i][1]+"##l";
				else
					if (i==0)
						text+="      #L"+i+"#"+itemMaster+"#l";
					else
						text+="       #L"+i+"#"+itemBorder+"#l";
			}
			
			text += "#e\r\n\r\n\r\n��\t\t\t\t\t\t\t\t\t\t\t��#n";
			//��ʾ�Ѿ�ѡ���װ����Ϣ
			if (selectedList.length >= 1) {
				text += "#k\r\n#e��\t\t     �� �Ѿ�ѡ���װ����Ϣ ��   \t\t��#n\r\n\r\n";
				for(var key in selectedList) {
					if(key==2)
					{break;}
					var item = cm.getInventory(1).getItem(selectedList[key][0]);
					var itemyyuanshushux = cm.getEquip(item.getItemId()).copy();
				    owner=item.getOwner();
					var flag=0;
					var itemSeq = "���뵽��װ��";
					if (key>=1)
						itemSeq = "#k��ȡ���Ե�װ��";
					var itemLevel = item.getLevel();
					var itemLevelStr = "";
				    if (itemLevel != 0)
					itemLevelStr = " (+"+itemLevel+")";
					itemstr=item.getStr();
                    itemdex=item.getDex();
                    itemluk=item.getLuk();
                    itemint=item.getInt();
                    itemhp=item.getHp();
                    itemmp=item.getMp();
                    itemwatk=item.getWatk();
                    itemmatk=item.getMatk();
                    itemwdef=item.getWdef();
                    itemmdef=item.getMdef();
                    itemavoid=item.getAvoid();
                    itemacc=item.getAcc();
                    itemjump=item.getJump();
                    itemspeed=item.getSpeed();
					text+="\t"+itemSeq+": #r["+owner+"]#n Lv."+ii.getReqLevel(item.getItemId())+" #d#e"+cm.getItemName(item.getItemId())+"#n"+itemLevelStr+"\r\n";
					text+="\tװ�����ԣ�����:"+item.getStr()+"#r+("+itemstr+")#d ����:"+item.getDex()+"#r+("+itemdex+")#d\r\n";
					text+="\tװ�����ԣ�����:"+item.getInt()+"#r+("+itemint+")#d ����:"+item.getLuk()+"#r+("+itemluk+")#d\r\n";
					text+="\tװ�����ԣ�����:"+item.getWatk()+"#r+("+itemwatk+")#d ħ��:"+item.getMatk()+"#r+("+itemmatk+")#d\r\n";
					text+="\tװ�����ԣ�HP  :"+item.getHp()+"#r+("+itemhp+")#d MP  :"+item.getMp()+"#r+("+itemmp+")#d\r\n";
					text+="\tװ�����ԣ�������:"+item.getWdef()+"#r+("+itemwdef+")#d ħ��������:"+item.getMdef()+"#r+("+itemmdef+")#d\r\n";
					text+="\tװ�����ԣ��ر���:"+item.getAvoid()+"#r+("+itemavoid+")#d ������:"+item.getAcc()+"#r+("+itemacc+")#d\r\n";
					text+="\tװ�����ԣ���Ծ��:"+item.getJump()+"#r+("+itemjump+")#d �ƶ��ٶ�:"+item.getSpeed()+"#r+("+itemspeed+")#d\r\n\r\n";

				}
				if (key>=1) {
					text+="#r            ���뵽��װ�������ԣ�#d\r\n";
					text+="\t��������ԣ�����:#r("+itemstr+")#d ����:#r("+itemdex+")#d\r\n";
					text+="\t��������ԣ�����:#r("+itemint+")#d ����:#r("+itemluk+")#d\r\n";
					text+="\t��������ԣ�����:#r("+itemwatk+")#d ħ��:#r("+itemmatk+")#d\r\n";
					text+="\t��������ԣ�HP  :#r("+itemhp+")#d MP  :#r("+itemmp+")#d\r\n";
					text+="\t��������ԣ�������:#r("+itemwdef+")#d ħ��������:#r("+itemmdef+")#d\r\n";
					text+="\t��������ԣ��ر���:#r("+itemavoid+")#d ������:#r("+itemacc+")#d\r\n";
				    text+="\t��������ԣ���Ծ��:#r("+itemjump+")#d �ƶ��ٶ�:#r("+itemspeed+")#d\r\n\r\n";
				}
				text += "#e\r\n��\t\t\t\t\t\t\t\t\t\t\t��#n";
			}
			var Surate=getSuccessRate(0),Surate1=getSuccessRate(1);//��ȡ�ɹ���
			if(ItemPosition!=0)
			{
				Surate+=��֮�ҳɹ��ʼӳ�;
				Surate1+=��֮�ҳɹ��ʼӳ�;
			}
			//��ʾ�����ĺϳɳɹ����Լ�����Ҫ�ķ���
			text += "\r\n#L990##b"+startIcon+" �̳гɹ��ʣ�#r"+Surate+"#b% ������ã�#r500��#b���#l\r\n";
			text += "\r\n#L991##b"+startIcon+" �̳гɹ��ʣ�#r"+Surate1+"#b% ������ã�#r500��#b��� #r1000#b���#l\r\n";
			//��ʾȷ����ť
			//var lastBtn = btnOk_disabled;
			//if (selectedList.length >= 2)  {
			//	lastBtn = btnOk;
			//}
			//text += "#k\t\t\t\t#L999##d#e"+lastBtn+"#l\r\n\r\n";
			//��������
			text += "#k\r\n#e��\t\t\t     �� �������� ��   \t\t\t��#n\r\n";
			text += "\t#b"+numArr[1]+" �̳�ǰ��������ϸ�Ķ��ϳ�˵����\r\n\t"+numArr[2]+" ��һ��λ��ѡ����ҪҪ�̳е���װ����\r\n\t#r"+numArr[3]+" �����װ���䶯����ȡ���Ե�װ����Ҫ����ѡ��\r\n\t"+numArr[4]+" ѡ��װ��ʱ��װ��������˳�������ݱ������˳��\r\n\t"+numArr[5]+" ѡ������󣬵����ȷ�ϡ�����װ���̳С�\r\n\t"+numArr[6]+" ������λ�������֮�ң���߿��Դﵽ�ٷ�֮�ٳɹ�#k";
			text += "#e\r\n��\t\t\t\t\t\t\t\t\t\t\t��#n";
			cm.sendSimple(text);
		} else if (status==1){
			var Surate=getSuccessRate(0),Surate1=getSuccessRate(1);//��ȡ�ɹ���
			//װ���ϳ��߼�����
			//if (sflag)
				//selection=910;
			if (selection >= 990) {
				sflag=true;
				var numberno=2;
				if(ItemPosition!=0){numberno+=1;}
				if (selectedList.length < numberno||selectedList[1]==null) {
					cm.sendPrev("�޷��̳У��������ȷ��װ��");
					cm.dispose();
				} else {
					if (ItemPosition==999 && !haveLuck) {
						status=0;
						haveLuck = true;
						cm.sendYesNo("���ı�����ӵ��#v4000517##b�ƽ���#k���ߣ��Ƿ�ʹ��#b�ƽ���#k���ɹ���������#b#e"+(successRate+�̳�ʯ�ɹ��ʼӳ�)+"%#n#k��\r\n\r\n#d#eѡ�������#r"+successRate+"%#d�ĳɹ��ʼ���ǿ����#n#k");
					} else {
						if(selection==990){
						if (cm.getPlayer().getMeso()<mose) {
							cm.sendOk("���Ľ�Ҳ���!");
							cm.dispose();
							return;
						}
						//����ɹ���
				        successRate =Surate;
						}
						else if(selection==991)
						{
							if (cm.getPlayer().getMeso()<mose2||cm.getPlayer().getCSPoints(1)<cost) {
							cm.sendOk("���Ľ�Ҳ�����ߵ����!");
							cm.dispose();
							return;
						}
						successRate =Surate1;
						}
						//��װ����Ϣ
						var masterItemId = selectedList[0][1];
						var masterItemPosition = selectedList[0][0];
						var masterItemReqLevel = ii.getReqLevel(masterItemId);
						//item = cm.getEquip(1302000).copy();
						if(selection=990)//�۳�����
						{cm.getPlayer().gainMeso(-mose,true);}
						else if(selection==991){
						cm.getPlayer().gainMeso(-mose2,true);
						cm.getPlayer().modifyCSPoints(1, -cost,true);
						}
						var item = cm.getInventory(1).getItem(masterItemPosition);
						//var ii = cm.getItemInfo();
						var toDrop = item.copy();
						
						
						//�ϳ�ʧ��
						
						//Math.random()*100ȡ0-100�������   Math.floorȡһ��������1.2  ��ȡ1
						var chance = Math.floor(Math.random()*100);
						
						/*a ? b : c ����˼�ǣ���� a ��ֵ�ж�Ϊ true������ʽ���� b ��ֵ�����򣬷��� c ��ֵ��
						a && b || c ���߼����㣬�����������ʽ��ֵ��ֻ���������Ż��ĽǶȣ����ǰ��ļ����Ѿ�ȷ���˽�����ͻ���ǰ��ֹ����ʱ���صĿ������м�ĳ�����ʽ��ֵ��
						���Ե� a = 1, b = 0, c = 1 ��ʱ�򣬾ͻ᷵�� c ��ֵ����ʱ������Ķ�Ԫ����������ͬ��*/
						successRate = (useLuck) ? successRate+��֮�ҳɹ��ʼӳ� : successRate;

						
						if (ItemPosition!=0) {
							cm.gainItem(Itemcost, -1);//�۳����� 
						}
						if (chance > successRate) {
							var indexof = 0;
							for(var key in selectedList) {
								if (key==0)
									continue;
								var breakRate = Math.floor(Math.random()*99);//���0-99
								if (breakRate <= 49) {
									indexof++;
									cm.removeSlot(1, selectedList[key][0], 1);
								}
							}
							var text = "���ҵ��ǣ���װ��������~����Ŭ���ɣ�";
							if (indexof > 0)  //����{}��ʾ ����ֻ��һ������if��䷶Χ�ڵ����
								text = "#r"+indexof+"#k����װ����ʧ�ˣ�����ģ���������ӻ����أ�";
							
							toDrop.setOwner(owner);
							cm.removeSlot(1, selectedList[0][0], 1)
							Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), toDrop, false);
							cm.sendOk("�治�ң��ϳ�ʧ���ˡ�"+text);
							cm.dispose();
							return;
						}
						//�ϳɳɹ�����
						
						//����װ������
						
						toDrop.setOwner(owner);
						toDrop.setStr(itemstr);
						toDrop.setDex(itemdex);
						toDrop.setInt(itemint);
						toDrop.setLuk(itemluk);
						toDrop.setWatk(itemwatk);
						toDrop.setMatk(itemmatk);
						toDrop.setHp(itemhp);
						toDrop.setMp(itemmp);
						toDrop.setWdef(itemwdef);
						toDrop.setMdef(itemmdef);
						toDrop.setAvoid(itemavoid);
						toDrop.setAcc(itemacc);
						toDrop.setJump(itemjump);
						toDrop.setSpeed(itemspeed);
						for(var key in selectedList) {
							cm.removeSlot(1, selectedList[key][0], 1)
						}
						Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), toDrop, false);
						var         text = "������" + itemstr + "\r\n" ;
                                    text += "���ݣ�" + itemdex + "\r\n" ;
                                    text += "������" + itemluk + "\r\n" ;
                                    text += "������" + itemint + "\r\n" ;
                                    text += "������" + itemwatk + "\r\n" ;
                                    text += "ħ����" + itemmatk + "\r\n" ;
		                            text += "HP��" + itemhp + "\r\n" ;
                                    text += "MP��" + itemmp + "\r\n" ;
                                    text += "��������" + itemwdef + "\r\n" ;
                                    text += "ħ����������" + itemmdef + "\r\n" ;
                                    text += "�ر��ʣ�" + itemavoid + "\r\n" ;
                                    text += "�����ʣ�" + itemacc + "\r\n" ;
                                    text += "��Ծ����" + itemjump + "\r\n" ;
		                            text += "�ƶ��ٶȣ�" + itemspeed + "\r\n" ;
						cm.sendOk("#r#e�̳� �ɹ���#n#k���μ̳�Ϊ����װ��#d[#v"+masterItemId+"#]#k������\r\n#k"+text);
						sflag=false;
						//if (nextGrade>3)
							//cm.worldMessageItem("[װ������] : " + "��ϲ[" + cm.getPlayer().getName() + "]�ϳɳ� " + grade[nextGrade] + "�� "+cm.getItemName(masterItemId), toDrop);
							//cm.worldSpouseMessage(0x15, "[װ���ϳ�] : ��ϲ " + cm.getChar().getName() + " �ϳɳ� " + grade[nextGrade] + "�� "+cm.getItemName(masterItemId));
						cm.dispose();
					}
				}
			   } else {
				//ѡ��װ������
				selectedPosition = selection;
				if (selectedPosition!=0 && selectedList[0]==null) {
					cm.sendPrev("����ѡ����װ����");
					//cm.dispose();
					//return;
				} 
				else if(selectedPosition==2)
				{
					var Newlistnow=0,newListId=0;
					inventoryType = 4;
					var list = cm.getInventory(inventoryType).list();
					var itemList = list.iterator();
					if(!cm.haveItem(Itemcost,1))//�ж��Ƿ�����֮��
					{
					text = "#e#r�����û����֮��,�޷������֮�Ҽӳ�#k#n\r\n\r\n#b";
					}else{
					text = "#e��ѡ��#r��֮��#n\r\n\r\n#b";
					while (itemList.hasNext()) {
					var item = itemList.next();
					if(item.getItemId()==Itemcost)
					{
						ItemPosition=item.getPosition();
						newItemList[item.getPosition()]=item.getItemId();
						break;
					}
					}
					//+="    #L" + key + "##v" + Itemcost + "#";
					}
					for(var key in newItemList) {
						if(newItemList[key]==Itemcost){
						text += "#L" + key + "##v" + newItemList[key] + "#";
						}
					}
					step=1;
                    status = -1;
					cm.sendSimple(text);
				}
				else {
					inventoryType = 1;
					
					var list = cm.getInventory(inventoryType).list();
					var itemList = list.iterator();
					text = "#e��ѡ��#r����ȡ���Եĸ�װ��#n\r\n\r\n#b";
					if (selectedPosition==0) {
						text="#e#d��ѡ����Ҫ����ǿ���ϳɵ�#r��װ����#n\r\n\r\n#b";
					}
					var indexof = 7;
					newItemList = Array();
					while (itemList.hasNext()) {
						var item = itemList.next();
						
						//������ѡװ��
						var flag=0;
						for(var key in selectedList) {
							if (item.getPosition() == selectedList[key][0])
							{
								flag = 1;
								break;
							}
						}
						if (flag==1)
							continue;
						if(ii.isCash(item.getItemId())==false)continue;
						if(item.getExpiration() != -1)continue;//�����  ����ʱ�Ĳ���ʾ
						//if(item.getFlag()==1)continue;//�����  �������Ĳ���ʾ
						if(listitem.indexOf(item.getItemId())!=-1){continue;}
						newItemList[item.getPosition()]=item.getItemId();
					}
					var noindex=0;
					for(var key in newItemList) {
						text += "#L" + key + "##v" + newItemList[key] + "#";
						noindex+=key;
						if (indexof > 1 && indexof % 5 == 0) {
							text += "\r\n";
						}
						indexof++;
					}
					if(noindex==0)
					{
						status = -1;
						cm.sendSimple("��ʱû�пɽ���ǿ���ϳɵ���סװ����");
					}
					else
					{
					//cm.sendPrev("��ʱû�пɽ���ǿ���ϳɵ���סװ����"+indexof);
					status = -1;
					step=1;
					if (newItemList.length < 0) {
						text = "#rû�п��Խ��кϳɵĸ�װ��#k"
						cm.sendSimple(text);
						cm.dispose();
						return;
					}
					cm.sendSimple(text);
					}
				}
			}
		}
    }
}

//����ɹ���
function getSuccessRate(count) {
	
	switch(count) {
		case 0:
			return 30;
		case 1:
			return 75;
		case 2:
			return 100;
		default:
			return 30;
	}
}


