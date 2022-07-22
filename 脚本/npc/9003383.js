/*
 * 
 * @QILIN
 * @npc羽翼进价+1
 */
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
            cm.sendSimple("\t\t\t\t#r#e"+专用刻印印章+"羽翼进价"+专用刻印印章+" #n\r\n#k#L0##v1102184#羽翼进价一级#n#l         #L1##v1102349#羽翼进价二级#n#l\r\n#L2##v1102604#羽翼进价三级#n#l        #L3##v1102074#羽翼进价四级#n#l\r\n#L4##v1102096#羽翼进价五级#n#l        \r\n"); //#L5##v1102386#羽翼进价六级#n#l\r\n#L6##v1102798#羽翼进价七级#n#l         #r#L7##v1102723#羽翼进价终极#n#l       
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


