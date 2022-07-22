/* ==================
 �ű�����: ���߽���	    
 �ű����ߣ���Ҷ   
 ��ϵ��ʽ��1848350048
 =====================
 */
//------------------------------------------------------------------------

var chosenMap = -1;
var monsters = 0;//������ͼ��һ���
var towns = 0;//���д��ͻ������
var bosses = 0;
var fuben = 0;

//------------------------------------------------------------------------

var bossmaps = Array(
		
					
		Array(230040420,380000,"����BOSS                  #r������38���ң�#b"), 
		Array(220080000,380000,"����BOSS                  #r������38���ң�#b"), 
		Array(211042300,380000,"����BOSS                  #r������38���ң�#b"),
        Array(702070400,380000,"��ɮBOSS                  #r������38���ң�#b"),
        Array(541020700,380000,"����BOSS                  #r������38���ң�#b"),		
        Array(105100100,380000,"��ħ����                  #r������38���ң�#b"),			
		Array(240040700,380000,"��������                  #r������38���ң�#b"),
        Array(270000100,380000,"Ʒ����BOSS                #r������38���ң�#b")		
		);

//------------------------------------------------------------------------

var monstermaps = Array(
		Array(104040000,500,"����ѵ����#r��500��ң�#b���� �����ʺ� 1 ~ 15 �����"),
		Array(101010100,580,"��ľ��#r��580��ң�#b ������   �� �ʺ� 8 ~ 15 �����"),
        Array(103000101,680,"����<��1����>#r��680��ң�#b��  ���ʺ� 20 ~ 25 �����"),
		Array(220010500,780,"¶̨����#r��780��ң�#b           �ʺ� 25 ~ 30 �����"),
		Array(101030001,880,"Ұ���������#r��880��ң�#b�� ��  �ʺ� 25 ~ 35 �����"),
		Array(103000105,980,"����<��4����>#r��980��ң�#b��  ���ʺ� 35 ~ 50 �����"), 
		Array(220040000,1180,"ʱ��֮·1#r��1180��ң�#b�� �������ʺ� 45 ~ 60 �����"),
		Array(105040306,1280,"����֮��#r��1280��ң�#b���� �� ���ʺ� 50 ~ 65 �����"),	
		Array(250010304,2280,"�����ܵĵ���#r��2280��ң�#b �� ���ʺ� 55 ~ 75 �����"),
		Array(251010402,2380,"�������ϳ�2#r��2380��ң�#b���� ���ʺ� 65 ~ 75 �����"),	 
		Array(541010010,2580,"���鴬2#r��2580��ң�#b������ �����ʺ� 60 ~ 80 �����"),
		Array(600020300,2680,"���붴Ѩ#r��2680��ң�#b������  ���ʺ� 80 ~ 90 �����"),
		Array(240010500,2780,"ɽ��Ͽ��#r��2780��ң�#b����  �����ʺ� 85 ~ 100 �����"), 
		Array(230040100,2880,"�Ͽ��2#r��2880��ң�#b���� �����ʺ� 90 ~ 100 �����"),
		Array(551030100,2980,"��ɭ�������#r��2980��ң�#b�������ʺ� 95 ~ 120 �����"),
		Array(240030102,3080,"��ʧ������#r��3080��ң�#b��  �����ʺ� 100 ~ 120 �����"),
		Array(240040511,3280,"����������֮��#r��3280��ң�#b  ���ʺ� 105 ~ 130 �����"),		  
		Array(541020000,3580,"��³�����#r��3580��ң�#b����  ���ʺ� 105 ~ 150 �����")
		); 

//------------------------------------------------------------------------

