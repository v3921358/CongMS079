/* ==================
 脚本类型: 开通会员
 脚本作者：果子
 联系方式：804295666
 =====================
 */

var inputName;
var Silver = 30;
var Gold = 60;
var Diamond = 90;

var NoobExchange    = 5220020;  //新手VIP兑换卷
var SilverExchange  = 5220019;  //白银VIP兑换卷
var GoldExchange    = 5220022;  //黄金VIP兑换卷
var DiamondExchange = 5220023;  //钻石VIP兑换卷
var PowerExchange   = 5220024;  //至尊VIP兑换卷

var NoobItemid      = 4310195;  //新手VIP
var SilverItemid    = 4310197;  //白银VIP
var GoldItemid      = 4310198;  //黄金VIP
var DiamondItemid   = 4310199;  //钻石VIP
var PowerItemid     = 4310200;  //至尊VIP

var flag = 0; //标识白银、黄金、钻石等
var ExchangeFlag = 0;//兑换标志

var NoobReward = [
    [4001126, 20], //枫叶
    [4000463, 1], //国庆纪念币
    [4000313, 1],  //黄金枫叶
    [4170005, 1],  //玩具蛋
    [4170006, 1],  //天空蛋
    [4001128, 200], //火药桶
    [4032391, 5], //破碎的碎片1
    [4032392, 5] //破碎的碎片2
];

var SilverReward = [
    [4001126, 50], //枫叶
    [4000463, 2], //国庆纪念币
    [4000313, 2],  //黄金枫叶
    [4170005, 1],  //玩具蛋
    [4170006, 1],  //天空蛋
    [4032391, 10], //破碎的碎片1
    [4032392, 10] //破碎的碎片2

];
var GoldReward = [
    [4001126, 100], //枫叶
    [4000463, 5], //国庆纪念币
    [4000313, 5], //黄金枫叶
    [4170005, 2],  //玩具蛋
    [4170006, 2],  //天空蛋
    [4032391, 20], //破碎的碎片1
    [4032392, 20]  //破碎的碎片2
];
var DiamondReward = [
    [4001126, 200], //枫叶
    [4000463, 8], //国庆纪念币
    [4000313, 8], //黄金枫叶
    [4170005, 3],  //玩具蛋
    [4170006, 3],  //天空蛋
    [4032391, 35], //破碎的碎片1
    [4032392, 35]  //破碎的碎片2

];

//VIP兑换数组
var ExchangeItemid = [
    [NoobExchange,    1, NoobItemid,    7 * 24,  "新手VIP"], //1张新手VIP兑换卷兑换新手VIP7天
    [SilverExchange,  1, SilverItemid,  30 * 24, "白银VIP"], //1张白银VIP兑换卷兑换白银VIP30天
    [GoldExchange,    1, GoldItemid,    30 * 24, "黄金VIP"],
    [DiamondExchange, 1, DiamondItemid, 30 * 24, "钻石VIP"]
];


var RewardArray = [
    [NoobItemid,    50000,  200, NoobReward,  "新手VIP"],
    [SilverItemid,  100000, 300, SilverReward,  "白银VIP"],
    [GoldItemid,    200000, 600, GoldReward,    "黄金VIP"],
    [DiamondItemid, 500000, 900, DiamondReward, "钻石VIP"]
]

