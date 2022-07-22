var FY0 = "┏━━━━━━━━━━━┓";
var FY1 = "┃       - 枫叶 -       ┃";
var FY2 = "┃ 脚本仿制  　定制脚本 ┃";
var FY3 = "┃ 技术支持 　 游戏顾问 ┃";
var FY4 = "┃ ＷＺ添加　  地图制作 ┃";
var FY5 = "┃ 加盾防御　  售登陆器 ┃";
var FY6 = "┣━━━━━━━━━━━┫";
var FY7 = "┃ 唯一QQ:1848350048    ┃";
var FY8 = "┗━━━━━━━━━━━┛";
var 专用刻印印章 = "#fItem/Consume/0259.img/02590004/info/icon#";
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
			//#L6##v1102798#羽翼进价七级#n#l         #r#L7##v1102723#羽翼进价终极#n#l 
            var add = "\t\t\t\t#r#e   "+专用刻印印章+"羽翼进价"+专用刻印印章+" #n\r\n       #k#L0##v1102184#进价一级#n#l             #L1##v1102349#进价二级#n#l\r\n       #L2##v1102604#进价三级#n#l			#L3##v1102074#进价四级#n#l\r\n       #L4##v1102096#进价五级#n#l		 	 #L5##v1103029#进价六级#n#l\r\n";       
		    //#L5##v1103029#进价六级#n#l
			if (cm.getPlayer().getLevel() > 199) {
			add += "       #L6##v1103026#进价七级#n#l             #L7##v1103027#进价八级#n#l\r\n";
			add += "                  #L8##v1103025#进价九级#v1103025##n#l\r\n";
			}
			
			cm.sendSimple(add);
		} else if (status == 1) {
            if (selection == 0) {//副本传送
			cm.dispose();
             cm.openNpc(9310070,100);
            } 
			else if (selection == 1) {//副本兑换奖励
			cm.dispose();
              cm.openNpc(9310070,200);
            }
			else if(selection == 2){
				cm.dispose();
                cm.openNpc(9310070,300);
			}
				else if(selection == 3){
					cm.dispose();
                cm.openNpc(9310070,400);
			}
			
				else if(selection == 4){
					cm.dispose();
                cm.openNpc(9310070,500);
				}
				
				else if(selection == 5){
					cm.dispose();
                cm.openNpc(9310070,600);
				}
				
				else if(selection == 6){
					cm.dispose();
                cm.openNpc(9310070,700);
				}
				
				else if(selection == 7){
					cm.dispose();
                cm.openNpc(9310070,800);
				}
				
				else if(selection == 8){
					cm.dispose();
                cm.openNpc(9310070,900);
        }
        }
    }
}


