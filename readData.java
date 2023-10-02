package GeneticAlgorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class readData {

	public foodData[] importFile() {
		foodData[] data = new foodData[62];
		int i=0;
		try{
			InputStreamReader isr = new InputStreamReader(new FileInputStream("src//foodData.csv"),"big5");//檔案讀取路徑
			BufferedReader reader = new BufferedReader(isr);
//			BufferedWriter bw = new BufferedWriter(new FileWriter("src//test.csv"));//檔案輸出路徑
			String line = null;
			
			while((line=reader.readLine())!=null){
				String item[] = line.split(",");
				/** 讀取 **/
//				String  data1= item[0].trim();
//				String  data2= item[1].trim();
//				String  data3= item[2].trim();
//				String  data4= item[3].trim();
//				String  data5= item[4].trim();
//				String  data6= item[5].trim();
//				String  data7= item[6].trim();
//				System.out.print(data1+"\t"+ data2+"\t"+ data3+"\t"+ data4+"\t"+ data5+"\t"+ data6+"\t"+ data7+"\n"); 
//				可自行變化成存入陣列或arrayList方便之後存取
				data[i] = new foodData(item[0].trim(),Integer.parseInt(item[1].trim()),Double.parseDouble(item[2].trim()),Double.parseDouble(item[3].trim()),Double.parseDouble(item[4].trim()),Double.parseDouble(item[5].trim()),item[6].trim());
				i++;
			}
			//bw.close();
		} 
		catch (FileNotFoundException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new readData();
//	}
}
