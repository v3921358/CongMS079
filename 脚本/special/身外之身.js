var 礼包物品 = "#v1302000#";
var x1 = "1302000,+1";// 物品ID,数量
var x2;
var x3;
var x4;

var 需要点券 = 5000;
function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("感谢你的光临！");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
				
			
            text += "  "+小雪花+"#v1003861#       #e#r欢迎来到身外之身    #v1003861#"+小雪花+"#k#n\r\n" 
			//注：此分身只具备角色 25% ~ 30% 的伤害          需要达到 100% 伤害请联系GM开启体验!  #v1003861##k#n\r\n"
			text += "#r#e----------------------------------------------#k#n\r\n";
			
			text += "注：此分身具备角色100%伤害!关闭分身快捷口令:@关闭身外\r\n";
			
			text += "#L0##k" + aaa + "#d开启身外之身(下线后请先关闭身外之身再开启即可)#l\r\n\r\n"
			if(cm.haveItem(3994769,1)){
			text += "      #L10##k"+小雪花+"#d< < <开启身外之身二> > >"+小雪花+"#l\r\n"
			text += "      #L11##k"+小雪花+"#r< < <关闭身外之身二> > >"+小雪花+"#l\r\n\r\n"

			}
            text += "             #L1##k" + aaa + "#r关闭身外之身#k#l\r\n"
			text += "#L2#" + aaa + "#b购买永久#v3994759##n#l    #L3##K" + aaa + "#b试用一时#v3994759##n\r\n\r\n"

            cm.sendSimple(text);
        } else if (status == 1) {
            if (selection == 0) {
				
                if (cm.getPlayer().getjf2() >= 1 ) {
					cm.sendOk("你已经开启了身外之身，无需再次开启！");
					cm.dispose();
					
				}else if(!cm.haveItem(3994759,1)){
					cm.sendOk("你貌似没有需求的东西");
					cm.dispose();
					
				} else {					
					cm.getPlayer().cloneLook();
					cm.getPlayer().setjf2(cm.getPlayer().getjf2()+1);
					cm.sendOk("成功开启身外之身！");
					cm.dispose();
				}
			
			 
                }else if (selection == 10) {
                if (cm.getPlayer().getjf4() >= 1 ) {
					cm.sendOk("你已经开启了身外之身2，无需再次开启！");
					cm.dispose();
					
				}else if(!cm.haveItem(3994769,1)){
					cm.sendOk("你貌似没有需求的东西");
					cm.dispose();
					
				} else {
					cm.getPlayer().cloneLook();
					cm.getPlayer().setjf4(cm.getPlayer().getjf4()+1);
					cm.sendOk("成功开启身外之身2！");
					cm.dispose();
				}
			
			 
                }else if (selection == 1) {
                if (cm.getPlayer().getjf2() < 1 ) {
					cm.sendOk("你还未开启身外之身，无需关闭。");
					cm.dispose();
				} else {
					cm.getPlayer().setjf2(cm.getPlayer().getjf2()-1);
					cm.getPlayer().disposeClones();
					cm.sendOk("已关闭身外之身,如需要请重新开启~");
					cm.dispose();
                 }
				
            }else if (selection == 11) {
                if (cm.getPlayer().getjf4() < 1 ) {
					cm.sendOk("你还未开启身外之身，无需关闭。");
					cm.dispose();
				} else {
					cm.getPlayer().setjf4(cm.getPlayer().getjf4()-1);
					cm.getPlayer().disposeClones();
					cm.sendOk("已关闭身外之身,如需要请重新开启~");
					cm.dispose();
                 }
				
            }else if (selection == 2) {
               if (!cm.checkNumSpace(4, 1)) {
			cm.sendOk("背包其他栏空间不足1格");
			cm.dispose();
			return;
		    }
			if(getmoneyb()>=1000 && cm.getPlayer().getItemQuantity(3994759,false)<1){
				setmoneyb(-1000);
				//cm.gainItemPeriod(3994720,1,30*24);//这里是给1个物品的30*24小时
				cm.gainItem(3994759,1);
				cm.sendOk("购买成功");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『身外之身』" + " : " + "[" + cm.getChar().getName() + "]通过购买，获得了永久身外之身功能！")); 
				cm.dispose();
				return;
			}else {
			cm.sendOk("元宝不足1000,或者你已经有这个东西了");
			cm.dispose();
			return;
			}
				
            }
			else if (selection == 3) {
               if (!cm.checkNumSpace(4, 1)) {
			cm.sendOk("背包其他栏空间不足1格");
			cm.dispose();
			return;
		    }
			if(cm.getPlayer().getItemQuantity(3994759,false)<1 && cm.getPlayer().getPrizeLog("试1") < 1){
				cm.getPlayer().setPrizeLog("试1");	
				cm.gainItemPeriod(3994759,1,1);
				cm.sendOk("领取成功");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『身外之身』" + " : " + "[" + cm.getChar().getName() + "]获得了试用身外之身功能！")); 
				cm.dispose();
				return;
			}else {
			cm.sendOk("你已经有这个东西了，或者试用过一次了");
			cm.dispose();
			return;
			}
				
            }
        }
    }
}

var add = "#fEffect/CharacterEff/1022223/4/0#";
var ttt = "#fUI/UIWindow.img/Quest/icon9/0#";
var xxx = "#fUI/UIWindow.img/Quest/icon8/0#";
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";
var add = "#fEffect/CharacterEff/1112905/0/1#";//红桃心
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//红色右箭头
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//选择道具
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 美化new = "#fUI/UIWindow/Quest/icon5/1#";
var 圆形 = "#fUI/UIWindow/Quest/icon3/6#";
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 琴符 = "#fEffect/CharacterEff/1003252/0/0#";
var 音符 = "#fEffect/CharacterEff/1032063/0/0#";
var 花 = "#fUI/UIWindow.img/AriantMatch/characterIcon/0#";
var s = "#fUI/StatusBar/BtClaim/normal/0#";
var h = "#fUI/CashShop/CSEffect/effect/1#";
var 小雪花 = "#fEffect/CharacterEff/1003393/0/0#";
var 翅膀 = "#fUI/CashShop/Base/Tab/Enable/2#";//翅膀
var 爱心4 = "#fEffect/CharacterEff/1042176/1/1#"; // 实体深红爱心【小型】
var 爱心2 = "#fEffect/CharacterEff/1022223/3/0#"; // 虚体深色粉红爱心
var 爱心1 = "#fEffect/CharacterEff/1003271/0/0#"; // 实体粉红爱心
var 表情大笑 ="#fUI/GuildBBS/GuildBBS/Emoticon/Basic/2#";//表情大笑/1哭/0微笑 

	function getmoneyb() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("moneyb");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}
function setmoneyb(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET moneyb = moneyb+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}
