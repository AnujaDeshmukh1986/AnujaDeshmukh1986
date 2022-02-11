package com.loggerAssignment.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.loggerAssignment.beans.LoggerBean;
import com.loggerAssignment.dao.MonitorLogsDao;
public class MonitorLogService {

	MonitorLogsDao dao = new MonitorLogsDao();
	public void monitorLogs() throws Exception
	{
		System.out.println("In MonitorLogService:: monitorLogs::Start::"); 
		try (Reader reader = new FileReader("D://Projects//loggerAssignmentProject//src//main//java//com//loggerAssignment//inputFiles//logfile.txt")) {
			 StringBuilder stringBuilder = getFileContents(reader);
			 parseJSONtoBean(stringBuilder);
	     }
		System.out.println("In MonitorLogService:: monitorLogs::End::"); 
	}

	private void parseJSONtoBean(StringBuilder stringBuilder) {
		System.out.println("In MonitorLogService:: parseJSONtoBean::Start::");
		System.out.println("Parsing JSON to Bean Objects::"); 
		Gson gson = new Gson();	String key;double duration = 0;boolean alert=false;
		 List<LoggerBean> loggerBeans = Arrays.asList((gson.fromJson(stringBuilder.toString(), LoggerBean[].class)));
		 Map<String, List<LoggerBean>> beans = loggerBeans.stream()
	        .collect(Collectors.groupingBy(LoggerBean::getId));
		 //System.out.print("fileData::"+beans);
		 for(Entry<String, List<LoggerBean>> entry:beans.entrySet())
		 {
			 LoggerBean daoBean=entry.getValue().get(0);
			 key=entry.getKey();
			 if(entry.getValue().size()==2)
			 {
				 duration=entry.getValue().get(1).getTimestamp()-entry.getValue().get(0).getTimestamp();
				 //System.out.print("Logger ID::"+entry.getKey()+" ::"+duration);
				 alert=Math.abs(duration)>=8?true:false;
				 System.out.println("Logger ID::"+entry.getKey()+" Logger Type:: "+daoBean.getType()+" Host:: "+daoBean.getHost()+" Duration::"+duration+" Flag::"+alert);
			 }
			 dao.saveToDB(key,duration,daoBean.getType(),daoBean.getHost(),alert);
		 }
		 System.out.println("In MonitorLogService:: parseJSONtoBean::End::");
	}


	private StringBuilder getFileContents(Reader reader) throws IOException {
		BufferedReader bufferReader = new BufferedReader(reader);
		 StringBuilder stringBuilder = new StringBuilder();
		 String line = null;
		 stringBuilder.append("[");
		 while ((line = bufferReader.readLine()) != null) {
			 //System.out.print("line::"+line);
		 	stringBuilder.append(line.trim());
		 	stringBuilder.append(",");
		 }
		 stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		 stringBuilder.append("]");
		 reader.close();
		 //System.out.print("stringBuilder::"+stringBuilder);
		return stringBuilder;
	}
}
