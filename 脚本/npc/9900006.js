
var status = 0;
var jobName;
var job;

function start() {
 //cm.givePartyExp(611111500);//给队伍所有人经验
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.sendOk("天气很好哦~~如果你改变想法记的随时来找我。祝你好运！");
        cm.dispose();
    } else {
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            cm.sendNext("嗨，我是 #b骑士团转职官#k 我可以帮助你进行转职哦~~！");
        } else if (status == 1) {
            if(cm.getJob() >= 1000 && cm.getJob() <= 1520){
                cm.sendNext("哇，你是骑士团的一员，我很高兴为你服务哦！！！");
                status = 160;
                return;
            }else {
				
				cm.sendNext("您不是骑士团，我只为骑士团服务哦！！！");
				cm.dispose();
			}
            
            

        }     else if (status == 154) {
           
            cm.sendSimple("怎么样~~在下面选择一种你所喜欢的职业吧！#b\r\n#L6#魂骑士#l #L7#炎术士#l #L8#风灵使者#l #L9#夜行者#l ");


        } else if (status == 155) {
            if (selection == 6) {
                jobName = "魂骑士";
                job = 1100;
            }if (selection == 7) {
                jobName = "炎术士";
                job = 1200;
            }if (selection == 8) {
                jobName = "风灵使者";
                job = 1300;
            }if (selection == 9) {
                jobName = "夜行者";
                job = 1400;
            }if (selection == 10) {
                jobName = "奇袭者";
                job = 1500;
            }
            cm.sendYesNo("不错的选择哦，确定要成为一名 #b[" + jobName + "] #k吗？"); 
        }  else if (status == 161) {
            if(cm.getJob() == 1000 && cm.getPlayer().getLevel()>=10){
                cm.sendSimple("看起来你还是一个初心者,快选择一个适合自己的职业吧!#b\r\n#L0#魂骑士#l #L1#炎术士#l #L2#风灵使者#l #L3#夜行者#l #L4#奇袭者#l#k");
            }
			else if(parseInt(cm.getJob() / 100) >10 && cm.getPlayer().getLevel()>=30 && cm.getJob()%100 == 0){
                cm.sendYesNo("您真的确定要花费500万金币，进行第二次转职了吗？");
            }else if(parseInt(cm.getJob() / 100) >10 && cm.getPlayer().getLevel()>=70 && cm.getJob()%10 == 0){
                cm.sendYesNo("您真的确定要花费3000万金币，进行第三次转职了吗？");
            }else{
                cm.sendOk("您目前的条件不能使用我的服务哦!\r\n骑士团转职等级分别是：10级-30级-70级\r\n骑士团最高是三转，没有四转哦！");
                cm.dispose();
            }
        } else if (status == 162) {
            if(cm.getJob() == 1000 && cm.getPlayer().getLevel()>=10){
                if (selection == 0) {
                    job = 1100;
                } else if (selection == 1) {
                    job = 1200;
                } else if (selection == 2) {
                    job = 1300;
                } else if (selection == 3) {
                    job = 1400;
                } else if (selection == 4) {
                    job = 1500;
                }
                cm.changeJob(job);
                
                
                cm.sendOk("转职成功！加油锻炼，当你变的强大的时候记的来找我哦！");
            } else if(parseInt(cm.getJob() / 100) >10 && cm.getPlayer().getLevel()>=30 && cm.getJob()%100 == 0){
				
				if(cm.getMeso() < 5000000) {
                cm.sendOk("抱歉您的金币不足500万，请凑足了再来！");
                cm.dispose();}
                else{
				cm.gainMeso(-5000000);
                cm.changeJob(cm.getJob()+10);
                cm.sendOk("转职成功！加油锻炼，当你变的强大的时候记的来找我哦！");
				}
            } else if(parseInt(cm.getJob() / 100) >10 && cm.getPlayer().getLevel()>=70 && cm.getJob()%10 == 0){
               if(cm.getMeso() < 30000000) {
                cm.sendOk("抱歉您的金币不足3000万，请凑足了再来！");
                cm.dispose();}
                else{ 
				cm.gainMeso(-30000000);
                cm.getPlayer().gainAp(5);
                cm.changeJob(cm.getJob()+1);
                cm.sendOk("转职成功！希望您以后的冒险之路顺利!");
				}
            }
            cm.dispose();
        }    else {
            cm.dispose();
        }  

    }
}
