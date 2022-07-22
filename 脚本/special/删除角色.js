/* ==================
 脚本类型:  任务	    
 脚本版权：游戏创意
 联系扣扣：12384161
 =====================
 */

var inputName;

var needMoneyb = 10;

function start() {
    status = -1;
    //if(cm.getPlayer().isGM()){
    if(true){
        action(1, 0, 0);
    }else{
        cm.sendOk("暂未开放的功能，如果需要此项服务，请联系管理员，赞助10元宝/个角色，赞助将全部用于服务器运营，感谢支持！");
        cm.dispose();
    }
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
            cm.sendGetText("#r请谨慎使用该功能，一旦删除本账号下指定角色，将不可恢复#l!!#n\r\n\r\n#b\t\t\t\t\t\t\t\t\r\n#d请输入本账号的其它角色名:");
        } else if (status == 1) {
            /*if(cm.getmoneyb < needMoneyb){
                cm.sendOk("您囊中羞涩，请确定有" + needMoneyb + "元宝后再说吧");
                cm.dispose();
                return;
            }*/
            inputName = cm.getText();
            if(inputName === ""){
                cm.sendOk("您还未输入任何角色名称!");
                cm.dispose();
            }
            var currentName = cm.getPlayer().getName();
            if(inputName === currentName){
                cm.sendOk("您不能删除当前使用的这个角色！");
                cm.dispose();
            }else{
                 cm.sendYesNo("请再三思考是否真的要删除角色?");
            }
        } else if(status == 2){
            var ret = cm.deleteRoleByName(inputName);
            //cm.getPlayer().dropMessage(5, ret);
            if(ret >= 0){
                //cm.gainNX(-2000);
                //cm.setmoneyb(-needMoneyb);
                cm.sendOk("#b成功删除当前账号下角色名为["+ inputName +"]的角色，请重新登录查看！");
            }else{
                cm.sendOk("角色名#r【" +inputName+ "】#k#l不存在或者已经被删除!!!\r\n");
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