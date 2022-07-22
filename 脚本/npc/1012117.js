/* 
	
*/
var status = -1;
var hair_Colo_new;
var random = java.lang.Math.floor(Math.random() * 9 + 1);

function action(mode, type, selection) {
    if (mode == 0) {
        cm.dispose();
        return;
    } else {
        status++;
    }
    if (status == 0) {
        cm.sendSimple("嗨，我是爱德华如果你有 #b#v5150040##k, 我就可以施展我的技术为了打造稀有的珍贵发型。\r\n\#L0#使用 #v5150040#随机皇家发型#l\r\n#b#L5#使用 #v5150040#随机皇家理发颜色#k\r\n#r注意：皇家发型要染颜色仅限这里，其它地方不支持染色");
    } else if (status == 1) {
        if (selection == 0) {
            var hair = cm.getPlayerStat("HAIR");
            hair_Colo_new = [];
            if (cm.getPlayerStat("GENDER") == 0) {
				hair_Colo_new = [40000,40010,40020,40030,40040,40050,40060,40090,40100,40110,40250,40260,40270,40280,40340,40350,40360,40370,40390,40430,40470,40480,40490];
		
            } else {
				hair_Colo_new = [41060,41070,41080,41090,41100,41110,41120,41130,41140,41150,41160,41190,41200,41210,41340,41350,41360,41400,41410,41420,41430,41440,41460,41550,41570,41580,42060,42070];
					
            }
            for (var i = 30000; i < hair_Colo_new.length; i++) {
                hair_Colo_new[i] = hair_Colo_new[i] + (hair % 10);
            }
            cm.sendYesNo("使用皇家理发券，可以随机更换发型。你真的要使用#b#t05150040##k，更换发型吗？");
        }else if(selection == 5){
			
			    var suiji = Math.floor(Math.random() * 8);
				if (cm.haveItem(5150040)){
                cm.gainItem(5150040, -1);
			    haircolor = Array();
                var current = parseInt(cm.getPlayer().getHair()/10)*10;
                for(var i = 0; i < 8; i++)
                haircolor.push(current + i);
                
				cm.setHair(haircolor[suiji]);
				//cm.sendOk(haircolor);验证输出发型代码
				 cm.sendOk("好了,让朋友们赞叹你的新发型吧!");
				 cm.dispose();
				 return;
				 } else{
                    cm.sendOk("您貌似没有#b#t5150040##k..");
                 }
			
		}
    } else if (status == 2) {
        if (cm.setRandomAvatar(5150040, hair_Colo_new) == 1) {
            cm.sendOk("好了,让朋友们赞叹你的新发型吧!");
        } else {
            cm.sendOk("必须要有皇家理发券，我才能为你理发。");
        }
        cm.safeDispose();
    }
}
