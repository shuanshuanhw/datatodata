package test;

import java.util.UUID;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		jisuan ji = new jisuan();
	//	String string = ji.jis(0, 5, 2, 2, 1, 120, 100, 80, 60, 40, 9840.0);
		//ji.Ajs(1, 3, 3, 1, 5, 30, 80, 40, 20, 6, 1200.0);
		
//		ji.jx(0, 11, 14, 17, 20, 22, 25, 50, 50, 25, 18, 9, 3, 1);
		
		
		int hashCodeV=0;
		for(int i=0;i<10000;i++)
		{
		hashCodeV=UUID.randomUUID().toString().hashCode();
if(hashCodeV<0){//有可能是负数
hashCodeV=-hashCodeV;
}
System.out.println(hashCodeV);
		}
	}
	


}
class jisuan
{
	public String jx(int a,int b,int c,int d,int e,int f,int g,int aa,int bb,int cc,int dd,int ee,int ff,int gg)
	{
		int other = 0;
		int totalScore=b*bb+c*cc+d*dd+e*ee+f*ff+g*gg;
		int every = 29040/totalScore;
	//	System.out.println("初级岗发的钱："+every*a);
		System.out.println("初级岗发的钱："+every*b);
		System.out.println("中级岗发的钱："+every*c);
		System.out.println("副主任发的钱："+every*d);
		System.out.println("正主任发的钱："+every*e);
		System.out.println("副馆长发的钱："+every*f);
		System.out.println("馆长发的钱："+every*g);
	//	System.out.println("初级岗发的钱："+every*a);
		System.out.println(every*b*bb+every*c*cc+every*d*dd+every*e*ee+every*f*ff+every*g*gg);
		return "";
	}
	public String jis(int AA,int A,int B,int C,int D,int AAScore,int AScore,int BScore,int CScore,int DScore,Double totalMoney)
	{
		double other=0;
		double other1=0;
		int totalScore=AA*AAScore+A*AScore+B*BScore+C*CScore+D*DScore;
		if(ifok(totalScore, totalMoney))
		{
			other=Math.round(totalMoney/totalScore);
			other1=Math.ceil(totalMoney/totalScore);
		}
		if(totalScore*other!=totalMoney)System.out.println("建议输入金额为："+totalScore*other+" 或者"+totalScore*other1);
		else {
			System.out.println("每个AA项目分得的钱："+120*other);
			System.out.println("每个A项目分得的钱："+100*other);
			System.out.println("每个B项目分得的钱："+80*other);
			System.out.println("每个C项目分得的钱："+60*other);
			System.out.println("每个D项目分得的钱："+40*other);
			System.out.println("总数："+(40*other*D+120*other*AA+100*other*A+80*other*B+60*other*C));
		}
		return "";
	}
	
	public boolean ifok(int totalScore,Double totalMoney)
	
	{
		try {
		double other=Math.round(totalMoney/totalScore);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public boolean ifok1(int totalScore,Double totalMoney)
	
	{
		try {
		double other=Math.round(totalMoney/totalScore);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	public String Ajs(int jy,int fzr,int zycy,int ybcy,int cycy,int jyScore,int fzrScore,int zycyScore,int ybcyScore,int cycyScore,Double totalMoney)
	{
		double other=0;
		int totalScore=jy*jyScore+fzr*fzrScore+zycy*zycyScore+ybcy*ybcyScore+cycy*cycyScore;
		if(ifok1(totalScore, totalMoney))
		{
			other=totalMoney/totalScore;
			System.out.println("totalScore"+totalScore);
			System.out.println("totalMoney"+totalMoney);
			System.out.println("other"+other);
			System.out.println("每个项目建议人分得的钱："+Math.round(jyScore*other));
			System.out.println("每个项目建议人分得的钱："+Math.round(jyScore*other));
			System.out.println("每个项目负责人分得的钱："+Math.round(fzrScore*other));
			System.out.println("每个项目主要成员分得的钱："+Math.round(zycyScore*other));
			System.out.println("每个项目一般成员分得的钱："+Math.round(ybcyScore*other));
			System.out.println("每个项目参与成员分得的钱："+Math.round(cycyScore*other));
			System.out.println("余额=="+(totalMoney-(Math.round(jyScore*other)*jy+Math.round(fzrScore*other)*fzr+Math.round(zycyScore*other)*zycy+Math.round(ybcyScore*other)*ybcy+Math.round(cycyScore*other)*cycy)));
		}
		
			
			//System.out.println("总数："+(40*other*D+120*other*AA+100*other*A+80*other*B+60*other*C));
		
		return "";
	}
}