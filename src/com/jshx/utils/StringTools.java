package com.jshx.utils;

public class StringTools {
	private static String[] arrays = new String[]{" _0","电工作业_1","焊接与热切割作业_2","高处作业_3","制冷与空调作业_4","冶金（有色）生产安全作业_5","危险化学品安全作业_6","烟花爆竹安全作业_7" +
		"高压电工作业_1","低压电工作业_2","防爆电气作业_3",
		"熔化焊接与热切割作业_1","压力焊作业_2","钎焊作业_3",
		"登高架设作业_1","高处安装、维护、拆除作业_2",
		"制冷与空调设备运行操作作业_1","制冷与空调设备安装修理作业_2",
		"煤气作业_1",
		"光气及光气化工艺作业_1","氯碱电解工艺作业_2","氯化工艺作业_3","硝化工艺作业_4","合成氨工艺作业_5","裂解（裂化）工艺作业_6","氟化工艺作业_7",
		"加氢工艺作业_8","重氮化工艺作业_9","氧化工艺作业_10","过氧化工艺作业_11","胺基化工艺作业_12","磺化工艺作业_13","聚合工艺作业_14","烷基化工艺作业_15","化工自动化控制仪表作业_16",
		"烟火药制造作业_1","黑火药制造作业_2","引火线制造作业_3","烟花爆竹产品涉药作业_4","烟花爆竹储存作业_5"}; 
	
	public  static String getIndexFromName(String name){
		String result="";
		try {
			for(String s:arrays){
				if(s.contains(name)){
					result = s.substring(s.lastIndexOf("_")+1);
					break;
				}else{
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  result;
		
	}
	public static String getSexIndexFromName(String name){
		String result="1";
		if(name!=null&&"女".equals(name.trim())){
			result = "0";
		}
		if(name!=null&&"博士".equals(name.trim())){
			result = "1";
		}
		if(name!=null&&"硕士".equals(name.trim())){
			result = "2";
		}
		if(name!=null&&"本科".equals(name.trim())){
			result = "3";
		}
		if(name!=null&&"大专".equals(name.trim())){
			result = "4";
		}
		if(name!=null&&"中专".equals(name.trim())){
			result = "5";
		}
		if(name!=null&&"其它".equals(name.trim())){
			result = "6";
		}
		return result;
	}
	
	public static String getXmIndexFromName(String name)
	{
		String result= name;
		if(name!=null&&"锅炉压力容器压力管道安全管理".equals(name.trim())){
			result = "A3";
		}
		else if(name!=null&&"电梯安全管理".equals(name.trim())){
			result = "A4";
		}
		else if(name!=null&&"起重机械安全管理".equals(name.trim())){
			result = "A5";
		}
		else if(name!=null&&"场（厂）内专用机动车辆安全管理".equals(name.trim())){
			result = "A8";
		}
		else if(name!=null&&"一级锅炉司炉".equals(name.trim())){
			result = "G1";
		}
		else if(name!=null&&"二级锅炉司炉".equals(name.trim())){
			result = "G2";
		}
		else if(name!=null&&"一级锅炉水质处理".equals(name.trim())){
			result = "G4";
		}
		else if(name!=null&&"二级锅炉水质处理（电站锅炉除外）".equals(name.trim())){
			result = "G5";
		}
		else if(name!=null&&"固定式压力容器操作".equals(name.trim())){
			result = "R1";
		}
		else if(name!=null&&"电梯司机".equals(name.trim())){
			result = "T3";
		}
		else if(name!=null&&"起重机械指挥".equals(name.trim())){
			result = "Q3";
		}
		else if(name!=null&&"桥门式起重机司机".equals(name.trim())){
			result = "Q4";
		}
		else if(name!=null&&"门座式起重机司机".equals(name.trim())){
			result = "Q6";
		}
		else if(name!=null&&"流动式起重机司机".equals(name.trim())){
			result = "Q8";
		}
		else if(name!=null&&"机械式停车设备司机".equals(name.trim())){
			result = "Q10";
		}
		else if(name!=null&&"叉车司机".equals(name.trim())){
			result = "N2";
		}
		else if(name!=null&&"蓄电池观光车司机".equals(name.trim())){
			result = "N5";
		}
		return result;
	}
	
	public static String getXmIndexFromCode(String name)
	{
		String result= name;
		if(name!=null&&"A3".equals(name.trim())){
			result = "锅炉压力容器压力管道安全管理";
		}
		else if(name!=null&&"A4".equals(name.trim())){
			result = "电梯安全管理";
		}
		else if(name!=null&&"A5".equals(name.trim())){
			result = "起重机械安全管理";
		}
		else if(name!=null&&"A8".equals(name.trim())){
			result = "场（厂）内专用机动车辆安全管理";
		}
		else if(name!=null&&"G1".equals(name.trim())){
			result = "一级锅炉司炉";
		}
		else if(name!=null&&"G2".equals(name.trim())){
			result = "二级锅炉司炉";
		}
		else if(name!=null&&"G4".equals(name.trim())){
			result = "一级锅炉水质处理";
		}
		else if(name!=null&&"G5".equals(name.trim())){
			result = "二级锅炉水质处理（电站锅炉除外）";
		}
		else if(name!=null&&"R1".equals(name.trim())){
			result = "固定式压力容器操作";
		}
		else if(name!=null&&"T3".equals(name.trim())){
			result = "电梯司机";
		}
		else if(name!=null&&"Q3".equals(name.trim())){
			result = "起重机械指挥";
		}
		else if(name!=null&&"Q4".equals(name.trim())){
			result = "桥门式起重机司机";
		}
		else if(name!=null&&"Q6".equals(name.trim())){
			result = "门座式起重机司机";
		}
		else if(name!=null&&"Q8".equals(name.trim())){
			result = "流动式起重机司机";
		}
		else if(name!=null&&"Q10".equals(name.trim())){
			result = "机械式停车设备司机";
		}
		else if(name!=null&&"N2".equals(name.trim())){
			result = "叉车司机";
		}
		else if(name!=null&&"N5".equals(name.trim())){
			result = "蓄电池观光车司机";
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(getIndexFromName("gf"));
	}

}