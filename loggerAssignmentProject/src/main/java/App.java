import com.loggerAssignment.controller.MonitorLogsController;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
    {
        System.out.println("In Main Method::Start::");
		MonitorLogsController obj = new MonitorLogsController();
        obj.monitorLogs();
        System.out.println("In Main Method::End::");
    }
}
