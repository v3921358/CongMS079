var CY0 = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓";
var CY1 = "┃       - 创意 -       ┃";
var CY2 = "┃ 脚本仿制  　定制脚本 ┃";
var CY3 = "┃ 技术支持 　 游戏顾问 ┃";
var CY4 = "┃ ＷＺ添加　  地图制作 ┃";
var CY5 = "┃ 加盾防御　  售登陆器 ┃";
var CY6 = "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫";
var CY7 = "┃   唯一QQ:12384161    ┃";
var CY8 = "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛";
var 奖励 = "#fUI/CashShop/CSDiscount/bonus#";
var 经验值 = "#fUI/UIWindow/QuestIcon/8/0#";
var 金币 = "#fUI/UIWindow/QuestIcon/7/0#";
var 红色箭头 = "#fEffect/CharacterEff/1112908/0/1#";  //彩光3
var 大红星 = "#fEffect/CharacterEff.img/1022223/1/0#";
var 正在进行中 = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var 毛球 = "#fUI/ChatBalloon.img/pet/12/nw#";
var 点券 = "#fUI/CashShop/CashItem/0#";
var 完成 = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var 警钟 = "#fUI/Basic/BtClaim/normal/0#";
var 商品已售空 = "#fUI/CashShop/GuideWords/0#";
var status = 0;
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        if (status == 0) {
            var txt = "";
            if (cm.getBossLog('每日跑商') < 1) {
            //if (cm.getPS() == 0){// cm.getPS()  的意思是 读取跑商值如果等于0 就得出他没有开始跑商 就运行他进行第一环跑商!
				txt = "\t\t\t\t#e"+毛球+"每日跑商第一环"+毛球+"#k#n\r\n";
                txt += "收集任务物品：\r\n";
				txt += "#v4000002# #z4000002# [#r#c4000002##k/30] #v4000017# #z4000017# [#r#c4000017##k/20] "+正在进行中+"\r\n";
				txt += "#L2#"+红色箭头+"快速传送至地图(猪的海岸) 需要 X 1万金币#l\r\n\r\n";
                txt += ""+奖励+"\r\n";
				txt += "#v4000313#  X 5\r\n";
				txt += ""+经验值+" X 10000 (随着等级提升Exp)\r\n";
				//txt += ""+点券+" 抵用卷 X 100\r\n";
				txt += "#L1##b"+红色箭头+"提交完成任务#l\r\n";
                cm.sendSimple(txt);
            }else if(cm.getBossLog('每日跑商') == 1){
                txt += "你已经完成了每日跑商第一环 "+完成+"\r\n";
                txt += "\t\t\t\t#e"+毛球+"每日跑商第二环"+毛球+"#k#n\r\n\r\n";
				txt += "第二环地图：\r\n";
				txt += "射手村 - NPC 阿得拉\r\n";// 9209000
                txt += "收集任务物品：\r\n";
				txt += "#v4000013# #z4000013# [#r#c4000013##k/50]\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('每日跑商') == 2){
                txt += "你已经完成了每日跑商第二环 "+完成+"\r\n";
                txt += "\t\t\t\t#e"+毛球+"每日跑商第三环"+毛球+"#k#n\r\n\r\n";
				txt += "第三环地图：\r\n";
                txt += "魔法密林 - NPC 道具制造易德!\r\n";// 1032002
                txt += "#v4000190# #z4000190# [#r#c4000190##k/25] #v4000191# #z4000191# [#r#c4000191##k/25]\r\n\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('每日跑商') == 3){
                txt += "你已经完成了每日跑商第三环 "+完成+"\r\n";
                txt += "\t\t\t\t#e"+毛球+"每日跑商第四环"+毛球+"#k#n\r\n\r\n";
				txt += "第四环地图：\r\n";
                txt += "勇士部落 - 仓库管理员 王先生!\r\n";// 1022005
                txt += "#v4000043# #z4000043# [#r#c4000043##k/50]\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('每日跑商') == 4){
                txt += "你已经完成了每日跑商第四环 "+完成+"\r\n";
                txt += "\t\t\t\t#e"+毛球+"每日跑商第五环"+毛球+"#k#n\r\n\r\n";
				txt += "第五环地图：\r\n";
                txt += "废弃都市 - NPC 网管马龙\r\n";// 1052012
                txt += "#v4000164# #z4000164# [#r#c4000164##k/25] #v4000165# #z4000165# [#r#c4000165##k/25]\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('每日跑商') == 5){
                txt += "你已经完成了每日跑商第五环 "+完成+"\r\n";
                txt += "\t\t\t\t#e"+毛球+"每日跑商第六环"+毛球+"#k#n\r\n\r\n";
				txt += "第六环地图：\r\n";
                txt += "林中之城 - NPC 仓库管理员 吴先生\r\n";// 1061008
                txt += "#v4000173# #z4000173# [#r#c4000173##k/50]\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('每日跑商') == 6){
                txt += "你已经完成了每日跑商第六环 "+完成+"\r\n";
                txt += "\t\t\t\t#e"+毛球+"每日跑商第七环"+毛球+"#k#n\r\n\r\n";
				txt += "第七环地图：\r\n";
                txt += "射手公园 - NPC 仓库管理员 李先生\r\n";// 1012009
                txt += "#v4000379# #z4000379# [#r#c4000379##k/100]\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('每日跑商') == 7){
                txt += "你已经完成了每日跑商第七环 "+完成+"\r\n";
                txt += "\t\t\t\t#e"+毛球+"每日跑商第八环"+毛球+"#k#n\r\n\r\n";
				txt += "第八环地图：\r\n";
                txt += "里恩 - NPC 仓库管理员 普斯拉\r\n";// 1200000
                txt += "#v2020015# #z2020015# [#r#c2020015##k/30] #v2020014# #z2020014# [#r#c2020014##k/30]\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('每日跑商') == 8){
                txt += "你已经完成了每日跑商第八环 "+完成+"\r\n";
                txt += "\t\t\t\t#e"+毛球+"每日跑商第九环"+毛球+"#k#n\r\n\r\n";
				txt += "第九环地图：\r\n";
                txt += "玩具城 - NPC 仓库管理员 舍琵\r\n";// 2041008
                txt += "#v4000265# #z4000265# [#r#c4000265##k/50]\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else if(cm.getBossLog('每日跑商') == 9){
                txt += "你已经完成了每日跑商第九环 "+完成+"\r\n";
                txt += "\t\t\t\t#e"+毛球+"每日跑商第十环"+毛球+"#k#n\r\n\r\n";
				txt += "第九环地图：\r\n";
                txt += "神木村 - NPC 仓库管理员 寇斯库!\r\n";// 2080005
                txt += "#v4001084# #z4001084# [#r#c4001084##k/1]\r\n";
				txt += "#v4000175# #z4000175# [#r#c4000175##k/1]\r\n";
				txt += "#r"+警钟+"最后第十环奖励超级丰厚哦！#k#n\r\n";
                cm.sendOk(txt);
                cm.dispose();
            }else{
                txt += "你已经完成了每日跑商第十环 "+完成+"\r\n\r\n";
				txt += "\t\t\t\t#e"+毛球+"剧情完结"+毛球+"#k#n\r\n\r\n";
				txt += ""+商品已售空+"\r\n";
				txt += "恭喜您完成今日所有跑商任务：\r\n";
				txt += "明日再战，我还在这里等你！\r\n";
                cm.sendOk(txt);
                cm.dispose();
			}
        }else if (status == 2) {
			if (cm.getMeso() >= 10000){//这个 是判断条件   对了才输出里面的。
				cm.gainMeso(-10000);	//加减点券
                cm.warp(104010001);
                cm.dispose();
			}else{
                cm.sendOk("金币不足1万");
                cm.dispose();
			}
		} else if (selection == 1) {
			if (!cm.checkNumSpace(4, 3)) {
			cm.sendOk("背包其他栏,空间不足3格");
			cm.dispose();
			return;
		}
            if (cm.haveItem(4000002,30) && cm.haveItem(4000017,20)){
                //cm.gainPS(1);//cm.gainPS(1);  的意思是 你完成跑商第一环的时候给予你 跑商值+1这样你就无法在重复做第一环了。只有凌晨12点刷新才行！
				cm.setBossLog('每日跑商');
                cm.gainItem(4000002, -30);
                cm.gainItem(4000017, -20);
				//cm.gainDY(100);
				//cm.gainMeso(+350000); //加减金币
				cm.gainItem(4000313,5);//黄金枫叶
                cm.gainExp(cm.getLevel()*10000);
				var shi = cm.getHour();
				var fen = cm.getMin();
				var miao = cm.getSec();
				cm.worldMessage(6,"玩家：【"+cm.getName()+"】每日跑商第一环-完成√");
                cm.sendOk("跑商第一环完成√\r\n然后你去射手村 - NPC 阿得拉/进行第二环！");
                cm.dispose();
            }else{
                cm.sendOk("请收集任务物品，集齐够了再来找我提交任务领取丰厚奖励！");
                cm.dispose();
            }
        }else if (selection == 2) {
                cm.sendYesNo("快速传送至所需物品怪物地图：（猪的海岸）  需要花费1万金币哦！");
        }
    }
}
