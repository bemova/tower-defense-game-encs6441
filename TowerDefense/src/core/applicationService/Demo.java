package core.applicationService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo {
	public static void main(String[] args) {
		    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
	        IAplication app = context.getBean(MyApplication.class);
	         
	        app.processMessage("Hi Mojtaba", "mojtaba@abc.com");
	         //test
	        //close the context
	        //test2
	        
	        System.out.println("test");
	        System.out.println("test");
	        System.out.println("test");
	        context.close();
	}

}
