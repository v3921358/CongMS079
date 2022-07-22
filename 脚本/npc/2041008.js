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

            if (cm.getBossLog('每日跑商') == 8) {
                txt = "\t\t\t\t#e"+毛球+"每日跑商第九环"+毛球+"#k#n\r\n";
				txt += "收集任务物品：\r\n";
                txt += "#v4000265# #z4000265# [#r#c4000265##k/50] "+正在进行中+"\r\n";
                txt += "#L2#"+红色箭头+"快速传送至地图(龙林3) 需要 X 2万金币#l\r\n\r\n";
				txt += ""+奖励+"\r\n";
				txt += "#v4000313#  X 5 #v4000463#  X 5\r\n";
				txt += ""+经验值+" X 10000 (随着等级提升Exp)\r\n";
				txt += "#L1##b"+红色箭头+"提交完成任务#l\r\n";
                cm.sendSimple(txt);
            }else{
				if (cm.getBossLog('每日跑商') < 8) {
			    txt += "\t\t\t\t"+正在进行中+"\r\n";
				txt += "地图告知：\r\n";
				txt += "里恩 - NPC 仓库管理员 普斯拉\r\n";
				txt += "接第八环跑商任务完成后再来找我接第九环跑商任务哦！\r\n";				
                cm.sendOk(txt);
                cm.dispose();
				}else{
				txt += "你已经完成了每日跑商第九环 "+完成+"\r\n\r\n";
                txt += "\t\t\t\t#e"+毛球+"每日跑商第十环"+毛球+"#k#n\r\n\r\n";
				txt += "第十环地图：\r\n";
                txt += "神木村 - NPC 仓库管理员 寇斯库\r\n";
			    txt += "收集任务物品：\r\n";
				txt += "#v4001084# #z4001084# [#r#c4001084##k/1]\r\n";
				txt += "#v4001085# #z4001085#  [#r#c4001085##k/1]\r\n";
				txt += "#r"+警钟+"最后第十环奖励超级丰厚哦！#k#n\r\n";
                cm.sendOk(txt);
                cm.dispose();
				}
            }

        } else if (status == 2) {
			if (cm.getMeso() >= 20000){
				cm.gainMeso(-20000);
                cm.warp(240030300);
                cm.dispose();
			}else{
                cm.sendOk("2万金币不足");
                cm.dispose();
			}
		} else if (selection == 1) {
            if (cm.haveItem(4000265,50)){
				
					if (!cm.checkNumSpace(0, 3)) {
			cm.sendOk("背包空间不足3格");
			cm.dispose();
			return;
		}
cm.setBossLog('每日跑商');
                cm.gainItem(4000265, -50);
				cm.gainItem(4000463,5);//国庆纪念币
				cm.gainItem(4000313,5);//黄金枫叶
                cm.gainExp(cm.getLevel()*10000);
				cm.worldMessage(6,"玩家：【"+cm.getName()+"】每日跑商第九环-完成√");
                cm.sendOk("跑商第九环完成√\r\n然后你去神木村 - NPC 寇斯库/进行第十环！");
                cm.dispose();
            }else{
                cm.sendOk("请收集任务物品，集齐够了再来找我提交任务领取丰厚奖励！");
                cm.dispose();
            }
        }else if (selection == 2) {
                cm.sendYesNo("快速传送至所需物品怪物地图：（龙林3）  需要花费2万金币哦！");
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