var townmaps = Array(
		//Array(910000000,520,"�����г�#r             ������520��ң�#b"), 
		//Array(701000210,0,"����̨"), 
		Array(1000000,100,"�ʺ絺���ִ�#r         ������1�ٽ�ң�#b"), 
		Array(104000000,500,"�����#r               ������5�ٽ�ң�#b"), 
		Array(100000000,800,"���ִ�#r               ������8�ٽ�ң�#b"), 
		Array(101000000,800,"ħ������#r             ������8�ٽ�ң�#b"), 
		Array(102000000,800,"��ʿ����#r             ������8�ٽ�ң�#b"), 
		Array(103000000,800,"��������#r             ������8�ٽ�ң�#b"), 
		Array(120000000,800,"ŵ����˹����ͷ#r       ������8�ٽ�ң�#b"),
		Array(105040300,1000,"����֮��#r             ������1ǧ��ң�#b"),
		Array(140000000,1000,"���#r                 ������1ǧ��ң�#b"),
		Array(200000000,1000,"���֮��#r             ������1ǧ��ң�#b"),
		Array(211000000,5000,"����ѩ��#r             ������5ǧ��ң�#b"), 
		Array(230000000,1000,"ˮ������#r             ������1ǧ��ң�#b"),  
		Array(222000000,1000,"ͯ����#r               ������1ǧ��ң�#b"), 
		Array(220000000,5000,"��߳�#r               ������5ǧ��ң�#b"),
		Array(701000000,5000,"��������#r             ������5ǧ��ң�#b"),
		Array(250000000,5000,"����#r                 ������5ǧ��ң�#b"), 
		Array(702000000,1000,"������#r               ������1ǧ��ң�#b"), 
		Array(500000000,500,"̩��#r                 ������5�ٽ�ң�#b"),
		Array(260000000,500,"���ﰲ��#r             ������5�ٽ�ң�#b"),  
		Array(600000000,500,"��Ҷ��#r               ������5�ٽ�ң�#b"), 
		Array(240000000,5000,"��ľ��#r               ������5ǧ��ң�#b"),  
		Array(261000000,1000,"�������#r             ������1ǧ��ң�#b"), 
		Array(221000000,1000,"�����������#r         ������1ǧ��ң�#b"), 
		Array(251000000,1000,"�ٲ���#r               ������1ǧ��ң�#b"),
		Array(701000200,10000,"�Ϻ�ԥ԰#r             ������1���ң�#b"),
		Array(550000000,10000,"��¡����#r           ������1���ң�#b"),
		Array(130000000,1000,"ʥ��#r                 ������1ǧ��ң�#b"),
		Array(551000000,1000,"�ʰ��#r               ������1ǧ��ң�#b"),
		Array(801000000,1000,"�Ѻʹ�#r               ������1ǧ��ң�#b"), 
		Array(540010000,1000,"�¼��»���#r           ������1ǧ��ң�#b"),
		Array(541000000,1000,"�¼�����ͷ#r           ������1ǧ��ң�#b"),
		Array(300000000,1000,"����ɭ��#r             ������1ǧ��ң�#b"), 
		Array(270000100,10000,"ʱ�����#r             ������1���ң�#b"), 
		Array(702100000,10000,"�ؾ���#r               ������1���ң�#b"), 
		Array(800000000,10000,"�Ŵ�����#r             ������1���ң�#b"), 
		Array(130000200,10000,"ʥ�ز�·#r             ������1���ң�#b"),
		Array(925020000,1000,"����������#r         ������1ǧ��ң�#b"),
		Array(930000010,1000,"ɭ�����#r             ������1ǧ��ң�#b"),	
		Array(702090101,1000,"Ӣ���#r               ������1ǧ��ң�#b")
		//Array(700000000,10000,"��𽹬#r               ������1���ң�#b")
		//Array(749020000,0,"���쵰���ͼ")
		);

//------------------------------------------------------------------------

var fubenmaps = Array(
        //Array(109080000,0,"��Ҭ��"),
        //Array(109080010,0,"����"),
        Array(109040001,0,"�ߵ�����                #r����ü��ܱң�#k"),
		//Array(109030001,0,"��¥         "),
		//Array(109060000,0,"��ѩ��"),
		Array(109010000,0,"#bѰ��                    #r����ü��ܱң�#k"),
		Array(105040316,10,"#bɭ������                #r����ü��ܱң�#k "),	
		Array(103000900,10,"#b��������                #r����ü��ܱң�#k "),    
		//Array(280020000,10,"��ɽ����"), 
		Array(101000100,10,"#b�̿�����                #r����ü��ܱң�#k ") 											
		);






