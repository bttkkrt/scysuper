package com.lkdj.util;

import com.lkdj.frk.GetFRJCInsertRequest;
import com.lkdj.frk.GetFRJCInsertRequest.Frjc;

public class ceateFRJCUtil {

	public Frjc createFrjc(String gszch,String ssqy,String fddbrxm,String jyfw,String kyrq,String dsdjrq,String guosdjrq,String gsdjrq,String zclx,String zcdz,
							String zczb,String zcbz,String sjjydz,String qyzt,String lxdh,String yzbm,String dwdm,String nsrsbh,String nsrbm,String zzjgdm,
							String jglx,String jgmc,String qyxz,String qyrs,String sshy){
		Frjc frjc = new GetFRJCInsertRequest.Frjc();
		frjc.setGszch(gszch);
		frjc.setFddbrxm(fddbrxm);
		frjc.setFddbrzjhm("");
		frjc.setJyfw(jyfw);
		frjc.setKyrq(kyrq);
		frjc.setDsdjrq(dsdjrq);
		frjc.setGuosdjrq(guosdjrq);
		frjc.setGsdjrq(gsdjrq);
		frjc.setZclx(zclx);
		frjc.setZcdz(zcdz);
		long a = 0;
		if(zczb!=null&&!"".equals(zczb))
		{
			if(zczb.indexOf(".") != -1)
			{
				a = Long.valueOf(zczb.substring(0,zczb.indexOf(".")));
			}
			else
			{
				a = Long.valueOf(zczb);
			}
		}
		
		frjc.setZczb(a);
		frjc.setZcbz(zcbz);
		frjc.setSjjydz(sjjydz);
		frjc.setQyzt(qyzt);
		frjc.setLxdh(lxdh);
		frjc.setYzbm(yzbm);
		frjc.setDwdm(yzbm);
		frjc.setNsrsbh(nsrsbh);
		frjc.setNsrbm(nsrbm);
		frjc.setZzjgdm(zzjgdm);
		frjc.setJglx(jglx);
		frjc.setJgmc(jgmc);
		frjc.setQyxz(qyxz);
		long b = 0;
		if(qyrs!=null&&!"".equals(qyrs))
		{
			if(qyrs.indexOf(".") != -1)
			{
				b = Long.valueOf(qyrs.substring(0,qyrs.indexOf(".")));
			}
			else
			{
				b = Long.valueOf(qyrs);
			}
		}
		frjc.setQyrs(b);
		frjc.setSshy(sshy);
		frjc.setSsqy(ssqy);
		frjc.setAppid("APP-00199");
		frjc.setShxydm("");
		return frjc;
	}

}
