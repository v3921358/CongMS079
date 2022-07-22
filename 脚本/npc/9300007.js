var status = 0
var victim;
var 结婚戒指 = Array(1112005,
1112001,
1112012,//红玫瑰情侣戒指
1112802,//红线挚友戒指
1112007,//许愿情侣戒指
1112006,//圣十字架恋人戒指
1112801,//挚友戒指
1112002
); 
var 结婚礼服 = Array(
1050122,
1051130
);    

function start() {
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
    if (status == 1) {
        cm.sendSimple("" +
                "#L1##r#e进入红鸾宫#l\r\n\r\n" + "#L4##r#e我要进入结婚礼堂宴请宾客!#l\r\n\r\n"+"#L2##k#e我想回去了#l\r\n\r\n\r\n\r\n"+"#L3##r#e我要离婚（一点就离婚，慎用）!#l");
    } else if (status == 2) {
		sele1 = selection;
        if (selection == 1) {
            if (cm.getParty() == null) {
                cm.sendNext("请与你的另一半组队后找我");
                cm.dispose();
                return;
            }
            if (!cm.isLeader()) {
                cm.sendNext("请让队长与我对话");
                cm.dispose();
                return;
            }
			if(cm.getPlayer().getMarriageId() > 0){
                cm.sendNext("你已经结过婚");
                cm.dispose();
                return;
			}
            var party = cm.getParty().getMembers();
            var inMap = cm.partyMembersInMap();
            var levelValid = 0;
			var imtenuber = 0;
			for (var i = 0; i < party.size(); i++) {
            if (party.get(i).getLevel() >= 30 && party.get(i).getLevel() <= 250)
                    levelValid++;
            }
			if (inMap < 2 || inMap > 2) {
                cm.sendOk("你的队伍人数不足2人.请把你的结婚对象叫到这里来.");
                cm.dispose();
				return;
            }else if (levelValid != inMap) {
                cm.sendOk("请确保你的另一半在本地图，且最小等级在 30 和 250之间.");
                cm.dispose();
				return;
			}
			var gender = cm.getPlayer().getGender();
            var mapId = cm.getPlayer().getMapId();
            var next = true;
            var party = cm.getPlayer().getParty().getMembers();
            var it = party.iterator();
			while (it.hasNext()) {
                var cPlayer = it.next();
                victim = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
				if (victim.getId() != cm.getPlayer().getId() && (party.size() > 2 || victim == null || victim.getMapId() != mapId)) {
					
                    next = false;
                    break;
                }
            }
			if (!next) {
                cm.sendNext("请确认您跟您的的另外一半在这一张地图、不同性別、并且都在线以及队伍中沒有其他人");
                cm.dispose();
                return;
            }
			
			
			var imtenuber = 0;
			for(var i=0;i<结婚戒指.length;i++){
			  if((cm.getPlayer().hasEquipped(结婚戒指[i]))&&(victim.hasEquipped(结婚戒指[i]))){//判断拥有这个戒指才执行
					imtenuber=结婚戒指[i];//imtenuber定义为结婚戒指代码
					break;
				}
			   
			}if ((imtenuber == 0)) {//判断imtenuber变量等于0就执行
                cm.sendNext("您或您的另一半沒有装备恋人戒指#？");
                cm.dispose();
                return;
            }
			var imtenuber1 = 0;
			for(var i=0;i<结婚礼服.length;i++){
			  if(cm.getPlayer().hasEquipped(结婚礼服[i])&&(victim.hasEquipped(结婚礼服[1]))){//判断拥有这个结婚礼服才执行
					imtenuber1=结婚礼服[i];//imtenuber定义为结婚戒指代码
					break;
				}
			}
			 if (imtenuber1 == 0) {//判断imtenuber变量等于0就执行
                cm.sendNext("您或您的另一半沒有装备婚礼服#？");
                cm.dispose();
                return;
            }
			cm.sendYesNo("確定是否要进去结婚吗?");
        } else if (selection == 2) { 
            	
            cm.warp(100000000);
            cm.dispose();
			
		} else if (selection == 3) {
			cm.sendYesNo("你确定要离婚么？");
        } else if (selection == 4) {
			cm.warpParty(700000200);
			cm.dispose();
            Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(12, cm.getClient().getChannel(), "『南山结婚喜讯』" + " : " + "[" + cm.getChar().getName() + "]和他的伴侣结为夫妻缘定三生三世,现在请大家速来结婚宴会礼堂参加他们的婚礼吧!!!"));

			
			
			
			
        }
    } else if (status == 3) {
		if (sele1 == 3) {
			if(cm.getPlayer().getMarriageId() ==0){
                cm.sendNext("没结过婚的别来凑热闹");
                cm.dispose();
                return;
			}else if(!cm.haveItem(1112804, 1)){
			cm.sendOk("请把结婚戒指放在背包里!!!");	
			cm.dispose();
			}else if(cm.getMeso() <= 1000000) {
            cm.sendOk("抱歉，离婚需要缴纳100万，请凑足了再来！");
            cm.dispose();
            }
			else{
			cm.gainMeso(-1000000);
			cm.gainItem(1112804, -1);
            cm.getPlayer().setMarriageId(0);
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(11, cm.getClient().getChannel(), "『月下老人』" + " : " + "[" + cm.getChar().getName() + "]和已经脱单,请他的另一半也来登记离婚"));
            cm.dispose();
			}
			cm.dispose();
            return;
		}
		
		
		cm.warpParty(700000100);
        cm.dispose();
    } else {
        cm.dispose();
    }
	

	
	
	
	
	
	
	
	
	
}