//------------------------------------------------------------------------

	function start() {
		status = -1;
		action(1, 0, 0);
		}
	function action(mode, type, selection) {
	if (mode == -1) {
		cm.sendOk("#b�õ�,�´��ټ�.");
		cm.dispose();
		} else {
	if (status >= 0 && mode == 0) {
		cm.sendOk("#b�õ�,�´��ټ�.");
		cm.dispose();
		return;
		}
	if (mode == 1) {
		status++;
		} else {
		status--;
		}

//------------------------------------------------------------------------

	if (status == 0) {

   	    var add = "\t\t"+�ʺ�+"  #e#d �� �� �� �� #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n"+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n������������������#r#k\r\n";
		
		add += "#L0##d#e#v1102747#���е�ͼ#v1102747#\t\t#l";//"+��è+"    "+�찮��+"

		add += "#L1##d#e#v1312039#������ͼ#v1312039##l\r\n\r\n";
		
		add += "#L10##d#e#v1001017#������ͼ#v1001017##l"; 
		
		
		add += "         #L4##d#e#v1102319#������ͼ#v1102319##l\r\n\r\n";
        if(cm.getPlayer().getLevel() > 119 ){		
		add += "\t\t\t#L3##d#e#v4000259#<BOSS��ͼ>#v4000259##l\r\n\r\n";
		add += "\t\t\t#L11##r#e#v4000259#<BOSS�ط�>#v4000259##l"; 
		}
		cm.sendSimple (add);    

//------------------------------------------------------------------------
				
	} else if (status == 1) {

	if (selection == 0){
		var selStr = "#d������������������#v1102747#���е�ͼ#v1102747##k#b";
		for (var i = 0; i < townmaps.length; i++) {
		selStr += "\r\n#L" + i + "#" + townmaps[i][2] + "";
		}
		cm.sendSimple(selStr);
		towns = 1;
		}

	if (selection == 1) {
		var selStr = "#d��������  ����#v1312039#�౶����������ͼ#v1312039##k#b\r\n";
		for (var i = 0; i < monstermaps.length; i++) {
		selStr += "\r\n#L" + i + "#" + monstermaps[i][2] + "";
		}
		cm.sendSimple(selStr);
		monsters = 1;
		}

	if (selection == 2) {
		cm.dispose();
		cm.warp(701000210, 0);
		}
		
	if (selection == 10) {
		cm.dispose();
            cm.openNpc(9900004,6006);
		}	

    if (selection == 11 ){
		cm.dispose();
		cm.openNpc(3003332,  "BOSS�ط�");
	}
	if (selection == 3) {
		var selStr = "#k\r\n#d������������������#d#e#v4000259#BOSS��ͼ#v4000259##k#b";
		for (var i = 0; i < bossmaps.length; i++) {
		selStr += "\r\n#L" + i + "#" + bossmaps[i][2] + "";
		}
		cm.sendSimple(selStr);
		bosses = 1;
		}

	if (selection == 4) {
		var selStr = "#d������������������#d#e#v1102319#������ͼ#v1102319##k#b";
		for (var i = 0; i < fubenmaps.length; i++) {
		selStr += "\r\n#L" + i + "#" + fubenmaps[i][2] + "";
		}
		cm.sendSimple(selStr);
		fuben = 1;
		}


//------------------------------------------------------------------------

	} else if (status == 2) {

	if (towns == 1) {
		cm.sendYesNo("��ȷ��Ҫȥ " + townmaps[selection][2] + "?");
		chosenMap = selection;
		towns = 2;

	} else if (monsters == 1) {
		cm.sendYesNo("��ȷ��Ҫȥ " + monstermaps[selection][2] + "?");
		chosenMap = selection;
		monsters = 2;

	} else if (bosses == 1) {
		cm.sendYesNo("��ȷ��Ҫȥ " + bossmaps[selection][2] + "?");
		chosenMap = selection;
		bosses = 2;

	} else if (fuben == 1) {
		cm.sendYesNo("��ȷ��Ҫȥ " + fubenmaps[selection][2] + "?");
		chosenMap = selection;
		fuben = 2;

		}

//----------------------------------------------------------------------

	} else if (status == 3) {

	if (towns == 2) {
		if(cm.getMeso()>=townmaps[chosenMap][1]){
		cm.warp(townmaps[chosenMap][0], 0);
		cm.gainMeso(-townmaps[chosenMap][1]);
		}else{
		cm.sendOk("��û���㹻�Ľ��Ŷ!");
		}
		cm.dispose();

	} else if (monsters == 2) {
		if(cm.getMeso()>=monstermaps[chosenMap][1]){
		cm.warp(monstermaps[chosenMap][0], 0);
		cm.gainMeso(-monstermaps[chosenMap][1]);
		}else{
		cm.sendOk("��û���㹻�Ľ��Ŷ!");
		}
		cm.dispose();

	} else if (bosses == 2) {
		if(cm.getMeso()>=bossmaps[chosenMap][1]){
		cm.warp(bossmaps[chosenMap][0], 0);
		cm.gainMeso(-bossmaps[chosenMap][1]);
		}else{
		cm.sendOk("��û���㹻�Ľ��Ŷ!");
		}
		cm.dispose();

	} else if (fuben == 2) {
		if(cm.getMeso()>=fubenmaps[chosenMap][1]){
		cm.warp(fubenmaps[chosenMap][0], 0);
		cm.gainMeso(-fubenmaps[chosenMap][1]);
		}else{
		cm.sendOk("��û���㹻�Ľ��Ŷ!");
		}
		cm.dispose();

                }

//------------------------------------------------------------------------

		}
		}
		}