function start() {
    status = -1;
    action(1, 0, 0);
}
function action(mode, type, selection) {

    if (mode == -1) {
        cm.dispose();
    }
    else {
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
            text += "#d#e本功能为果果爱冒险会员区，购买不同等级的会员，将得到不同的物品和材料！#k#l\r\n\r\n";
            text += "#L1#" +蓝色箭头+ " #b购买一个月白银会员需" + Silver + "元宝#k#l\r\n\r\n";
            text += "#L2#" +蓝色箭头+ " #b购买一个月黄金会员需" + Gold + "元宝#k#l\r\n\r\n";
            text += "#L3#" +蓝色箭头+ " #b购买一个月钻石会员需" + Diamond + "元宝#k#l\r\n\r\n";
            text += "#L4#" +蓝色箭头+ " #b领取会员奖励#k#l\r\n\r\n";
            text += "#L5#" +蓝色箭头+ " #b使用兑换卷兑换VIP#k#l\r\n";
            cm.sendSimple(text);
        } else if (status == 1) {
            if(selection == 1){
                flag = 1;
                cm.sendYesNo("#b白银会员需要" + Silver + "元宝，是否确认购买？#l!");
            }else if(selection == 2){
                flag = 2;
                cm.sendYesNo("#b黄金会员需要" + Gold + "元宝，是否确认购买？#l!");
            }else if(selection == 3){
                flag = 3;
                cm.sendYesNo("#b钻石会员需要" + Diamond + "元宝，是否确认购买？#l!");
            }else if(selection == 4){
                var RewardText = "确认领取每日会员奖励？\r\n所有VIP每日奖励可免费领取#v5211047#一张，时限使用3小时！\r\n";
                flag = 4
                for(var i = 0; i < RewardArray.length; i++){
                    RewardText += "#v" + RewardArray[i][0] + "# 会员每日奖励：\r\n"
                    RewardText += "#r金币：x " + RewardArray[i][1] + "      ";
                    RewardText += "点券：x " + RewardArray[i][2] + "\r\n#k#l";
                    var Tmp = RewardArray[i][3];
                    for(var j = 0; j < Tmp.length; j++){
                        RewardText += "#v" + Tmp[j][0] + "#x" + Tmp[j][1]+ "  "
                    }
                    RewardText += "\r\n\r\n";
                }
                cm.sendYesNo(RewardText);
            }else if(selection == 5){
                flag = 5;
                var ExchangeText = "在这里可以使用兑换卷来兑换VIP！\r\n\r\n";
                for(var j = 0; j < ExchangeItemid.length; j++){
                    ExchangeText += "#L2" + j + "#" +小兔+ " #b 使用#v" + ExchangeItemid[j][0]+ "#x" + ExchangeItemid[j][1] + "兑换#v" + ExchangeItemid[j][2] + "#" +ExchangeItemid[j][3] / 24 + "天！\r\n"
                }
                cm.sendSimple(ExchangeText);
            }
            //cm.sendYesNo("请再三思考是否真的要花费#r【2000】#k#l点券删除角色，回到解放前?");
        }else if(status == 2){
            var sel = selection;

            //cm.getPlayer().dropMessage(5, sel);
            if(flag == 1){
                buyCard(Silver, SilverItemid, 30);
            }else if(flag == 2){
                buyCard(Gold, GoldItemid, 30);
            }else if(flag == 3){
                buyCard(Diamond, DiamondItemid, 30);
            }else if(flag == 4){
                if(cm.getInventory(4).isFull(6)){
                    cm.sendOk("为了保证领取正常福利，#b其它#k#l栏至少要有#r6#k#l个格子，请检查！");
                    cm.dispose();
                    return;
                }
                SubmitReward();
            }else if(flag == 5){
                //进入兑换VIP逻辑
                
                //cm.getPlayer().dropMessage(5, sel);
                switch (sel) {
                    case 20:
                        ExchangeFlag = 20;
                        cm.sendYesNo("#b确认使用#v" +NoobExchange + "#来兑换#v" + NoobItemid + "#7天？#l!");
                        break;
                    case 21:
                        ExchangeFlag = 21;
                        cm.sendYesNo("#b确认使用#v" +SilverExchange + "#来兑换#v" + SilverItemid + "#30天？#l!");
                        break;
                    case 22:
                        ExchangeFlag = 22;
                        cm.sendYesNo("#b确认使用#v" +GoldExchange + "#来兑换#v" + GoldItemid + "#30天？#l!");
                        break;
                    case 23:
                        ExchangeFlag = 23;
                        cm.sendYesNo("#b确认使用#v" +DiamondExchange + "#来兑换#v" + DiamondItemid + "#30天？#l!");
                        break;
                    default:
                        cm.sendOk("脚本出错，请联系管理员！");
                        cm.dispose();
                        break
                }
            }
        }else if(status == 3){
            //cm.getPlayer().dropMessage(5, ExchangeFlag);
            switch (ExchangeFlag){
                case 20:
                    SubmitExchange(NoobExchange, NoobItemid);
                    break;
                case 21:
                    SubmitExchange(SilverExchange, SilverItemid);
                    break;
                case 22:
                    SubmitExchange(GoldExchange, GoldItemid);
                    break;
                case 23:
                    SubmitExchange(DiamondExchange, DiamondItemid);
                    break;
                default:
                    cm.sendOk("脚本出错，请联系管理员！");
                    cm.dispose();
                    break;
            }
        }
    }
}

