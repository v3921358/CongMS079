var select = -1;
var xx;
importPackage(java.util);
importPackage(Packages.client);
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
var numArr = Array("#fUI/Basic.img/LevelNo/0#","#fUI/Basic.img/LevelNo/1#","#fUI/Basic.img/LevelNo/2#","#fUI/Basic.img/LevelNo/3#","#fUI/Basic.img/LevelNo/4#","#fUI/Basic.img/LevelNo/5#","#fUI/Basic.img/LevelNo/6#","#fUI/Basic.img/LevelNo/7#","#fUI/Basic.img/LevelNo/8#","#fUI/Basic.img/LevelNo/9#");
importPackage(Packages.server);
importPackage(Packages.tools);
importPackage(Packages.tools.packet);
function start() {
    var Editing = false //false 开始
    if (Editing) {
        cm.sendOk("维修中");
        cm.dispose();
        return;
    }
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }
    if (status == 0) {
        cm.sendSimple("#r#e             "+小黄星+"   #e#d装 备 解 锁#k#n   "+小黄星+"  #k#n\r\n  "+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n\r\n#r介绍：\r\n"+numArr[1]+" 装备解锁：解除上锁后可变为能交易（消耗2000万金币）\r\n"+numArr[2]+" 不可交易：可以在商城购买宿命剪刀使用后可变为能交易\r\n"+numArr[3]+" 不可交换：为锁定永久不能交换和交易#k\r\n\r\n" +
                 
                "#L0##b"+红色箭头+"[解锁装备栏第一格装备]#k\r\n"
				

				);
            
    } else if (status == 1) {
		if(cm.getInventory(1).getItem(1) == null) {
            cm.sendOk("第一格没有装备.");
            cm.dispose();
            return;
        }
        var xx = cm.getInventory(1).getItem(1).getOwner();
        var statup = new java.util.ArrayList();
        var itemId1 = cm.getInventory(1).getItem(1).getItemId();
        var item = cm.getInventory(1).getItem(1).copy();
        var ii = Packages.server.MapleItemInformationProvider.getInstance();
        var type =  Packages.constants.GameConstants.getInventoryType(itemId1);
        var sx0 = item.getStr();
        var sx1 = item.getDex();
        var sx2 = item.getInt();
        var sx3 = item.getLuk();
        var sx4 = item.getHp();
        var sx5 = item.getMp();
        var sx6 = item.getWatk();
        var sx7 = item.getMatk();
        var sx8 = item.getWdef();
        var sx9 = item.getMdef();
        var sx10= item.getAcc();
        var sx11= item.getAvoid();
        var sx12= item.getHands();
        var sx13= item.getSpeed();
        var sx14= item.getJump();
        if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
            cm.sendOk("限时装备不能解锁.");
            cm.dispose();
            return;
        }
        /*if (cm.getInventory(1).getItem(1).getUniqueId() > 0) {
            cm.sendOk("现金装备无法解锁。");
            cm.dispose();
            return;
        }*/
        /*if (Math.floor(item.getItemId()/10000) == 114 ) {
            cm.sendOk("第一格装备是#v" + item.getItemId() + "#,勋章无法觉醒!"); 		
            cm.dispose();    
            return;
        }*/
		if(item.getFlag()==0){//判断装备没有上锁
            cm.sendOk("没有上锁的装备，不需要解锁哦"); 		
            cm.dispose();    
            return;
        }
            if (selection == 0) {
                
				 if(cm.getPlayer().getMeso() < 20000000){
			cm.sendOk("金币不足,需要2000W!");
            cm.dispose();
                    return;			
			}
                
                cm.gainMeso(-20000000);//减去金币
                var 四维属性 = 1;
                var 攻魔 = 1;
		    	item.setFlag(0);//0为没上锁，1为上锁
		    	item.setStr(sx0);
		    	item.setDex(sx1);
		    	item.setInt(sx2);
		    	item.setLuk(sx3);
		    	item.setHp(sx4);
                item.setMp(sx5);
                var RandomChance = Math.floor(Math.random()*101);
                    if(RandomChance <= 60){
                        item.setWatk(sx6);
		    	        item.setMatk(sx7);
                    }
		    	item.setWatk(sx6);
		    	item.setMatk(sx7);
		    	item.setWdef(sx8);
		    	item.setMdef(sx9);
		    	item.setAcc(sx10);
		    	item.setAvoid(sx11);
		    	item.setHands(sx12);
		    	item.setSpeed(sx13);
		    	item.setJump(sx14);
		    	//item.setOwner("突破");				
		    	cm.dispose();
		    	MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    	MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
		    	cm.ShowWZEffect("Effect/BasicEff.img/SkillBook/Success/0"); //成功
		    	cm.sendOk("解锁成功！");
				cm.worldMessage(  " 【解锁公告】" + " : " + "["+cm.getPlayer().getName()+"]解锁了一件装备！！")
                cm.dispose();
                return;
            } 

    }
}

function openNpc(npcid) {
    openNpc(npcid, null);
}

function openNpc(npcid, script) {
    var mapid = cm.getMapId();
    cm.dispose();
    if (cm.getPlayerStat("LVL") < 10) {
        cm.sendOk("你的等级不能小于10等.");
    } else if (
            cm.hasSquadByMap() ||
            cm.hasEventInstance() ||
            cm.hasEMByMap() ||
            mapid >= 990000000 ||
            (mapid >= 680000210 && mapid <= 680000502) ||
            (mapid / 1000 === 980000 && mapid !== 980000000) ||
            mapid / 100 === 1030008 ||
            mapid / 100 === 922010 ||
            mapid / 10 === 13003000
            ) {
        cm.sendOk("你不能在这里使用这个功能.");
    } else {
        if (script == null) {
            cm.openNpc(npcid);
        } else {
            cm.openNpc(npcid, script);
        }
    }
}