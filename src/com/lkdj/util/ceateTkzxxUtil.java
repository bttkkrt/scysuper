package com.lkdj.util;

import java.util.Date;

import com.lkdj.kzk.GetTkzxxRequest;
import com.lkdj.kzk.GetTkzxxRequest.Tkzxxs;

public class ceateTkzxxUtil {

	public Tkzxxs ceateTkzxxs(String sjly,Date rksj,String kzxx,String zjhm,String gsid){

		Tkzxxs tkzxxs = new GetTkzxxRequest.Tkzxxs();
		tkzxxs.setAppid("APP-00199");
		tkzxxs.setSjly(sjly);
		tkzxxs.setKzxx(kzxx);
		tkzxxs.setZjhm(zjhm);
		tkzxxs.setGsid(gsid);
		return tkzxxs;
	}

}
