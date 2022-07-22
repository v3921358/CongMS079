/* ==================
 脚本类型:  任务	    
 脚本版权：游戏创意
 联系扣扣：12384161
 =====================
 */

var inputName;
var needMoneyb = 20;

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
            //if(!cm.haveItem(2350001, 1)){
            /*if(cm.getmoneyb() < needMoneyb){
                cm.sendOk("您账户中没有" + needMoneyb + "元宝，请确认后再来！");
                cm.dispose();
                return;
            }else{*/
                 //cm.sendYesNo("#b请确定是否要使用" + needMoneyb + "元宝为您的账户上增加一个角色位?");
				 cm.sendYesNo("#b请确定是否要为您的账户上增加一个角色位?");
            //}
        } else if(status == 1){
            var ret = cm.addRoleNumber();
            //cm.getPlayer().dropMessage(5, ret);
            if(ret == 0){
                cm.sendOk("#b您当前账号已经扩展至当前服务器设置的最大角色数量！");
            }else if(ret == -1){
                cm.sendOk("#r当前系统出错，请联系管理员！\r\n");
            }else if(ret > 3){
                //cm.gainItem(2350001, -1);
                //cm.setmoneyb(-needMoneyb);
                //cm.喇叭(2, "玩家【" + cm.getPlayer().getName() + "】使用" +needMoneyb+ "元宝成功增加了一个角色位！");
                cm.sendOk("#b成功增加一个角色位，您当前总共可创建【" + ret + "】个角色！");
            }
            cm.dispose();
        }
    }
}
