package com.jshx.commonTrouble.entity;

import java.text.DecimalFormat;

public class KsjcBean {
	private String szzname;
	private int data1;
	private int data2;
	private int data3;
	private int data4;
	private int data5;
	private int data6;
	private int data7;
	private int data8;
	private int data9;
	private int data10;
	private int data11;
	private int data12;
	private int data13;
	private String data14;
	private String data15;
	private int data16;
	private int data17;
	private String data18;
	private int data19;
	private String data20;
	private int data21;
	private int data22;
	private int data23;
	private int data24;
	private int data25;
	private int data26;
	private int data27;
	private int data28;
	private int data29;
	private String data30;
	private String data31;
	private int data32;
	private int data33;
	private String data34;
	private int data35;
	private String data36;
	private int data37;
	private int data38;
	private int data39;
	private int data40;
	private int data41;
	private int data42;
	private int data43;
	private int data44;
	private int data45;
	private String data46;
	private String data47;
	private int data48;
	private int data49;
	private String data50;
	private int data51;
	private String data52;
	private int data53;
	private int data54;
	private int data55;
	private int data56;
	private int data57;
	private int data58;
	private int data59;
	private int data60;
	private int data61;
	private String data62;
	private String data63;
	private int data64;
	private int data65;
	private String data66;
	private int data67;
	private String data68;
	private int data69;
	private int data70;
	private int data71;
	private int data72;
	private int data73;
	private int data74;
	private int data75;
	private int data76;
	private int data77;
	private String data78;
	private String data79;
	private int data80;
	private int data81;
	private int data82;
	private int data83;
	private int data84;
	private String data85;
	private int data86;
	private int data87;
	private int data88;
	private int data89;
	private int data90;
	private String data91;
	private int data92;
	private int data93;
	private int data94;
	private int data95;
	private int data96;
	private String data97;
	public String getSzzname() {
		return szzname;
	}
	public void setSzzname(String szzname) {
		this.szzname = szzname;
	}
	public int getData1() {
		return data1;
	}
	public void setData1(int data1) {
		this.data1 = data1;
	}
	public int getData2() {
		return data2;
	}
	public void setData2(int data2) {
		this.data2 = data2;
	}
	public int getData3() {
		return data3;
	}
	public void setData3(int data3) {
		this.data3 = data3;
	}
	public int getData4() {
		return data4;
	}
	public void setData4(int data4) {
		this.data4 = data4;
	}
	public int getData5() {
		return data5;
	}
	public void setData5(int data5) {
		this.data5 = data5;
	}
	public int getData6() {
		return data6;
	}
	public void setData6(int data6) {
		this.data6 = data6;
	}
	public int getData7() {
		return data7;
	}
	public void setData7(int data7) {
		this.data7 = data7;
	}
	public int getData8() {
		return data8;
	}
	public void setData8(int data8) {
		this.data8 = data8;
	}
	public int getData9() {
		return data9;
	}
	public void setData9(int data9) {
		this.data9 = data9;
	}
	public int getData10() {
		return data10;
	}
	public void setData10(int data10) {
		this.data10 = data10;
	}
	public int getData11() {
		return data5-data8;
	}
	public void setData11(int data11) {
		this.data11 = data11;
	}
	public int getData12() {
		return data6-data9;
	}
	public void setData12(int data12) {
		this.data12 = data12;
	}
	public int getData13() {
		return data7-data10;
	}
	public void setData13(int data13) {
		this.data13 = data13;
	}
	public String getData14() {
		if((data6)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data9)*100/(data6));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData14(String data14) {
		this.data14 = data14;
	}
	public String getData15() {
		if((data5)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data8)*100/(data5));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData15(String data15) {
		this.data15 = data15;
	}
	public int getData16() {
		return data16;
	}
	public void setData16(int data16) {
		this.data16 = data16;
	}
	public int getData17() {
		return data17;
	}
	public void setData17(int data17) {
		this.data17 = data17;
	}
	public String getData18() {
		if((data16)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data17)*100/(data16));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData18(String data18) {
		this.data18 = data18;
	}
	public int getData19() {
		return data19;
	}
	public void setData19(int data19) {
		this.data19 = data19;
	}
	public String getData20() {
		if((data16)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data19)*100/(data16));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData20(String data20) {
		this.data20 = data20;
	}
	public int getData21() {
		return data21;
	}
	public void setData21(int data21) {
		this.data21 = data21;
	}
	public int getData22() {
		return data22;
	}
	public void setData22(int data22) {
		this.data22 = data22;
	}
	public int getData23() {
		return data23;
	}
	public void setData23(int data23) {
		this.data23 = data23;
	}
	public int getData24() {
		return data24;
	}
	public void setData24(int data24) {
		this.data24 = data24;
	}
	public int getData25() {
		return data25;
	}
	public void setData25(int data25) {
		this.data25 = data25;
	}
	public int getData26() {
		return data26;
	}
	public void setData26(int data26) {
		this.data26 = data26;
	}
	public int getData27() {
		return data21-data24;
	}
	public void setData27(int data27) {
		this.data27 = data27;
	}
	public int getData28() {
		return data22-data25;
	}
	public void setData28(int data28) {
		this.data28 = data28;
	}
	public int getData29() {
		return data23-data26;
	}
	public void setData29(int data29) {
		this.data29 = data29;
	}
	public String getData30() {
		if((data22)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data25)*100/(data22));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData30(String data30) {
		this.data30 = data30;
	}
	public String getData31() {
		if((data21)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data24)*100/(data21));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData31(String data31) {
		this.data31 = data31;
	}
	public int getData32() {
		return data32;
	}
	public void setData32(int data32) {
		this.data32 = data32;
	}
	public int getData33() {
		return data33;
	}
	public void setData33(int data33) {
		this.data33 = data33;
	}
	public String getData34() {
		if((data32)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data33)*100/(data32));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData34(String data34) {
		this.data34 = data34;
	}
	public int getData35() {
		return data35;
	}
	public void setData35(int data35) {
		this.data35 = data35;
	}
	public String getData36() {
		if((data32)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data35)*100/(data32));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData36(String data36) {
		this.data36 = data36;
	}
	public int getData37() {
		return data37;
	}
	public void setData37(int data37) {
		this.data37 = data37;
	}
	public int getData38() {
		return data38;
	}
	public void setData38(int data38) {
		this.data38 = data38;
	}
	public int getData39() {
		return data39;
	}
	public void setData39(int data39) {
		this.data39 = data39;
	}
	public int getData40() {
		return data40;
	}
	public void setData40(int data40) {
		this.data40 = data40;
	}
	public int getData41() {
		return data41;
	}
	public void setData41(int data41) {
		this.data41 = data41;
	}
	public int getData42() {
		return data42;
	}
	public void setData42(int data42) {
		this.data42 = data42;
	}
	public int getData43() {
		return data37-data40;
	}
	public void setData43(int data43) {
		this.data43 = data43;
	}
	public int getData44() {
		return data38-data41;
	}
	public void setData44(int data44) {
		this.data44 = data44;
	}
	public int getData45() {
		return data39-data42;
	}
	public void setData45(int data45) {
		this.data45 = data45;
	}
	public String getData46() {
		if((data38)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data41)*100/(data38));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData46(String data46) {
		this.data46 = data46;
	}
	public String getData47() {
		if((data37)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data40)*100/(data37));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData47(String data47) {
		this.data47 = data47;
	}
	public int getData48() {
		return data48;
	}
	public void setData48(int data48) {
		this.data48 = data48;
	}
	public int getData49() {
		return data49;
	}
	public void setData49(int data49) {
		this.data49 = data49;
	}
	public String getData50() {
		if((data48)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data49)*100/(data48));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData50(String data50) {
		this.data50 = data50;
	}
	public int getData51() {
		return data51;
	}
	public void setData51(int data51) {
		this.data51 = data51;
	}
	public String getData52() {
		if((data48)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data51)*100/(data48));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData52(String data52) {
		this.data52 = data52;
	}
	public int getData53() {
		return data53;
	}
	public void setData53(int data53) {
		this.data53 = data53;
	}
	public int getData54() {
		return data54;
	}
	public void setData54(int data54) {
		this.data54 = data54;
	}
	public int getData55() {
		return data55;
	}
	public void setData55(int data55) {
		this.data55 = data55;
	}
	public int getData56() {
		return data56;
	}
	public void setData56(int data56) {
		this.data56 = data56;
	}
	public int getData57() {
		return data57;
	}
	public void setData57(int data57) {
		this.data57 = data57;
	}
	public int getData58() {
		return data58;
	}
	public void setData58(int data58) {
		this.data58 = data58;
	}
	public int getData59() {
		return data53-data56;
	}
	public void setData59(int data59) {
		this.data59 = data59;
	}
	public int getData60() {
		return data54-data57;
	}
	public void setData60(int data60) {
		this.data60 = data60;
	}
	public int getData61() {
		return data55-data58;
	}
	public void setData61(int data61) {
		this.data61 = data61;
	}
	public String getData62() {
		if((data54)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data57)*100/(data54));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData62(String data62) {
		this.data62 = data62;
	}
	public String getData63() {
		if((data53)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data56)*100/(data53));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData63(String data63) {
		this.data63 = data63;
	}
	public int getData64() {
		return data64;
	}
	public void setData64(int data64) {
		this.data64 = data64;
	}
	public int getData65() {
		return data65;
	}
	public void setData65(int data65) {
		this.data65 = data65;
	}
	public String getData66() {
		if((data64)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data65)*100/(data64));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData66(String data66) {
		this.data66 = data66;
	}
	public int getData67() {
		return data67;
	}
	public void setData67(int data67) {
		this.data67 = data67;
	}
	public String getData68() {
		if((data64)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data67)*100/(data64));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData68(String data68) {
		this.data68 = data68;
	}
	public int getData69() {
		return data69;
	}
	public void setData69(int data69) {
		this.data69 = data69;
	}
	public int getData70() {
		return data70;
	}
	public void setData70(int data70) {
		this.data70 = data70;
	}
	public int getData71() {
		return data71;
	}
	public void setData71(int data71) {
		this.data71 = data71;
	}
	public int getData72() {
		return data72;
	}
	public void setData72(int data72) {
		this.data72 = data72;
	}
	public int getData73() {
		return data73;
	}
	public void setData73(int data73) {
		this.data73 = data73;
	}
	public int getData74() {
		return data74;
	}
	public void setData74(int data74) {
		this.data74 = data74;
	}
	public int getData75() {
		return data69-data72;
	}
	public void setData75(int data75) {
		this.data75 = data75;
	}
	public int getData76() {
		return data70-data73;
	}
	public void setData76(int data76) {
		this.data76 = data76;
	}
	public int getData77() {
		return data71-data74;
	}
	public void setData77(int data77) {
		this.data77 = data77;
	}
	public String getData78() {
		if((data70)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data73)*100/(data70));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData78(String data78) {
		this.data78 = data78;
	}
	public String getData79() {
		if((data69)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data72)*100/(data69));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData79(String data79) {
		this.data79 = data79;
	}
	public int getData80() {
		return data80;
	}
	public void setData80(int data80) {
		this.data80 = data80;
	}
	public int getData81() {
		return data81;
	}
	public void setData81(int data81) {
		this.data81 = data81;
	}
	public int getData82() {
		return data82;
	}
	public void setData82(int data82) {
		this.data82 = data82;
	}
	public int getData83() {
		return data83;
	}
	public void setData83(int data83) {
		this.data83 = data83;
	}
	public int getData84() {
		return data82-data83;
	}
	public void setData84(int data84) {
		this.data84 = data84;
	}
	public String getData85() {
		if((data82)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data83)*100/(data82));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData85(String data85) {
		this.data85 = data85;
	}
	public int getData86() {
		return data86;
	}
	public void setData86(int data86) {
		this.data86 = data86;
	}
	public int getData87() {
		return data87;
	}
	public void setData87(int data87) {
		this.data87 = data87;
	}
	public int getData88() {
		return data88;
	}
	public void setData88(int data88) {
		this.data88 = data88;
	}
	public int getData89() {
		return data89;
	}
	public void setData89(int data89) {
		this.data89 = data89;
	}
	public int getData90() {
		return data88-data89;
	}
	public void setData90(int data90) {
		this.data90 = data90;
	}
	public String getData91() {
		if((data88)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data89)*100/(data88));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData91(String data91) {
		this.data91 = data91;
	}
	public int getData92() {
		return data92;
	}
	public void setData92(int data92) {
		this.data92 = data92;
	}
	public int getData93() {
		return data93;
	}
	public void setData93(int data93) {
		this.data93 = data93;
	}
	public int getData94() {
		return data94;
	}
	public void setData94(int data94) {
		this.data94 = data94;
	}
	public int getData95() {
		return data95;
	}
	public void setData95(int data95) {
		this.data95 = data95;
	}
	public int getData96() {
		return data94-data95;
	}
	public void setData96(int data96) {
		this.data96 = data96;
	}
	public String getData97() {
		if((data94)!=0){
			DecimalFormat myFormat = new DecimalFormat("0.00"); 
			String strFloat = myFormat.format((float)(data95)*100/(data94));
			return strFloat+"%";
		}else{
			return "100.00%";
		}
	}
	public void setData97(String data97) {
		this.data97 = data97;
	}
}
