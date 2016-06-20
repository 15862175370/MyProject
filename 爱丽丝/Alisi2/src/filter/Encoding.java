package filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;



/**
 * Servlet Filter implementation class Encoding
 */
@WebFilter({ "/Encoding", "/*" })
public class Encoding implements Filter {
	private FilterConfig config = null;
	private ServletContext context = null;
	//private String encode = null;

    /**
     * Default constructor. 
     */
    public Encoding() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
	
			System.out.println("dofilter  come into filter");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");

			chain.doFilter(new MyHttpServletRequest((HttpServletRequest) request), response);
			
			
	
	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config = fConfig;
		this.context = fConfig.getServletContext();
		//this.encode = context.getInitParameter("encode");
		
		System.out.println("come into filter");
	}
	private class MyHttpServletRequest extends HttpServletRequestWrapper{
		private  HttpServletRequest request = null;
		private boolean isNotEncode = true;
		public MyHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		
		@Override
		public Map<String,String[]> getParameterMap() {
			try{
				if(request.getMethod().equalsIgnoreCase("POST")){
					request.setCharacterEncoding("utf-8");
					return request.getParameterMap();
				}else if(request.getMethod().equalsIgnoreCase("GET")){
					Map<String,String[]> map = request.getParameterMap();
					
					
					if(isNotEncode){
						for(Map.Entry<String, String[]> entry : map.entrySet()){
							String [] vs = entry.getValue();
							for(int i=0;i<vs.length;i++){
								vs[i] = new String(vs[i].getBytes("iso8859-1"),"utf-8");
							}
						}
						isNotEncode = false;
					}
					return map;
				}else{
					return request.getParameterMap();
				}
			}catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
		@Override
		public String[] getParameterValues(String name) {
			return getParameterMap().get(name);
		}
		
		@Override
		public String getParameter(String name) {
			return getParameterValues(name) == null ? null : getParameterValues(name)[0];
		}
		
	} 

}
