var status = 0;
//被选择的装备列表
var selectedList = Array();
//筛选后的背包装备列表
var newItemList = Array();
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var itemBorder = "#fUI/UIWindow.img/Item/activeIcon#";
var itemMaster = "#fUI/UIWindow.img/Item/bossPetIcon#"
var itemIcon = "#fUI/Basic.img/Cursor/0/0#";
var numArr = Array("#fUI/Basic.img/LevelNo/0#","#fUI/Basic.img/LevelNo/1#","#fUI/Basic.img/LevelNo/2#","#fUI/Basic.img/LevelNo/3#","#fUI/Basic.img/LevelNo/4#","#fUI/Basic.img/LevelNo/5#","#fUI/Basic.img/LevelNo/6#","#fUI/Basic.img/LevelNo/7#","#fUI/Basic.img/LevelNo/8#","#fUI/Basic.img/LevelNo/9#");
var btnOk_disabled="#fUI/Basic.img/BtYes2/disabled/0#";
var btnOk="#fUI/Basic.img/BtYes2/mouseOver/0#";
var startIcon = "#fUI/Basic.img/icon/arrow#";
var ii =Packages.server.MapleItemInformationProvider.getInstance();
var itemstr,itemdex,itemluk,itemint,itemhp,itemmp,itemwatk,itemmatk,itemwdef,itemmdef,itemavoid,itemacc,itemjump,itemspeed,itemcishu,owner;
//装备槽顺序
var selectedPosition = 0;
//标记位
var step = 0;
var successRate = 100;
//费用
var mose =50000000,mose2=50000000;//金币 
var cost = 50000;//点卷
var Itemcost=4310148;//扣除材料
var 星之币成功率加成=25;//如果有星之币头就增加成功率
var ItemPosition=0;
var haveLuck = false;
var useLuck = false;
var sflag = false;
var listitem=Array(
1102184, //天使之翼翅膀
1102349,//发光精灵翅膀
1102604,//苍穹之翼
1102074,//闪闪发光的蝴蝶翼
1102096,//夏其耳的翅膀
1103029,//六介翅膀



1103029//占位，
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
		//如果拥有黄金鱼，并且点了否
		
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
			if (step==1) {
				//清除副装备
				if (selectedPosition == 0){
					selectedList.splice(0,4);
				ItemPosition=0;}
				//加入到被选装备列表
				if (selection!=-1)
					selectedList[selectedPosition] = Array(selection, newItemList[selection]);
				//重置标记
				step=0;

			}
			var text = "#e┌\t\t      ─ 时装属性转换 ─  \t\t\t┐#n\r\n\r\n";
			for (var i=0; i<3; i++) {
				if (selectedList[i]!=null)
					text+="       #L"+i+"##v"+selectedList[i][1]+"##l";
				else
					if (i==0)
						text+="      #L"+i+"#"+itemMaster+"#l";
					else
						text+="       #L"+i+"#"+itemBorder+"#l";
			}
			
			text += "#e\r\n\r\n\r\n└\t\t\t\t\t\t\t\t\t\t\t┘#n";
			//显示已经选择的装备信息
			if (selectedList.length >= 1) {
				text += "#k\r\n#e┌\t\t     ─ 已经选择的装备信息 ─   \t\t┐#n\r\n\r\n";
				for(var key in selectedList) {
					if(key==2)
					{break;}
					var item = cm.getInventory(1).getItem(selectedList[key][0]);
					var itemyyuanshushux = cm.getEquip(item.getItemId()).copy();
				    owner=item.getOwner();
					var flag=0;
					var itemSeq = "融入到的装备";
					if (key>=1)
						itemSeq = "#k提取属性的装备";
					var itemLevel = item.getLevel();
					var itemLevelStr = "";
				    if (itemLevel != 0)
					itemLevelStr = " (+"+itemLevel+")";
					itemstr=item.getStr()-itemyyuanshushux.getStr();
                    itemdex=item.getDex()-itemyyuanshushux.getDex();
                    itemluk=item.getLuk()-itemyyuanshushux.getLuk();
                    itemint=item.getInt()-itemyyuanshushux.getInt();
                    itemhp=item.getHp()-itemyyuanshushux.getHp();
                    itemmp=item.getMp()-itemyyuanshushux.getMp();
                    itemwatk=item.getWatk()-itemyyuanshushux.getWatk();
                    itemmatk=item.getMatk()-itemyyuanshushux.getMatk();
                    itemwdef=item.getWdef()-itemyyuanshushux.getWdef();
                    itemmdef=item.getMdef()-itemyyuanshushux.getMdef();
                    itemavoid=item.getAvoid()-itemyyuanshushux.getAvoid();
                    itemacc=item.getAcc()-itemyyuanshushux.getAcc();
                    itemjump=item.getJump()-itemyyuanshushux.getJump();
                    itemspeed=item.getSpeed()-itemyyuanshushux.getSpeed();
					text+="\t"+itemSeq+": #r["+owner+"]#n Lv."+ii.getReqLevel(item.getItemId())+" #d#e"+cm.getItemName(item.getItemId())+"#n"+itemLevelStr+"\r\n";
					text+="\t装备属性：力量:"+item.getStr()+"#r+("+itemstr+")#d 敏捷:"+item.getDex()+"#r+("+itemdex+")#d\r\n";
					text+="\t装备属性：智力:"+item.getInt()+"#r+("+itemint+")#d 运气:"+item.getLuk()+"#r+("+itemluk+")#d\r\n";
					text+="\t装备属性：攻击:"+item.getWatk()+"#r+("+itemwatk+")#d 魔攻:"+item.getMatk()+"#r+("+itemmatk+")#d\r\n";
					text+="\t装备属性：HP  :"+item.getHp()+"#r+("+itemhp+")#d MP  :"+item.getMp()+"#r+("+itemmp+")#d\r\n";
					text+="\t装备属性：防御力:"+item.getWdef()+"#r+("+itemwdef+")#d 魔法防御力:"+item.getMdef()+"#r+("+itemmdef+")#d\r\n";
					text+="\t装备属性：回避率:"+item.getAvoid()+"#r+("+itemavoid+")#d 命中率:"+item.getAcc()+"#r+("+itemacc+")#d\r\n";
					text+="\t装备属性：跳跃力:"+item.getJump()+"#r+("+itemjump+")#d 移动速度:"+item.getSpeed()+"#r+("+itemspeed+")#d\r\n\r\n";

				}
				if (key>=1) {
					text+="#r            融入到的装备的属性：#d\r\n";
					text+="\t融入的属性：力量:#r("+itemstr+")#d 敏捷:#r("+itemdex+")#d\r\n";
					text+="\t融入的属性：智力:#r("+itemint+")#d 运气:#r("+itemluk+")#d\r\n";
					text+="\t融入的属性：攻击:#r("+itemwatk+")#d 魔攻:#r("+itemmatk+")#d\r\n";
					text+="\t融入的属性：HP  :#r("+itemhp+")#d MP  :#r("+itemmp+")#d\r\n";
					text+="\t融入的属性：防御力:#r("+itemwdef+")#d 魔法防御力:#r("+itemmdef+")#d\r\n";
					text+="\t融入的属性：回避率:#r("+itemavoid+")#d 命中率:#r("+itemacc+")#d\r\n";
				    text+="\t融入的属性：跳跃力:#r("+itemjump+")#d 移动速度:#r("+itemspeed+")#d\r\n\r\n";
				}
				text += "#e\r\n└\t\t\t\t\t\t\t\t\t\t\t┘#n";
			}
			var Surate=getSuccessRate(0),Surate1=getSuccessRate(1);//获取成功率
			if(ItemPosition!=0)
			{
				Surate+=星之币成功率加成;
				Surate1+=星之币成功率加成;
			}
			//显示计算后的合成成功率以及所需要的费用
			text += "\r\n#L990##b"+startIcon+" 继承成功率：#r"+Surate+"#b% 所需费用：#r5000万#b金币#l\r\n";
			text += "\r\n#L991##b"+startIcon+" 继承成功率：#r"+Surate1+"#b% 所需费用：#r5000万#b金币 #r5万#b点卷#l\r\n";
			//显示确定按钮
			//var lastBtn = btnOk_disabled;
			//if (selectedList.length >= 2)  {
			//	lastBtn = btnOk;
			//}
			//text += "#k\t\t\t\t#L999##d#e"+lastBtn+"#l\r\n\r\n";
			//操作帮助
			text += "#k\r\n#e┌\t\t\t     ─ 操作帮助 ─   \t\t\t┐#n\r\n";
			text += "\t#b"+numArr[1]+" 继承前，请先仔细阅读合成说明。\r\n\t"+numArr[2]+" 第一个位置选择需要要继承的主装备。\r\n\t#r"+numArr[3]+" 如果主装备变动，提取属性的装备需要重新选择。\r\n\t"+numArr[4]+" 选择装备时，装备的排列顺序是依据背包里的顺序。\r\n\t"+numArr[5]+" 选择结束后，点击“确认”进行装备继承#k";
			text += "#e\r\n└\t\t\t\t\t\t\t\t\t\t\t┘#n";
			cm.sendSimple(text);
		} else if (status==1){
			//装备合成逻辑运算
			//if (sflag)
				//selection=910;
			if (selection >= 990) {
				sflag=true;
				var numberno=2;
				if(ItemPosition!=0){numberno+=1;}
				if (selectedList.length < numberno||selectedList[1]==null) {
					cm.sendPrev("无法继承，请放入正确的装备");
					cm.dispose();
				} else {
					if (ItemPosition==999 && !haveLuck) {
						status=0;
						haveLuck = true;
						cm.sendYesNo("您的背包中拥有#v4000517##b黄金鱼#k道具，是否使用#b黄金鱼#k将成功率提升至#b#e"+(successRate+继承石成功率加成)+"%#n#k？\r\n\r\n#d#e选择否则以#r"+successRate+"%#d的成功率继续强化。#n#k");
					} else {
						if(selection==990){
						if (cm.getPlayer().getMeso()<mose) {
							cm.sendOk("您的金币不足!");
							cm.dispose();
							return;
						}
						//计算成功率
				        successRate =Surate;
						}
						else if(selection==991)
						{
							if (cm.getPlayer().getMeso()<mose2||cm.getPlayer().getCSPoints(1)<cost) {
							cm.sendOk("您的金币不足或者点卷不足!");
							cm.dispose();
							return;
						}
						successRate =Surate1;
						}
						//主装备信息
						var masterItemId = selectedList[0][1];
						var masterItemPosition = selectedList[0][0];
						var masterItemReqLevel = ii.getReqLevel(masterItemId);
						//item = cm.getEquip(1302000).copy();
						if(selection=990)//扣除费用
						{cm.getPlayer().gainMeso(-mose,true);}
						else if(selection==991){
						cm.getPlayer().gainMeso(-mose2,true);
						cm.getPlayer().modifyCSPoints(1, -cost,true);
						}
						var item = cm.getInventory(1).getItem(masterItemPosition);
						//var ii = cm.getItemInfo();
						var toDrop = item.copy();
						//合成失败
						var chance = Math.floor(Math.random()*100);
						successRate = (useLuck) ? successRate+星之币成功率加成 : successRate;
						if (ItemPosition!=0) {
							cm.gainItem(Itemcost, -1);//扣除材料 
						}
						if (chance > successRate) {
							var indexof = 0;
							for(var key in selectedList) {
								if (key==0)
									continue;
								var breakRate = Math.floor(Math.random()*100);
								if (breakRate <= 50) {
									indexof++;
									cm.removeSlot(1, selectedList[key][0], 1);
								}
							}
							var text = "庆幸的是，副装备都还在~继续努力吧！";
							if (indexof > 0)
								text = "#r"+indexof+"#k件副装备消失了，别灰心，后面的日子还长呢！";
							
							toDrop.setOwner(owner);
							cm.removeSlot(1, selectedList[0][0], 1)
							Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), toDrop, false);
							cm.sendOk("真不幸，合成失败了。"+text);
							cm.dispose();
							return;
						}
						//合成成功部分
						
						//设置装备属性
						
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
						var         text = "力量：" + itemstr + "\r\n" ;
                                    text += "敏捷：" + itemdex + "\r\n" ;
                                    text += "运气：" + itemluk + "\r\n" ;
                                    text += "智力：" + itemint + "\r\n" ;
                                    text += "攻击：" + itemwatk + "\r\n" ;
                                    text += "魔攻：" + itemmatk + "\r\n" ;
		                            text += "HP：" + itemhp + "\r\n" ;
                                    text += "MP：" + itemmp + "\r\n" ;
                                    text += "防御力：" + itemwdef + "\r\n" ;
                                    text += "魔法防御力：" + itemmdef + "\r\n" ;
                                    text += "回避率：" + itemavoid + "\r\n" ;
                                    text += "命中率：" + itemacc + "\r\n" ;
                                    text += "跳跃力：" + itemjump + "\r\n" ;
		                            text += "移动速度：" + itemspeed + "\r\n" ;
						cm.sendOk("#r#e继承 成功！#n#k本次继承为您的装备#d[#v"+masterItemId+"#]#k提升了\r\n#k"+text);
						sflag=false;
						//if (nextGrade>3)
							//cm.worldMessageItem("[装备吞噬] : " + "恭喜[" + cm.getPlayer().getName() + "]合成出 " + grade[nextGrade] + "的 "+cm.getItemName(masterItemId), toDrop);
							//cm.worldSpouseMessage(0x15, "[装备合成] : 恭喜 " + cm.getChar().getName() + " 合成出 " + grade[nextGrade] + "的 "+cm.getItemName(masterItemId));
						cm.dispose();
					}
				}
			   } else {
				//选择装备过程
				selectedPosition = selection;
				if (selectedPosition!=0 && selectedList[0]==null) {
					cm.sendPrev("请先选择主装备！");
					//cm.dispose();
					//return;
				} 
				else if(selectedPosition==2)
				{
					var Newlistnow=0,newListId=0;
					inventoryType = 4;
					var list = cm.getInventory(inventoryType).list();
					var itemList = list.iterator();
					if(!cm.haveItem(Itemcost,1))//判断是否有星之币
					{
					text = "#e#r你好像没有星之币,无法获得星之币加成#k#n\r\n\r\n#b";
					}else{
					text = "#e请选择：#r星之币#n\r\n\r\n#b";
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
					text = "#e请选择：#r被提取属性的副装备#n\r\n\r\n#b";
					if (selectedPosition==0) {
						text="#e#d请选择需要进行强化合成的#r主装备：#n\r\n\r\n#b";
					}
					var indexof = 7;
					newItemList = Array();
					while (itemList.hasNext()) {
						var item = itemList.next();
						
						//过滤已选装备
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
						//if(item.getFlag()==1)continue;//加这个  就上锁的不显示
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
						cm.sendSimple("暂时没有可进行强化合成的新住装备！");
					}
					else
					{
					//cm.sendPrev("暂时没有可进行强化合成的新住装备！"+indexof);
					status = -1;
					step=1;
					if (newItemList.length < 0) {
						text = "#r没有可以进行合成的副装。#k"
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

//计算成功率
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


