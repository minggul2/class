package guestbook.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.bean.GuestbookDTO;
import guestbook.dao.GuestbookDAO;

@WebServlet("/GuestbookListServlet")
public class GuestbookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터
		
		int pg = Integer.parseInt(request.getParameter("pg"));
		
		
		//DB 1 1페이지당 3개씩
		int endNum = pg * 3;
		int startNum = endNum - 2;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		
		out.println("<html>");
		out.println("<head>");
		
//		out.println("<style> .test_table { table-layout: fixed; }");//높이고정
//		out.println("		 .test_table td { width : 50px; overflow : hidden; text-overflow:ellipsis; white-space:nowrap; }");
//		out.println("		 .test_table tr { height : 10px; }");
//		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		
		
		
		//db에서 arrayList꺼내오기
		GuestbookDAO guestbookDAO = GuestbookDAO.getInstance();
		ArrayList<GuestbookDTO> guestbookList = new ArrayList<>();
		guestbookList = guestbookDAO.list(startNum, endNum);
		
		//1페이지당 3개씩

		//페이징 처리
		int totalA = guestbookDAO.getTotalA();	//총 글수
		int totalP = (totalA + 2) / 3; //총 페이지 수
		
		
		out.println("<h2 align = 'center'>글목록</h2>");
		
		out.println("<div align = 'center' style = 'font-size : 50'>"); 
		for(int i = 1; i <= totalP; i++) {
			if(pg == i)		
				out.println("[<a href='/guestbookservlet/GuestbookListServlet?pg="+i+"' style = 'color:red;  text-decoration: underline;'>"+i+"</a>]");
			else {
				out.println("[<a href='/guestbookservlet/GuestbookListServlet?pg="+i+"' style = 'color:black; text-decoration: none;'>"+i+"</a>]");
			}
		}	
		out.println("</div>");
		
		out.println("<br>");
		
		for(GuestbookDTO dto : guestbookList) {
			out.println("<table style='table-layout: fixed;' border = 1 cellpadding = '5' cellspacing ='0' align = 'center'>");
			out.println("<tr>");
			out.println("<td width = 100>작성자</td>");
			out.println("<td width = 100>"+dto.getName()+"</td>");
			out.println("<td width = 100>작성일</td>");
			out.println("<td width = 100>"+dto.getLogtime()+"</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>이메일</td>");
			out.println("<td colspan = '3'>"+dto.getEmail()+"</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>홈페이지</td>");
			out.println("<td colspan = '3' style = 'text-overflow:ellipsis; overflow:hidden;'><a href = '"+dto.getHomepage()+"'><nobr>"+dto.getHomepage()+"<nobr></a></td>");
//			out.println("<td colspan = '3' style='width:50%; text-overflow:ellipsis; overflow:hidden; white-space:nowrap;'><a href = '"+dto.getHomepage()+"'</a>"+dto.getHomepage()+"</td>");
			out.println("</tr>");
					
			out.println("<tr>");
			out.println("<td>제목</td>");
			out.println("<td colspan = '3'>"+dto.getSubject()+"</td>");
			out.println("</tr>");
			/*
			out.println("<tr>");
			out.println("<td colsspan = '4'><textarea rows = '5' cols = '40' style = 'overflow-y:scroll; resize:none;' readonly>"+dto.getContent()+"</textarea></td>");
			out.println("</tr>");
			*/
			out.println("<tr>");
			out.println("<td colspan = '4'><pre>"+dto.getContent()+"</pre></td>");
			out.println("</tr>");
			
			out.println("</table>");
			out.println("<br>");
			out.println("<br>");
			out.println("<hr color = 'red' width = '500' align = 'center' >");
		}
		
		out.println("</body>");
		out.println("</html>");
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("리스트");
		out.println("<head>");
		out.println("</head>");
		out.println("</html>");
	}
	
	

}
