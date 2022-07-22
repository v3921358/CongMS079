/* ==================
 脚本类型: 角色管理，可以删除角色，转新手，增加角色等
 脚本作者：果子
 联系方式：12384161
 =====================
 */

var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 小兔 = "#fEffect/CharacterEff/1112960/3/0#";  //邪恶小兔 【小】

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
            var text = "\t#d#e这里有很多意想不到的角色相关功能，赶紧试试吧！#k#l\r\n\r\n";
            
            text += "#L1#" +小兔+ " #b删除角色#k#l   ";
            text += "#L2#" +小兔+ " #b增加角色#k#l\r\n\r\n"
            text += "#L3#" +小兔+ " #b角色改名#k#l   ";
			text += "#L4#" +小兔+ " #b回到新手#k#l\r\n\r\n"
            text += "\r\n\r\n"
            cm.sendSimple(text);
        } else if(status == 1){
            switch(selection){
                //cm.getPlayer().dropMessage(5, selection);
                
                case 1:
                    cm.dispose();
                    cm.openNpc(3003332, "删除角色");
                    break;
                case 2:
                    cm.dispose();
                    cm.openNpc(3003332, "增加角色");
                    break;
                case 3:
                    cm.dispose();
                    cm.openNpc(3003332, "角色改名");
                    break;
               case 4:
                    cm.dispose();
                    cm.openNpc(3003332, "回到新手");
                    break;
                
                default:
                    cm.sendOk("未知错误，请联系管理员！");
                    cm.dispose();
                    break;
            }
        }
    }
}