var acc = "#fEffect/CharacterEff/1112903/0/0#";//������
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//��ɫ�Ҽ�ͷ
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//��ɫ�Ҽ�ͷ
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//ѡ�����
var ��ɫ�ǵ� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";

var ��è = "#fCharacter/Cape/01102747/icon/#";

var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var Բ�� = "#fUI/UIWindow/Quest/icon3/6#";
var ����new = "#fUI/UIWindow/Quest/icon2/7#";
var ����ne = "#fUI/UIWindow/Quest/icon6/7#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� =  "#fUI/ChatBalloon/37/nw#";
var �� =    "#fUI/ChatBalloon/37/e#";
var �� =    "#fUI/ChatBalloon/37/w#";
var ����è ="#fUI/ChatBalloon/37/s#";
var è���� ="#fUI/ChatBalloon/37/se#";
var è���� ="#fUI/ChatBalloon/37/sw#";
var �ʹڰ� ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var ��ɫ�ǵ� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ��ݮ = "#fUI/GuildMark/Mark/Plant/00003000/1#"; // ��ɫ��ݮ
var ��ݮ1 = "#fUI/GuildMark/Mark/Plant/00003000/10#"; // ����ɫ��ݮ
var ��ݮ2 = "#fUI/GuildMark/Mark/Plant/00003000/11#"; // ��ɫ��ݮ
var ��ݮ3 = "#fUI/GuildMark/Mark/Plant/00003000/15#"; // ��ɫ��ݮ
var ��ݮ4 = "#fUI/GuildMark/Mark/Plant/00003000/3#"; // ��ɫ��ݮ
var ��ݮ5 = "#fUI/GuildMark/Mark/Plant/00003000/8#"; // ��ɫ��ݮ
var С���� = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var ����� = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var С�� = "#fEffect/CharacterEff/1112960/3/0#";  //а��С�� ��С��
var Сˮ�� = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var ��ˮ�� = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var �찮�� ="#fEffect/CharacterEff/1112905/0/1#";
var ���ͼ�� = "#fUI/UIWindow.img/QuestIcon/7/0#";