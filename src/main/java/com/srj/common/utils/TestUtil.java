package com.srj.common.utils;


import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TestUtil {
	private Set<Integer> set = new HashSet<Integer>();
	private int num = 100;//囚徒人数
	private int start = 1;//开柜子数量
	private int total = 50;//开柜子数量

	public int []  shuffle(int [] arr) {
		int [] arr2 =new int[arr.length];
		int count = arr.length;
		int cbRandCount = 0;// 索引
		int cbPosition = 0;// 位置
		int k =0;
		do {
			Random rand = new Random();
			int r = count - cbRandCount;
			cbPosition = rand.nextInt(r);
			arr2[k++] = arr[cbPosition];
			cbRandCount++;
			arr[cbPosition] = arr[r - 1];// 将最后一位数值赋值给已经被使用的cbPosition
		} while (cbRandCount < count);
		for(int i:arr2){
			//System.out.print(i+" ");
		}
		return arr2;
	}

	@Test
	public void test2(){
		int s = 0;
		int e = 0;
		for(int i=0;i<1000;i++){
			boolean b = Method();
			if(b){
				s++;
			}else{
				e++;
			}
		}

		System.out.println("成功次数:"+s+",失败次数:"+e);
	}

	public boolean Method(){
		int arr[] = new int[num];
		for(int i=0;i<num;i++){
			arr[i]=i;
		}
		//柜子
		arr = shuffle(arr);
		for(int k=0;k<num;k++){
			set.clear();
			start=1;
			boolean b = open(arr,k,k);//囚犯开柜子
			if(!b){
				//System.out.println("--------------------------任务失败！");
				return false;
			}
		}
		return true;
	}

	//开柜子递归
	private boolean open(int[] arr, int n, int m) {
		//System.out.println("囚犯"+n+"打开了"+m+"号柜子，里面写的值是"+arr[m]);
		start++;
		int p = arr[m];
		if(p!=n){
			if(start>total){
				//System.out.println("囚犯"+n+"任务失败!");
				return false;
			}else{
				set.add(p);
				//System.out.println("囚犯"+n+"继续开启"+p+"号柜子");
				return open(arr,n,p);
			}
		}else{
			//System.out.println("囚犯"+n+"任务完成");
			return true;
		}
	}
}
