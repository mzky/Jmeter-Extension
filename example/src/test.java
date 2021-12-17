package src;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

 
public class test extends AbstractJavaSamplerClient{

     //设置传入的参数，可以设置多个，已设置的参数会显示到Jmeter的参数列表中
    public Arguments getDefaultParameters() {//必要项，不能省略
    	Arguments args = new Arguments();

    	args.addArgument("address", "192.168.218.82");//无参数可省略，第二项为默认参数，可省略
    	args.addArgument("rulenum","53582C51C506201E");//无参数可省略，第二项为默认参数，可省略
    	args.addArgument("pdffile", "D://test.pdf");//无参数可省略，第二项为默认参数，可省略
        return args;
    }
     
    //初始化方法，实际运行时每个线程仅执行一次，在测试方法运行前执行
    public void setupTest(JavaSamplerContext context) {//可以省略，去掉本项后可同时去掉全局变量
        SampleResult sampleResult = new SampleResult();
        String address = context.getParameter("address");   //获取在Jmeter中设置的参数值，检测不为空
        if (address != null && address.length() > 0) {
        	sampleResult.setSamplerData(address);
        }
        String rulenum = context.getParameter("rulenum");   //获取在Jmeter中设置的参数值，检测不为空
        if (rulenum != null && rulenum.length() > 0) {
        	sampleResult.setSamplerData(rulenum);
        }
        String pdffile=context.getParameter("pdffile");//获取在Jmeter中设置的参数值，检测不为空
        if (pdffile != null && pdffile.length() > 0) {
        	sampleResult.setSamplerData(pdffile);
        }
    }
 
     //测试执行的循环体，根据线程数和循环次数的不同可执行多次
    @Override
    public SampleResult runTest(JavaSamplerContext context) {
    	SampleResult sampleResult = new SampleResult();
        String address = context.getParameter("address");//获取参数值，可在GUI页面进行参数化
        String rulenum = context.getParameter("rulenum");//获取参数值，可在GUI页面进行参数化
        String pdffile = context.getParameter("pdffile");//获取参数值，可在GUI页面进行参数化
        
        sampleResult.sampleStart();      //事务的起点，必选项
        try {
            AnySignClientTool anySignClientTool = new AnySignClientTool(address, "8002");
            byte[] pdfBty = ClientUtil.readFileToByteArray(new File(pdffile));  //pdf字节数组
            ChannelMessage message = anySignClientTool.pdfSign(rulenum, pdfBty);   //签章规则编号
   	        
   	        sampleResult.setResponseData(message.getStatusCode(),"");//设置返回消息，注意第二个参数必须这么写，可忽略
   	        sampleResult.setResponseMessageOK();//成功的Message，可忽略
   	        sampleResult.setSuccessful(true); //设置本次事务成功，必选项
        } catch (Exception e) {
        	sampleResult.setResponseMessage("Error:"+e.toString());//错误信息，可忽略
            sampleResult.setResponseData("Error:"+e.toString(),"");//设置返回消息，打印错误到GUI，注意第二个参数必须这么写，可忽略
            sampleResult.setSuccessful(false); //设置本次事务失败，必选项
        }finally{
        	sampleResult.sampleEnd();//事务的终点，必选项
        }
        return sampleResult;
    }
     
    //结束方法，实际运行时每个线程仅执行一次，在测试方法运行结束后执行
    public void teardownTest(JavaSamplerContext context) {//可以省略
    }
    
//    public static void main(String[] args) {//导出jar包前，必须、必须、必须屏蔽main函数，否则无法使用
//    	System.out.println("test");//jmeter不支持System.out.println，导出jar包前需要屏蔽掉
//    	test test = new test();
//    	test.setupTest(null);
//    	test.runTest(null);    	
//	}
}