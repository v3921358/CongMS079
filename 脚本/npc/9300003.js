var status = 0;
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

function start(){
	action(1, 0, 0);
}

function action(mode, type, selection){	
	if(mode == 1){
		status++;
	} else if(mode == 0){
		status--;
	} else {
		cm.dispose();
		return;
	}
	if(status == 1){
		cm.sendYesNo("你真的确定要结婚吗?");
	} else if (status == 2){
			if(cm.getPlayer().getMarriageId() > 0){
                cm.sendNext("你已经结过婚");
                cm.dispose();
                return;
			} else if (cm.getParty() == null) {
                cm.sendNext("组队后在来找我");
                cm.dispose();
                return;
            } else if (!cm.isLeader()) {
                cm.sendNext("请让队长与我对话");
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
                if (victim.getId() != cm.getPlayer().getId() && (party.size() > 2 || victim == null || victim.getMarriageId() > 0 || victim.getMapId() != mapId || victim.getGender() == gender)) {
                    next = true;
                    break;
                }
            }

            if (!next) {
                cm.sendNext("请确认您跟您的的另外一半在这一张地图、不同性別、没结婚过、并且都在线以及队伍中沒有其他人");
                cm.dispose();
                return;
            }
			
			var imtenuber = 0;
			for(var i=0;i<结婚戒指.length;i++){
			  if(cm.getPlayer().hasEquipped(结婚戒指[i]) && victim.hasEquipped(结婚戒指[i]) ){//判断自己和另一半拥有这个戒指才执行
					imtenuber=结婚戒指[i];//imtenuber定义为结婚戒指代码
					break;
				}
			}
			 if (imtenuber == 0) {//判断imtenuber变量等于0就执行
                cm.sendNext("您或您的另一半沒有装备时装恋人/挚友戒指#？");
                cm.dispose();
                return;
            }
			
            
			
			if(!cm.canHold(1112804) || !victim.canHold(1112804)){
                cm.sendNext("您或您的另一半背包空间不足");
                cm.dispose();
                return;
			}
			cm.getPlayer().setMarriageId(victim.getId());
			victim.setMarriageId(cm.getPlayer().getId());
			cm.givePartyItems(1112804, 1, false);
			cm.getPlayer().saveToDB(false, false);
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"『月下老人』" + " :[ " + cm.getPlayer().getName() +"]和他的伴侣["+victim.getName()+"]结为夫妻。小姐珠圆玉润旺夫之相、宜室宜家,先生才高八斗、学富五车。现福禄鸳鸯缘定三生，佳偶天成，珠联壁合。祝二人：永结同心，百年好合、百子千孙,无论富贵贫穷同德同心、琴瑟合鸣、相敬如宾。结此终身之盟,守此终身之誓,不离不弃、白头偕老。大家祝福他[她]！我们祝他/她们从游戏走到现实婚姻的殿堂。",true));//黄色
			
			
			victim.saveToDB(false, false);
			
			cm.warpParty(700000200, 0);
			cm.dispose();
	} else{
		cm.dispose();
	}
	
}