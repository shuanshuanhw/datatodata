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
if(hashCodeV<0){//�п����Ǹ���
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
	//	System.out.println("�����ڷ���Ǯ��"+every*a);
		System.out.println("�����ڷ���Ǯ��"+every*b);
		System.out.println("�м��ڷ���Ǯ��"+every*c);
		System.out.println("�����η���Ǯ��"+every*d);
		System.out.println("�����η���Ǯ��"+every*e);
		System.out.println("���ݳ�����Ǯ��"+every*f);
		System.out.println("�ݳ�����Ǯ��"+every*g);
	//	System.out.println("�����ڷ���Ǯ��"+every*a);
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
		if(totalScore*other!=totalMoney)System.out.println("����������Ϊ��"+totalScore*other+" ����"+totalScore*other1);
		else {
			System.out.println("ÿ��AA��Ŀ�ֵõ�Ǯ��"+120*other);
			System.out.println("ÿ��A��Ŀ�ֵõ�Ǯ��"+100*other);
			System.out.println("ÿ��B��Ŀ�ֵõ�Ǯ��"+80*other);
			System.out.println("ÿ��C��Ŀ�ֵõ�Ǯ��"+60*other);
			System.out.println("ÿ��D��Ŀ�ֵõ�Ǯ��"+40*other);
			System.out.println("������"+(40*other*D+120*other*AA+100*other*A+80*other*B+60*other*C));
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
			System.out.println("ÿ����Ŀ�����˷ֵõ�Ǯ��"+Math.round(jyScore*other));
			System.out.println("ÿ����Ŀ�����˷ֵõ�Ǯ��"+Math.round(jyScore*other));
			System.out.println("ÿ����Ŀ�����˷ֵõ�Ǯ��"+Math.round(fzrScore*other));
			System.out.println("ÿ����Ŀ��Ҫ��Ա�ֵõ�Ǯ��"+Math.round(zycyScore*other));
			System.out.println("ÿ����Ŀһ���Ա�ֵõ�Ǯ��"+Math.round(ybcyScore*other));
			System.out.println("ÿ����Ŀ�����Ա�ֵõ�Ǯ��"+Math.round(cycyScore*other));
			System.out.println("���=="+(totalMoney-(Math.round(jyScore*other)*jy+Math.round(fzrScore*other)*fzr+Math.round(zycyScore*other)*zycy+Math.round(ybcyScore*other)*ybcy+Math.round(cycyScore*other)*cycy)));
		}
		
			
			//System.out.println("������"+(40*other*D+120*other*AA+100*other*A+80*other*B+60*other*C));
		
		return "";
	}
}