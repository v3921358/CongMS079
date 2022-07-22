
var 表情高兴 = "#fUI/GuildBBS/GuildBBS/Emoticon/Basic/2#";
var status = -1;
var selection;
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 积分 = new Array(1,2);
var 随机积分 = 积分[Math.floor(Math.random() * 积分.length)];
var jilusl = new Array();
var jilupd = new Array();
var jilname= new Array();
var playerChannel = 0;


function start() {
    
	status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }
    if (status == 0) {
		
		
	var i = 0;
	
	
    var text = "";
	var text2 = "";
	
	var xmcserv = Packages.handling.channel.ChannelServer.getAllInstances().iterator();
	while (xmcserv.hasNext()) {
		var xmfwq = xmcserv.next();	
		var cserv1 = xmfwq.getPlayerStorage().getAllCharacters().iterator();		
	    while (cserv1.hasNext()) {	
            i++;
            var mch = cserv1.next();
			//mch.getClient().getChannel();
			jsname = mch.getName();
			mapid = mch.getMapId();
			pdid = mch.getClient().getChannel();
			
			jilname[i]=jsname;
			jilusl[i] = mapid;
			jilupd[i] = pdid;
			
			text += "#b#L"+i+"#"+jsname+""
			for (var j = 16 - jsname.replaceAll("[^\\x00-\\xff]", "**").getBytes().length; j > 0; j--) {
				text += " ";
			}
			var mapName = mch.getMap().getMapName();
			text += "#d"+mapName;
			for (var j = 20 - mapName.replaceAll("[^\\x00-\\xff]", "**").getBytes().length; j > 0; j--) {
				text += " ";
			}
			text += "所在频道："+jilupd[i]+"#l\r\n"//["+mapid+"]显示玩家地图代码
			
			  		
			
			}
			
	    }
        
        text2 += "当前在线人数："+i+"  点击跟踪到\r\n"				
		cm.sendSimple(text2+text);
        
    } else if (status == 1) {
		sele = selection;
		
		cm.跟踪玩家(jilname[sele]);
		//cm.getPlayer().ForcechangeChannel(jilupd[sele]);

        cm.dispose();			 
		
    } else if (status == 2) {
       		
}
}