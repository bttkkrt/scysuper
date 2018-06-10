package com.lkdj.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lkdj.rkk.GetLkRKRequest;
import com.lkdj.rkk.GetLkRKRequest.Lkrk;

public class ceateLkRKUtil {

	public Lkrk ceateLkRK(String zjlx,String zjhm,String xm,String xb,String gj,String whcd,
							String zy,String ryzt){

		Lkrk lkrk = new GetLkRKRequest.Lkrk();
		lkrk.setAPPID("APP-00199");
		lkrk.setZJLX(zjlx);
		lkrk.setZJHM(zjhm);
		lkrk.setXM(xm);
		lkrk.setCYM("");
		lkrk.setXB(xb);
		lkrk.setGJ(gj);
		lkrk.setMZ("");
		lkrk.setCSRQ("");
		lkrk.setJG("");
		lkrk.setZZMM("");
		lkrk.setZJXY("");
		lkrk.setWHCD(whcd);
		lkrk.setBYZK("");
		lkrk.setHYZK("");
		lkrk.setZY(zy);
		lkrk.setXX("");
		lkrk.setHH("");
		lkrk.setRYLB("");
		lkrk.setRYZT(ryzt);
		return lkrk;
	}
	
	public static void main(String[] args) {
		String xx = "我是中文EE1111_\\//??";
        String repickStr = xx.replaceAll("[^\\w]|_","");  
        System.out.println(repickStr);
	}

}
