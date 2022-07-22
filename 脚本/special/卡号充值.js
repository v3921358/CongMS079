var CY0 = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓";
var CY1 = "┃       - 创意 -       ┃";
var CY2 = "┃ 脚本仿制  　定制脚本 ┃";
var CY3 = "┃ 技术支持 　 游戏顾问 ┃";
var CY4 = "┃ ＷＺ添加　  地图制作 ┃";
var CY5 = "┃ 加盾防御　  售登陆器 ┃";
var CY6 = "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫";
var CY7 = "┃   唯一QQ:12384161    ┃";
var CY8 = "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛";

var money;

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
            var text = "#r本次赞助将用于所有服务器的运营开支!\r\n感谢您的大力支持!!!!!!!\r\n\r\n";
            //text += "#L1##b在线购买赞助充值卡#k#l\r\n";
            text += "#L2##b输入卡号进行兑换#k#l\r\n";
            cm.sendSimple(text);
            //cm.sendSimple(text);
        } else if (status == 1) {
            if(selection == 1){
                cm.openWeb("https://shop108398141.taobao.com/");
                cm.playerMessage(5, "正在打开赞助充值网站！如果没有弹出充值网站请联系群主!"); //个人看见的对话 5红色字 6蓝色字 1为弹窗
                cm.dispose();
                return;
            }else if(selection == 2){
                cm.sendGetText("#b\t\t\t\t\t\t\t\t\r\n#d请输入充值卡号（按CTRL+V进行粘贴):");
            }
        } else if(status == 2){
            money = cm.getText();
            if(money === ""){
                cm.sendOk("您还未输入任何卡号!");
                cm.dispose();
            }
            cm.sendYesNo("是否进行充值卡兑换？");
        } else if(status == 3){
            //0：点券；1：抵用卷；2：元宝
            var ret = cm.donateMoney(money, cm.getPlayer().getAccountID(), cm.getPlayer().getName());
            //cm.getPlayer().dropMessage(5, ret);
            var type = ret[0];
            var value = ret[1];
            if(type == -1 && value == -1){
                cm.sendOk("卡号已经被使用过，无法充值！");
                cm.dispose();
                return;
            }
            if(type == 0 && value == 0){
                cm.sendOk("充值失败，卡号不存在!\r\n");
            }else{
                if(type == 0){
                    cm.gainNX(value);
                    cm.sendOk("充值点券【#r" + value +"#k#l】成功!!!");
                }else if(type == 1){
                    cm.gainDY(value);
                    cm.sendOk("充值抵用券【#r" + value +"#k#l】成功!!!");
                }else if(type == 2){
                    cm.setmoneyb(value);
                    cm.sendOk("充值元宝【#r" + value +"#k#l】成功!!!");
                }
            }
            cm.dispose();
        }
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