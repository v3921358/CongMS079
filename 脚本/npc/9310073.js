var CY0 = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓";
var CY1 = "┃       - 创意 -       ┃";
var CY2 = "┃ 脚本仿制  　定制脚本 ┃";
var CY3 = "┃ 技术支持 　 游戏顾问 ┃";
var CY4 = "┃ ＷＺ添加　  地图制作 ┃";
var CY5 = "┃ 加盾防御　  售登陆器 ┃";
var CY6 = "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫";
var CY7 = "┃   唯一QQ:12384161    ┃";
var CY8 = "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛";
importPackage(Packages.client);
importPackage(Packages.client.inventory);
var status = -1;
var ApprenticeIntegral=1;//积分  这里是出师后师傅获得点出师积分
var RewardPoints=100000;//点卷   这里是出师后师傅获得的点卷
var PropsReward= new Array(//给徒弟给师傅点物品奖励  这里面是放给点奖励点物品 和数量 就是出师后双方的得到点物品
Array(2049118, 1), //混沌卷
Array(2370000, 5), //孙子兵法10万经验书
Array(2340000, 1) //祝福卷
);
var PointsItem=new Array(//积分兑换d物品
Array(2049118,10),//混沌卷 10积分  这里是出师积分兑换d物品  和兑换点积分 他
Array(2340000,10), //祝福卷 5积分
Array(1114314,50)
);

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    
    if (mode == 1) {
        status++;
    } else if (mode == 0 && status != 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }

    if (status == 0) {
		    var text ="#e#b"+花草1+" 师 徒 中 心 "+花草+"#k\r\n\r\n";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			text +=" #L1##d"+tz2+"建立师门"+tz2+"#l                  #L9#"+tz2+"查看师傅"+tz2+"#l\r\n"
			text +=" #L2#"+tz2+"带徒入门"+tz2+"#l                  #L4#"+tz2+"逐徒出师"+tz2+"#l\r\n"
            text +=" #L3#"+tz2+"带徒出师"+tz2+"#l                  #L5#"+tz2+"退出师门"+tz2+"#l\r\n"
			text +=" #L8#"+tz2+"徒弟列表"+tz2+"#l                  #L7#"+tz2+"积分兑换"+tz2+"#l\r\n"
			text +="                 #L6#"+tz2+"师徒说明"+tz2+"#l\r\n"
			//text +="              #L10#"+tz2+"<师徒戒指升星>"+tz2+"#l\r\n"
            cm.sendSimple(text);
    } else if (status == 1){
		if (selection == 1){
			var id = cm.getPlayer().getId();
			if (cm.getPlayer().getLevel() < 120){
				cm.sendOk("你的等级不够120级");
				cm.dispose();
				return;
			}else if(cm.getPlayer().getiLearnTeacher(id,1)==1){
				cm.sendOk("你还没有出师,无法建立师门!");
				cm.dispose();
				return;
			}else if(cm.getPlayer().getiLearnTeacher(id,1)!=3){
				cm.getPlayer().setLearnteacher(id);
				cm.sendOk("你成功建立了师门，赶紧去收徒吧！");
				cm.worldMessage(6,"【师徒系统】[" + cm.getChar().getName() + "]成功建立了师门，要找师傅的赶紧了！");
				cm.dispose();
				return;
			} else {
				cm.sendOk("你已经建立过师门了！去收徒吧！");
				cm.dispose();
				return;
			}
			
        }if (selection == 10){
			cm.dispose();
				cm.openNpc(9310073,  "师徒戒指升星");
		}
		else if (selection == 2){
			var id = cm.getPlayer().getId();
			if (cm.getParty() == null) {
                cm.sendNext("请组队后在来找我！");
                cm.dispose();
                return;
            } else if (cm.allMembersHere() == false){
				cm.sendOk("徒弟或者师傅不在这个地图啊？");
				cm.dispose();
				return;
				
			} else if (cm.getPlayer().getiLearnTeacher(id,1)!=3) {
                cm.sendNext("请让师傅且要建立过师门后找我对话(PS：师傅一定要是队长)");
                cm.dispose();
                return;
			} 

			var next = true;
			var mapId = cm.getPlayer().getMapId();
			var party = cm.getPlayer().getParty().getMembers();
			var it = party.iterator();
			var cPlayer = it.next();
			var victim = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
			var Tname=victim.getName();

            while (it.hasNext()) {
                var cPlayer = it.next();
                victim = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
                if (victim.getId() != cm.getPlayer().getId()) {
                    next = true;
                    break;
                }
            }
			var TDid = victim.getId();
            if (!next) {
                cm.sendNext("请确认都在这一张地图,并且都在线以及队伍中沒有其他人。");
                cm.dispose();
                return;
			} 
			else if (cm.getPlayer().getParty().getMembers().size() != 2){
				cm.sendOk("每次只能带一个徒弟入门（请2人组队）");
				cm.dispose();
				return;
            }
			else if (victim.getId() == cm.getPlayer().getId()){
				cm.sendOk("获取不到队友信息,需要师傅开创队伍，邀请徒弟加入组队,师傅为队长,徒弟在同一地图同一频道!");
				cm.dispose();
				return;
			} 
			else if (victim.getLevel() < 10){
				cm.sendOk("准徒弟等级是否已经大于10级了？！");
				cm.dispose();
				return;
		    } else if (victim.getLevel() > 90){
				cm.sendOk("你的队友等级已经超过90级了!队友当前等级为:"+victim.getLevel());
				cm.dispose();
				return;
			} else if (cm.getPlayer().getiLearnTeacher(TDid,1)>= 1){
				cm.sendOk("这名队员已经有师傅，或者已经出师了!");
				cm.dispose();
				return;
			} else if (cm.getPlayer().getiLearnTeacher(id,1)!=3){
				cm.sendOk("你还没建立师门呢");
				cm.dispose();				
			} else if (cm.getPlayer().getParty().getMembers().size() > 2){
				cm.sendOk("每次只能带一个徒弟入门（请2人组队）");
				cm.dispose();
				return;
			} else {
				cm.getPlayer().setApprentice(id,TDid);
				cm.sendOk("你成功收了"+victim.getName()+"为徒弟，请照顾好你的徒弟，成功出师双方将获得丰厚奖励！");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(11,cm.getC().getChannel(),"师徒系统" + " : ["+ cm.getPlayer().getName() +"]收了["+victim.getName()+"]为徒弟，请照顾好你的徒弟，成功出师双方将获得丰厚奖励！",true));
				cm.dispose();
				return;
			}
		} else if (selection == 3){
			var id=cm.getPlayer().getId();
			if (cm.getParty() == null) {
            cm.sendNext("组队后在来找我");
            cm.dispose();
            return;
            }else if (cm.getOneTimeLog("师傅") > 1){
				cm.sendOk("你还没建立师门呢");
				cm.dispose();
				return;
			}
			var next = true;
			var gender = cm.getPlayer().getGender();
			var mapId = cm.getPlayer().getMapId();
			var party = cm.getPlayer().getParty().getMembers();
			var it = party.iterator();
			var cPlayer = it.next();
			var victim = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());

            while (it.hasNext()) {
				var id = cm.getPlayer().getId();
                var cPlayer = it.next();
                victim = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
                if (victim.getId() != cm.getPlayer().getId()) {
                    next = true;
                    break;
                }
            }
			var TDid = victim.getId();
			if (!next) {
            cm.sendNext("请确认都在这一张地图,并且都在线以及队伍中沒有其他人。");
            cm.dispose();
            return;
			}else if (cm.getPlayer().getParty().getMembers().size() != 2)
			{
			cm.sendOk("每次只能带一个徒弟入门（请2人组队）");
			cm.dispose();
			return;
            }else if (cm.getPlayer().getiLearnTeacher(id,1)!=3) 
			{
            cm.sendOk("请让师傅找我对话(PS：师傅一定要是队长)");
            cm.dispose();
            return;
			}else if(cm.getPlayer().getiLearnTeacher(TDid,0)!=id)
			{
			cm.sendOk("你队友并不是你的徒弟，请带上自己未出师的徒弟来进行!");
			cm.dispose();
			return;
		    }else if (victim.getLevel() < 120){
			cm.sendOk("你的徒弟等级不够120级，不能出师！");
			cm.dispose();
			return;
			} else if (cm.getPlayer().getiLearnTeacher(TDid,1) >1){
			cm.sendOk("这个徒弟已经出师了！");
			cm.dispose();
			return;
			} else {
			if(!cm.canHold())
			{
			cm.sendOk("师傅背包满了不能获取奖励，请清理背包后再进行出师");
			cm.dispose();
			return;
			}
			for(var i=0;i<PropsReward.length;i++)
			{
				Reward(PropsReward[i][0],PropsReward[i][1]);
                victim.gainItem(PropsReward[i][0],PropsReward[i][1]);
			}
				Reward(0,RewardPoints);//给点卷点数量
				Reward(1,ApprenticeIntegral);//给点积分
				cm.getPlayer().updateApprentice(id,TDid,0);
				cm.sendOk("你带徒"+victim.getName()+"出师成功!\r\n师傅获得：师傅出师奖励。");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(11,cm.getC().getChannel(),"师徒系统" + " : ["+ cm.getPlayer().getName() +"]玩家成功带徒出师["+victim.getName()+"]获得重磅礼包！",true));
				cm.dispose();
			}
		} else if (selection == 4){
			var id=cm.getPlayer().getId();
			if (cm.getPlayer().getiLearnTeacher(id,1)!=3) 
			{
            cm.sendOk("你还没有成立师门,请成立师门后再进行操作!");
            cm.dispose();
            return;
			}else if(cm.getPlayer().getiLearnTeacher(id,1)==3)
			{	
			var str="",x="",dxt="";
			var pid=0;
			var xx=0;
			xx=cm.getPlayer().getiApprentice(id);
			if(xx=="[]")
			{
				cm.sendOk("您暂时没有名下的徒弟，到达一定等级成为师傅后可以是找到心仪d玩家成为您的徒弟!");
				cm.dispose();
				return;
			}
			for(i=0;i<xx.size();i++)
			{
				var n=100000;
				x=cm.getPlayer().getCharaName(xx.get(i));
				pid=cm.getPlayer().getiLearnTeacher(xx.get(i),1);
				if(pid ==1)
				{
					var ax=0;
					ax=xx.get(i);
					n=parseInt(n)+parseInt(ax);
					str+="#L"+n+"#【徒弟】 "+x+"   [未出师]   #r 逐出师门#k#l \r\n";
				}
				}
				if(str!=""){
				cm.sendNext(str);
				}else{
				cm.sendOk("你名下的徒弟都已经出师了，再去找新的徒弟把!");
				cm.dispose();
				return;
				}
			}
			else 
			{
				cm.sendOk("你名下的徒弟都已经出师了，再去找新的徒弟把!");
				cm.dispose();
				return;
			}
		} else if (selection == 5){
			var id = cm.getPlayer().getId();
			var jrsm = cm.getPlayer().getiLearnTeacher(id,1);
			if (jrsm != 1){;
				cm.sendOk("退出师门失败！\r\n你没并没有加入过师门或已经出师了!\r\n（注：师傅不可以取消师门）");
				cm.dispose();
			} else {
				cm.getPlayer().updateApprentice(id,id,2);
				cm.sendOk("退出师门成功!");
				cm.dispose();
			}
		} else if (selection == 6){
			cm.sendOk("师门系统介绍：\r\n徒弟等级：必须大于10级小于90级\r\n师父等级：必须120级以上\r\n\r\n徒弟达到120级即可出师！\r\n\r\n师父出师获得：\r\n10万点卷，混沌卷*1，祝福卷*1，孙子兵法*5，师徒积分*1\r\n\r\n徒弟出师获得：混沌卷*1，祝福卷*1，孙子兵法*5\r\n#r出师请确认好背包有足够空位，导致空位不足奖励领取不到后果自负\r\n");
			cm.dispose();
		} else if (selection == 7){
			var str="  您当前拥有积分:"+cm.getjf9()+"\r\n";
			for(i=0;i<PointsItem.length;i++){
			
				var n=1000000;
				n=parseInt(n)+parseInt(i);
				str+="#L"+n+"#[积分兑换] #i"+PointsItem[i][0]+"#       需要:#r"+PointsItem[i][1]+"积分#k#l\r\n";
			}
			cm.sendNext(str);
		}
		else if(selection ==8)
		{
			var str="",x="",dxt="";
			var pid=0;
			var xxx=cm.getPlayer().getId();
			var xx=cm.getPlayer().getiApprentice(xxx);
			if(xx=="[]")
			{
				cm.sendOk("您暂时没有名下的徒弟，到达一定等级成为师傅后可以是找到心仪的玩家成为您的徒弟!");
				cm.dispose();
			}
			for(i=0;i<xx.size();i++)
			{
				var name="";
				pid=cm.getPlayer().getiLearnTeacher(xx.get(i),1);
			    name=cm.getPlayer().getCharaName(xx.get(i));
				if(pid>1){dxt=name+"  [出师]";}else {dxt=name+"  [未出师]";}
				str+=""+x+""+dxt+"\r\n";
				}
				//str+=""+xx.get(i);
				
				cm.sendOk(str);
				cm.dispose();
		}
		else if(selection ==9)
		{
			var str="";
			var xxx=cm.getPlayer().getId();
			var str =cm.getPlayer().getiLearnTeacher(xxx,0);
			if(str<1)
			{
				cm.sendOk("您暂时没有师傅，可以找一名玩家来当你的师傅，或者达到一定等级后自己成为师傅！");
				cm.dispose();}
		    else{
			var xx=cm.getPlayer().getCharaName(str);
				cm.sendOk("你师傅是："+xx);
				cm.dispose();
		     }
		}	
    }		
	else if(selection >100000&&1000000>selection)
	{
	var id=cm.getPlayer().getId();
	var playerid=selection-100000;
	cm.getPlayer().updateApprentice(playerid,playerid,1);
	cm.sendOk("【徒弟】"+cm.getPlayer().getCharaName(playerid)+" 已经被你逐出师门 ！！");
	cm.dispose();
	return;
	}
	else if(selection>=1000000)
	{
		if(!cm.canHold())
		{
		cm.sendOk("您的背包满了不能兑换奖励，请清理背包后再进行兑换");
		cm.dispose();
		return;
		}
		var pion=selection-1000000;
		if(cm.getPlayer().getjf9()>PointsItem[pion][1])
		{
			//扣除积分呢
			cm.gainjf9(-PointsItem[pion][1]);
			cm.gainItem(PointsItem[pion][0],1);
			cm.sendOk("成功兑换物品：#r#t"+PointsItem[pion][0]+"##k");
			cm.dispose();
			return;
		}else
		{
			cm.sendOk("出师积分不足，无法兑换！");
			cm.dispose();
			return;
		}
	}
}


function Reward(id,number)
{
	switch(id)
	{
		case 0://点卷
		cm.getChar().modifyCSPoints(1,number,true);
		break;
		case 1://积分
		cm.gainjf9(ApprenticeIntegral);
		break;
		default:
		cm.gainItem(id,number);
	}
}
var 花草 ="#fEffect/SetEff/208/effect/walk2/4#";
var 花草1 ="#fEffect/SetEff/208/effect/walk2/3#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
var tz2 = "#fEffect/CharacterEff/1082565/2/0#";  //蓝兔子