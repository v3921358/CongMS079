/* ==================
 脚本类型: 跑商
 版权：金华枫叶团队     
 联系扣扣：1848350048
 =====================
 */
var 奖励 = "#fUI/CashShop/CSDiscount/bonus#";
var 经验值 = "#fUI/UIWindow/QuestIcon/8/0#";
var 金币 = "#fUI/UIWindow/QuestIcon/7/0#";
var 红色箭头 = "#fEffect/CharacterEff/1112908/0/1#";  //彩光3
var 大红星 = "#fEffect/CharacterEff.img/1022223/1/0#";
var 正在进行中 = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var 毛球 = "#fUI/ChatBalloon.img/pet/12/nw#";
var 点券 = "#fUI/CashShop/CashItem/0#";
var 完成 = "#fUI/UIWindow/Quest/Tab/enabled/2#";
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

            if (cm.getBossLog('每日跑商') == 2) {
                txt = "\t\t\t\t#e"+毛球+"每日跑商第三环"+毛球+"#k#n\r\n";
				txt += "收集任务物品：\r\n";
                txt += "#v4000190# #z4000190# [#r#c4000190##k/25] #v4000191# [#r#c4000191##k/25] "+正在进行中+"\r\n";
				txt += "#L2#"+红色箭头+"快速传送至地图(徐州岔道) 需要 X 2万金币#l\r\n\r\n";
				txt += ""+奖励+"\r\n";
				txt += "#v4000313#  X 5\r\n";
				txt += ""+经验值+" X 10000 (随着等级提升Exp)\r\n";
				//txt += ""+点券+" 抵用卷 X 300\r\n";
				txt += "#L1##b"+红色箭头+"提交完成任务#l\r\n";
                cm.sendSimple(txt);
            }else{
				if (cm.getBossLog('每日跑商') < 2) {
				txt += "\t\t\t\t"+正在进行中+"\r\n";
				txt += "地图告知：\r\n";
				txt += "射手村 - NPC 阿得拉\r\n";// 9209000
				txt += "接第二环跑商任务完成后再来找我接第三环跑商任务哦！\r\n";
                cm.sendOk(txt);
                cm.dispose();
				}else{
				txt += "你已经完成了每日跑商第三环 "+完成+"\r\n\r\n";
				txt += "\t\t\t\t#e"+毛球+"每日跑商第四环"+毛球+"#k#n\r\n\r\n";
				txt += "第四环地图：\r\n";
                txt += "勇士部落 - NPC 仓库管理员 王先生\r\n";
				txt += "收集任务物品：\r\n";
				txt += "#v4000043# #z4000043# [#r#c4000043##k/50]\r\n";
                cm.sendOk(txt);
                cm.dispose();
				}
				
				
            }

        }else if (status == 2) {
			if (cm.getMeso() >= 20000){
				cm.gainMeso(-20000);
                cm.warp(701010300);
                cm.dispose();
			}else{
                cm.sendOk("2万金币不足");
                cm.dispose();
			}
		}else if (selection == 1) {
            if (cm.haveItem(4000190,25) && cm.haveItem(4000191,25)){
				if (!cm.checkNumSpace(0, 3)) {
			cm.sendOk("背包空间不足3格");
			cm.dispose();
			return;
		}              
                cm.setBossLog('每日跑商');
                cm.gainItem(4000190, -25);
				cm.gainItem(4000191, -25);
				cm.gainItem(4000313,5);//黄金枫叶
                cm.gainExp(cm.getLevel()*10000);
				cm.worldMessage(6,"玩家：【"+cm.getName()+"】每日跑商第三环-完成√");
                cm.sendOk("跑商第三环完成√\r\n然后你去勇士部落 - NPC 仓库管理员 王先生/进行第四环！");
                cm.dispose();
            }else{
                cm.sendOk("请收集任务物品，集齐够了再来找我提交任务领取丰厚奖励！");
                cm.dispose();
            }
        }else if (selection == 2) {
                cm.sendYesNo("快速传送至所需物品怪物地图：（徐州岔道）  需要花费2万金币哦！");
        }
    }
}