function SubmitExchange(itemidBefore,itemidAfter){
    var index = 0;
    for(index == 0; index < ExchangeItemid.length; index++){
        if((ExchangeItemid[index][0] == itemidBefore) && (ExchangeItemid[index][2] == itemidAfter)){
            if(!cm.haveItem(ExchangeItemid[index][0], ExchangeItemid[index][1])){
                cm.sendOk("您还没有#v"+ExchangeItemid[index][0]+"#x"+ExchangeItemid[index][1]+"，不能兑换#v" + ExchangeItemid[index][2] + "#！");
                cm.dispose();
            }else{
                if(cm.haveItem(ExchangeItemid[index][2])){
                    cm.sendOk("您已经有兑换过#v" +ExchangeItemid[index][2]+ "#了，不需要重复兑换！");
                    break;
                }else{
                    cm.gainItem(ExchangeItemid[index][2], 1, ExchangeItemid[index][3]);
                    cm.gainItem(ExchangeItemid[index][0], -ExchangeItemid[index][1]);
                    cm.dispose();
                    break;
                }
            }
        }
    }
    cm.dispose();
}

function SubmitReward(){
    var i = 0;
    var j = 0;
    var RewardFlag = false;

    for(i = 0; i < RewardArray.length; i++ ){
        if (cm.haveItem(RewardArray[i][0])){
            if(cm.getBossLog(RewardArray[i][4]) >=1){
                cm.sendOk("你今天已经领取过会员奖励了！");
                cm.dispose();
                continue;
            }else{
                cm.setBossLog(RewardArray[i][4]);
            }
            RewardFlag = true;
            cm.gainMeso(RewardArray[i][1]); //金币
            //cm.getPlayer().dropMessage(6, "金币：+" + RewardArray[i][1]);
            cm.gainNX(RewardArray[i][2]);   //点券
            cm.getPlayer().dropMessage(6, "点券：+" + RewardArray[i][2]);
            var TmpArray = RewardArray[i][3];//奖励
            for(j = 0; j< TmpArray.length; j++ ){
                cm.gainItem(TmpArray[j][0], TmpArray[j][1]);
            }
        }
    }
    if(RewardFlag == true){
        
        if(!cm.canHold(5211047, 1)){
            cm.sendOk("背包的特殊栏位必须至少有1个格子！");
            cm.dispose();
            return;
        }
        cm.喇叭(2, "土豪玩家【" + cm.getPlayer().getName()+ "】领取了每日会员奖励！")
        cm.sendOk("成功领取会员奖励！");
        if (!cm.haveItem(5211047)){
            cm.gainItem(5211047,1,3);
        }
    }else{
        cm.sendOk("您还未开通会员权限，请开通后再领取！")
    }
    cm.dispose();
}

function buyCard(needMoney, itemid, timeout){
    if(!cm.canHold(itemid, 1)){
        cm.sendOk("背包的其它栏位必须至少有1个格子！");
        cm.dispose();
        return;
    }
    if(cm.getmoneyb() < needMoney){
        cm.sendOk("您囊中羞涩，请确定有"+needMoney+"元宝后再说吧");
        cm.dispose();
        return;
    }
     if (!cm.haveItem(itemid)){
        cm.setmoneyb(-needMoney);
        cm.gainItem(itemid, 1, timeout * 24);//给予会员卡时间
        cm.sendOk("恭喜您成功获得#v"+itemid+"#，道具放在其它栏，请小心不要丢弃，否则要重新购买哦！");
        cm.喇叭(2, "哦嚯~~土豪玩家【" + cm.getPlayer().getName()+ "】通过拍卖开通了会员功能！");
        cm.dispose();
    }else{
        cm.sendOk("您已经有#v"+itemid+"#，请不要重复购买!");
        cm.dispose();
    }

}
var acc = "#fEffect/CharacterEff/1112903/0/0#";//红桃心
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//红色右箭头
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//选择道具
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 圆形 = "#fUI/UIWindow/Quest/icon3/6#";
var 美化new = "#fUI/UIWindow/Quest/icon2/7#";
var 美化ne = "#fUI/UIWindow/Quest/icon6/7#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 草莓 = "#fUI/GuildMark/Mark/Plant/00003000/1#"; // 红色草莓
var 草莓1 = "#fUI/GuildMark/Mark/Plant/00003000/10#"; // 淡蓝色草莓
var 草莓2 = "#fUI/GuildMark/Mark/Plant/00003000/11#"; // 紫色草莓
var 草莓3 = "#fUI/GuildMark/Mark/Plant/00003000/15#"; // 白色草莓
var 草莓4 = "#fUI/GuildMark/Mark/Plant/00003000/3#"; // 黄色草莓
var 草莓5 = "#fUI/GuildMark/Mark/Plant/00003000/8#"; // 绿色草莓
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 大黄星 = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var 小兔 = "#fEffect/CharacterEff/1112960/3/0#";  //邪恶小兔 【小】
var 小水滴 = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var 大水滴 = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var 红爱心 ="#fEffect/CharacterEff/1112905/0/1#";
var 金币图标 = "#fUI/UIWindow.img/QuestIcon/7/0#";
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";