var passwords = new Array(5);
var str;
var selectn1;
var selectn2;
var selectn3;
var selectn4;
var selectn5;
var selectlog;
var postrue = 0;
var seltrue = 0;
var stars = "";
var unlock = 3;
var unlocklog = new Array("");
var str_unlocklog = "";
var num = 0;
var gg = 0;
var it = 0;

function start() {
	a = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if(mode == -1){
	  cm.dispose();
	}else{
	  if(mode == 1)
		a++;
	  else
		a = -1;
	if (a == -1){
      cm.dispose();
	  
	}else if (a == 0){
		cm.sendNext("找到了找到了……。點下一步給你說明解除密碼的關鍵步驟。")
    }else if (a == 1){
      cm.sendYesNo(" - 保險庫密碼鎖任務說明：\r\n\r\n　　密碼鎖由四個不重複的數字組成，在對話框內依次選擇正確的數字，密碼鎖就會被打開，密碼鎖的密碼是由0~9所組成，並且四個數字之間不會重複。\r\n\r\n#r#e任務簡介：#n#k\r\n　　開始遊戲後NPC隨機生成密碼，玩家會獲得3次猜對這組密碼的機會，玩家按照提示依次選擇5個數字，組成四位數的密碼，確認之後會得到提示：\r\n\r\n#r★#k表示答案裡有幾個，數字正確且位置正確\r\n#r☆#k表示答案裡有幾個，數字正確但位置不正確\r\n\r\n#r#e注意事項：#n\r\n　如關閉了NPC對話，那麼再次點擊NPC開始遊戲密碼會改變。 \r\n 且如果機會都用光還是回答失敗，那會有小小的懲罰！！ \r\n獎品隨機的~~~");
      for (var i = 0; i<5; i++) 
      {
        passwords[i] = Math.floor(Math.random()*10);
        for (var j = 0; j < i; j++) {
          if (passwords[j] == passwords[i]) {
            i--;
            break;
          }
        }
      }
	  }else if (a == 2){
      str = "請猜猜倉庫密碼的第一位數字\r\n\r\n";
      postrue = seltrue = 0;
      for (var i = 0; i < 10; i++) {
        str += "#L" + i + "#" + i;
      }
	  if (cm.getPlayer().isGM()) {
		  str += "#l\r\n\r\n#r★GM本次密碼貼心提示☆:"+passwords+"#k#l\r\n\r\n\r\n已選擇的數字：\r\n#n剩餘次數：#r" + unlock + "#k\r\n記錄：\r\n";
	  } else {
		  str += "#l\r\n\r\n\r\n已選擇的數字：\r\n#n剩餘次數：#r" + unlock + "#k\r\n記錄：\r\n";
	  }
      
      cm.sendSimple(str + str_unlocklog);
	  }else if (a == 3){
      str = "請猜猜倉庫密碼的第二位數字\r\n\r\n";
      selectn1 = selection;
      for (var i = 0; i < 10; i++) {
        if (i != selectn1)
          str += "#L" + i + "#" + i;
      }
      str += "#l";
      selectlog = "\r\n\r\n\r\n已選擇的數字：#r" + selectn1;
      selectlog += "\r\n#k剩餘次數：#r" + unlock + "#k\r\n記錄：\r\n";
      cm.sendSimple(str + selectlog + str_unlocklog);
	  }else if (a == 4){
      str = "請猜猜倉庫密碼的第三位數字\r\n\r\n";
      selectn2 = selection;
      for (var i = 0; i < 10; i++) {
        if (i != selectn1 && i != selectn2)
          str += "#L" + i + "#" + i;
      }
      str += "#l";
      selectlog = "\r\n\r\n\r\n已選擇的數字：#r" + selectn1 + " " + selectn2;
      selectlog += "\r\n#k剩餘次數：#r" + unlock + "#k\r\n記錄：\r\n";
      cm.sendSimple(str + selectlog + str_unlocklog);
	  }else if (a == 5){
      str = "請猜猜倉庫密碼的第四位數字\r\n\r\n";
      selectn3 = selection;
      for (var i = 0; i < 10; i++) {
        if (i != selectn1 && i != selectn2 && i != selectn3)
          str += "#L" + i + "#" + i;
      }
      str += "#l";
      selectlog = "\r\n\r\n\r\n已選擇的數字：#r" + selectn1 + " " + selectn2 + " " + selectn3;
      selectlog += "\r\n#k剩餘次數：#r" + unlock + "#k\r\n記錄：\r\n";
      cm.sendSimple(str + selectlog + str_unlocklog);
	  }else if (a == 6){
      str = "請猜猜倉庫密碼的第五位數字\r\n\r\n";
      selectn4 = selection;
      for (var i = 0; i < 10; i++) {
        if (i != selectn1 && i != selectn2 && i != selectn3 && i != selectn4)
          str += "#L" + i + "#" + i;
      }
      str += "#l";
      selectlog = "\r\n\r\n\r\n已選擇的數字：#r" + selectn1 + " " + selectn2 + " " + selectn3 + " " + selectn4;
      selectlog += "\r\n#k剩餘次數：#r" + unlock + "#k\r\n記錄：\r\n";
      cm.sendSimple(str + selectlog + str_unlocklog);
	  }else if (a == 7){
      selectn5 = selection;
      selectlog = "\t\t\t\t確定以這組數字開鎖麼? #r\r\n\r\n\t\t\t\t\t   " + selectn1 + " " + selectn2 + " " + selectn3 + " " + selectn4 + " " + selectn5;
      selectlog += "\r\n#k剩餘次數：#r" + unlock + "#k\r\n記錄：\r\n";
      cm.sendYesNo(selectlog + str_unlocklog);
    }else if (a == 8){
      for (var i=0; i<5; i++){
        if (passwords[i] == selectn1) {
          if (i == 0)
            postrue += 1;
          else
            seltrue += 1;
        } else if (passwords[i] == selectn2) {
          if (i == 1)
            postrue += 1;
          else
            seltrue += 1;
        } else if (passwords[i] == selectn3) {
          if (i == 2)
            postrue += 1;
          else
            seltrue += 1;
        } else if (passwords[i] == selectn4) {
          if (i == 3)
            postrue += 1;
          else
            seltrue += 1; 
        } else if (passwords[i] == selectn5) {
          if (i == 4)
            postrue += 1;
          else
            seltrue += 1;
        }    
      }
	  num++;
		  unlocklog.push("第"+ num +"次選擇的數字：" + selectn1 + selectn2 + selectn3 + selectn4 + selectn5 +"　#r" + postrue + "★  " + seltrue + "☆#k\r\n");
      str_unlocklog = "";
      for (var i = 0; i < unlocklog.length; i++)
        str_unlocklog += unlocklog[i];
      if (postrue == 5) {
		gg = Math.floor(Math.random() * 6)+1;
		if (gg >= 2) {
			it = 4000019;
		} else {
			it = 5220040;
		}
        cm.sendNext("恭喜你,開鎖成功!\r\n\r\n您的記錄：\r\n" + str_unlocklog);
        cm.gainItem(it, 1);
		cm.worldMessage("『保險箱密碼』：恭喜玩家:" + cm.getChar().getName() + " 使用了"+num+"次成功開啟保險箱密碼看來是一個很強的解鎖達人!!!");
      } else {
        unlock -=1;
        if (unlock >= 1)
          a = 1;
        cm.sendNext("真遺憾,開鎖失敗!");
      }
    }else if (a == 9){
      if (postrue != 5) {
        cm.sendOk("您的開鎖機會已經用完了~\r\n\r\n正確密碼為：" + passwords + "\r\n您的記錄：\r\n" + str_unlocklog);
		//cm.worldMessage("『保險箱密碼』：恭喜玩家:" + cm.getChar().getName() + " 使用完機會了並失敗開啟保險箱密碼看來給予一個獎勵死亡一次!!!");
		cm.getPlayer().setHp(0);
        cm.getPlayer().updateSingleStat(Packages.client.MapleStat.HP, 0);
        cm.dispose();
      } else {
        cm.sendOk("歡迎您再來挑戰~");
        cm.dispose();
      }
      
	  }//status
	}
}