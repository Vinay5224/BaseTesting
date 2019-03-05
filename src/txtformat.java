
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;


public class txtformat {
	static JSONObject jsontxtarr = new JSONObject();
	public static void main(String[] args) throws IOException, JSONException, ParseException, org.json.simple.parser.ParseException {
		// TODO Auto-generated method stub
	//Json calling
		JSONArray arrtxt = new JSONArray();
		
		String content = new String(Files.readAllBytes(Paths.get(args[0])));
        jsontxtarr = new JSONObject(content);
		
		//Json Preparation
		///home/ec2-user/perftest/individual_plain/"+args[0]+".csv"
		/*BufferedReader br = new BufferedReader(new FileReader(new File("/home/exa1/Documents/Files/ind.csv")));
				//"/home/ec2-user/perftest/individual_plain/"+args[0])));
		 String line1=null;
		 List<Integer> index =new ArrayList<Integer>();
		 while((line1=br.readLine())!=null){
			 String[] colsplit =line1.split(",");
			 for(int i=0;i<colsplit.length;i++){
					JSONObject temp1 = new JSONObject();
					temp1.put("name", colsplit[i]);
					temp1.put("tablename", "individual_profile_schema");
					arrtxt.add(temp1);
			 }
			 break;
		 }
		 JSONArray jsonrows = new JSONArray();
		//Taking particular columns from the text file 
		 while ((line1 = br.readLine()) != null){
		  	String[] splitrow = line1.split(",");
		  	int assign = 0;
		  	//JSONObject temp = new JSONObject();
		  	LinkedHashMap<String, Object> temp = new LinkedHashMap<String, Object>();
		  	for (String i : splitrow) {
		  			temp.put(String.valueOf(assign), i);
		  			assign++;
		  	 }
		  	jsonrows.add(temp);
		 }
		 //preparing path7 api call
		 jsontxtarr.put("RECORDS", jsonrows);
		 jsontxtarr.put("COLUMNS", arrtxt);
		 jsontxtarr.put("aid", "7");
		 jsontxtarr.put("tid", "0");
//System.out.println(jsontxtarr.toString()+"\n"+index.size());
*/        
        FileWriter fw = new FileWriter("/home/ec2-user/perftest/path6out.txt");
		 JSONObject outputjson = readJsonFromUrl("http://localhost:45670/path6");
        //fw.write(jsontxtarr.toString()); 
        fw.write(outputjson.toString());
		 fw.flush();
		 //System.out.println("Response Completed"+outputjson.getString("RECORDS"));
	}
	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException, ParseException {
		//InputStream is = new URL(url).openStream();
		URL object = new URL(url);
		HttpURLConnection contxt = (HttpURLConnection) object.openConnection();
		try {
			
			contxt.setDoOutput(true);
			contxt.setDoInput(true);
			contxt.setRequestProperty("Content-Type", "application/json");
			contxt.setRequestProperty("Accept", "application/json");
			contxt.setRequestMethod("POST");
			 OutputStream os = contxt.getOutputStream();
	            os.write(jsontxtarr.toString().getBytes());
	            os.flush();
			BufferedReader rd = new BufferedReader(new InputStreamReader(contxt.getInputStream()));
			
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			contxt.disconnect();
		}
	}
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

}

