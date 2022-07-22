/* ==================
 脚本类型: 跑商
 版权：金华枫叶团队     
 联系扣扣：1848350048
 =====================
 */

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

            if (cm.getBossLog('每日跑商') == 9) {
                txt = "\t\t\t\t#e"+毛球+"每日跑商第十环"+毛球+"#k#n\r\n";
				txt += "收集任务物品：\r\n";
                txt += "#v4001084##z4001084#[#r#c4001084##k/1]#v4000175##z4000175#[#r#c4000175##k/1]"+正在进行中+"\r\n";
                txt += ""+红色箭头+"殴打：(闹钟王+1 鱼王+1)即可获得所需材料#l\r\n\r\n";
				txt += ""+奖励+"\r\n";
				txt += "#v4000313# X 10 #v4000463# X 10 #v4000038# X 2 #v2340000# X 2 #v2049100# X 2\r\n";
				txt += ""+经验值+" X 20000 (随着等级提升Exp)\r\n";
				//txt += ""+点券+" 抵用卷 X 1500\r\n";
				txt += "#L1##b"+红色箭头+"提交完成任务#l\r\n";
                cm.sendSimple(txt);

            }else{
				if (cm.getBossLog('每日跑商') < 9) {
                txt += "\t\t\t\t"+正在进行中+"\r\n";
				txt += "地图告知：\r\n";
			    txt += "玩具城 - NPC 仓库管理员 舍琵\r\n";
				txt += "接第九环跑商任务完成后再来找我接第十环跑商任务哦！\r\n";
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
            }
			
            } else if (status == 2) {
			if (cm.getMeso() > 2000000){
				cm.gainMeso(-2000000);	
                cm.gainItem(4001084,1);
                cm.gainItem(4000175,1);
                cm.dispose();
			}else{
                cm.sendOk("200万金币不足");
                cm.dispose();
			}

        } else if (selection == 1) {
            if (cm.haveItem(4001084,1) && cm.haveItem(4000175,1)){
					if (!cm.checkNumSpace(0, 4)) {
			cm.sendOk("背包空间不足3格");
			cm.dispose();
			return;
		}
				
              cm.setBossLog('每日跑商');
                cm.gainItem(4001084, -1);
                cm.gainItem(4000175, -1);
				//cm.gainDY(1500);
				cm.gainItem(2049100,2);//混沌
				cm.gainItem(2340000,2);//祝福
				cm.gainItem(4000038,2);//圣杯
				cm.gainItem(4000463,10);//国庆纪念币
				cm.gainItem(4000313,10);//黄金枫叶
                cm.gainExp(cm.getLevel()*20000);
				cm.worldMessage(6,"玩家：【"+cm.getName()+"】恭喜您完成所有每日跑商任务√");
                cm.sendOk("恭喜您完成所有每日跑商任务√");
                cm.dispose();
            }else{
                cm.sendOk("请收集任务物品，集齐够了再来找我提交任务领取丰厚奖励！");
                cm.dispose();
            }
			}else if (selection == 2) {
                cm.sendYesNo("快速购买通道：#v4001084#+1#v4000175#+1 需要 X 200万金币哦！");
        }
    }
}
var 奖励 = "#fUI/CashShop/CSDiscount/bonus#";
var 经验值 = "#fUI/UIWindow/QuestIcon/8/0#";
var 金币 = "#fUI/UIWindow/QuestIcon/7/0#";
var 红色箭头 = "#fEffect/CharacterEff/1112908/0/1#";  
var 大红星 = "#fEffect/CharacterEff.img/1022223/1/0#";
var 正在进行中 = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var 毛球 = "#fUI/ChatBalloon.img/pet/12/nw#";
var 点券 = "#fUI/CashShop/CashItem/0#";
var 完成 = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var 警钟 = "#fUI/Basic/BtClaim/normal/0#";
var 商品已售空 = "#fUI/CashShop/GuideWords/0#";