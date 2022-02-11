package com.loggerAssignment.controller;

import com.loggerAssignment.service.MonitorLogService;

public class MonitorLogsController {
	MonitorLogService service = new MonitorLogService();
	
public void monitorLogs()
{
	System.out.println("In MonitorLogsController:: monitorLogs::Start::");
	try {
		service.monitorLogs();
	} catch (Exception e) {
		System.out.println("Error while processing input file!!");
	}
	System.out.println("In MonitorLogsController:: monitorLogs::End::");
}
}

