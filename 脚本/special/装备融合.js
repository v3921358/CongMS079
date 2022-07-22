
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 水晶矿 = "#fReactor/9222000/0/0#";
var 黄晶矿 = "#fReactor/9222000/6/0#";
var 紫水晶矿 = "#fItem/Etc/0426/04260002/info/iconRaw#";  //
var 绿水晶矿 = "#fItem/Etc/0426/04260005/info/iconRaw#";  //
var z14 = "#fUI/CN_Chat/ChattingRoom/BtVolUp/0/normal/0#";//喇叭
var c1 = "#fUI/GuildMark/Mark/Animal/00002020/1#";//
var c2 = "#fUI/GuildMark/Mark/Animal/00002020/3#";//
var c3 = "#fUI/GuildMark/Mark/Animal/00002020/6#";//
var c4 = "#fUI/GuildMark/Mark/Animal/00002020/12#";//
var c5 = "#fUI/GuildMark/Mark/Animal/00002020/15#";//
var 增幅参数 = new Array(
new Array("<增幅1>",100,1),new Array("<增幅2>",95,2),
new Array("<增幅3>",90,3), new Array("<增幅4>",85,4),
new Array("<增幅5>",80,5), new Array("<增幅6>",80,6),
new Array("<增幅7>",75,7),new Array("<增幅8>",70,8),
new Array("<增幅9>",65,9),new Array("<增幅10>",60,10),
new Array("<增幅11>",55,15),new Array("<增幅12>",50,20),
new Array("<增幅13>",45,30),new Array("<增幅14>",40,40),
new Array("<增幅15>",35,50),new Array("<增幅16>",30,70),
new Array("<增幅17>",25,90),new Array("<增幅18>",15,120)
)
var 增幅成功率;
var 增幅属性;
var 增幅名称;
var 增幅等级;
var 需要点卷;
var 是否可转移 = true;
var xold11="";
var status = 0;
var fstype = 0;
//////////////////////////////////////////////////////////
function start() 
{
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) 
{
	if (mode == -1) 
	{
		cm.dispose();
	} 
	else 
	{
		if (status >= 0 && mode == 0) 
		{
			cm.dispose();
			return;
		}
		if (mode == 1)
		status++;
		else
		status--;
		if (status == 0) 
		{ 
		var ii = Packages.server.MapleItemInformationProvider.getInstance();
        var item = cm.getInventory(1).getItem(1);
        var statup = new java.util.ArrayList();
        var item1 = cm.getInventory(1).getItem(2);
		var text = ""
		text += "                   #e#d装备融合#n  \r\n\r\n"
		text += "装备融合说明:\r\n"
		text += "注意:必须是两件相同的装备才可融合\r\n"
		text += "#d1.请将材料装备放到装备栏#r第一格#d\r\n";
		text += "#d2.请将主装备放到装备栏#r第二格#d\r\n";
		text += "#d3.主装备会获得材料装备的10%属性\r\n";
		text += "#d4.第一格的材料装备会消失！\r\n"
		if (item == null) 
		{
            cm.sendOk("你装备栏第一格没放装备！请放入#r材料装备#k到背包第一格\r\n#r(请把材料装备放进背包第一格,主装备放在背包第二格)\r\n#k")
			cm.dispose();
			return;
        } 
		else
		{
			if (cm.isCash(cm.getInventory(1).getItem(1).getItemId())) 
			{
			cm.sendOk("你装备栏第一格是时装，无法进行属性继承！")
			cm.dispose();
			return;
			}
			else
			{
				var itemid = item.getItemId();
				var 装备 = cm.getInventory(1).getItem(1).getItemId();
				增幅等级 = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).getOwner();
				text += "当前第一格装备:#v"+装备+"##r[材料装备]#k#l\r\n"
				
			}
		}
		if (item1 == null) 
		{
            cm.sendOk("你装备栏第二格没放装备！请放入#r主装备#k到背包第二格\r\n#r(请把材料装备放进背包第一格,主装备放在背包第二格)\r\n#k")
			cm.dispose();
			return;
        }
		else
		{
			if (cm.isCash(cm.getInventory(1).getItem(2).getItemId())) 
			{
			cm.sendOk("你装备栏第二格是时装，无法进行属性继承！")
			cm.dispose();
			return;
			}
			else
			{
				var itemid1 = item1.getItemId();
				var 装备1 = cm.getInventory(1).getItem(2).getItemId();
				//cm.getInventory(1).getItem(2).getUpgradeSlots()
				text += "当前第二格装备:#v"+装备1+"##r[主要装备]  剩余可升级次数："+cm.getInventory(1).getItem(2).getUpgradeSlots()+"#k#l\r\n"
				
			}
		}
		var FOr确认 = false;
		for(var i=0;i<增幅参数.length;i++)
		{
			if(增幅等级 == 增幅参数[i][0])
			{
				需要点卷 = 10000000;
				text += "当前增幅转移需要点卷:#r"+需要点卷+"#d"
				FOr确认 = true;
				是否可转移 = true;
			}
			else
			{
				if(FOr确认 == false)
				{
					是否可转移 = false;
				}
			}
		}
		cm.sendSimple (text);    

	//================================================================================================================================================	
	}
	else if (status == 1) 
	{
		if(是否可转移 == true)
		{
			cm.sendOk("#d#e冒险岛提示#n当前不符合转移条件,请检查！");
            cm.dispose();
			return;
		}
		
		if(cm.getInventory(1).getItem(2).getUpgradeSlots() <= 0){
			cm.sendOk("你的主装备#r[可升级次数为 0 ]#k，无法合成装备！");
			cm.dispose();
			return;
			}
			
		if(cm.getInventory(1).getItem(1).getItemId() != cm.getInventory(1).getItem(2).getItemId()){
			cm.sendOk("非常抱歉，必须是两件相同的装备才可合成！");
			cm.dispose();
			return;
			}
			
		if(cm.判断金币() < 1000000)
		{
			cm.sendOk("#d#e冒险岛提示#n您的金币数量不足,需要:100万金币.");
            cm.dispose();
			return;
		}
        var ii = Packages.server.MapleItemInformationProvider.getInstance();
        var item = cm.getInventory(1).getItem(2);
        var statup = new java.util.ArrayList();
		var itemid = item.getItemId();
		if(itemid == 1102884||itemid == 1102049||itemid == 1102050||itemid == 1102051||itemid == 1102052||itemid == 1102353||itemid == 1102668||itemid == 1102149||itemid == 1102148||itemid == 1102349)
		{
			cm.sendOk("#d#e冒险岛提示#n翅膀满级前不能增幅");
            cm.dispose();
			return;
		}
//================================================================================================================================================
		else
		{
			var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
			var item1 = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(2).copy();
			var a=item.getItemId();
			var statup = new java.util.ArrayList();
			//item1.setOwner(增幅等级);
			item1.setStr(item1.getStr() + item.getStr() / 10);
			item1.setDex(item1.getDex() + item.getDex() / 10);
			item1.setInt(item1.getInt() + item.getInt() / 10);
			item1.setLuk(item1.getLuk() + item.getLuk() / 10);
			item1.setWatk(item1.getWatk() + item.getWatk() / 10);
			item1.setMatk(item1.getMatk() + item.getMatk() / 10);
            item1.setUpgradeSlots(item1.getUpgradeSlots() - 1);			
			Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 2, 1, true);
			Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(),item1,false);
			cm.收金币(1000000);
			cm.gainItem(a,-1);	
			cm.sendOk("#d#e冒险岛提示#n转移成功！");
            cm.dispose();
			return;
		}
	}
	
		
	
	}
}


